package uk.ac.rgu.cm2115;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import uk.ac.rgu.cm2115.commands.Command;
import uk.ac.rgu.cm2115.commands.CommandNotExistException;
import uk.ac.rgu.cm2115.devices.Device;
import uk.ac.rgu.cm2115.devices.DeviceFactory;
import uk.ac.rgu.cm2115.devices.diagnostics.DeviceVisitor;
import uk.ac.rgu.cm2115.devices.diagnostics.SimpleDiagnosticVisitor;

public class SmartHomeMainController extends Controller<Home> {

    @FXML
    private ListView<Device<?>> lstDevices;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblName;

    @FXML
    private HBox hBoxCommands;

    @FXML
    private ComboBox<String> comboDeviceType;

    @FXML
    private TextField txtDeviceName;

    @FXML
    private TextField txtFilterDevices;

    @FXML
    private TextField txtCommand;

    @FXML
    private ComboBox<DeviceFactory> factoryType;

    private NewDeviceCommand[] newDeviceCommands = new NewDeviceCommand[3];

    @Override
    public void setModel(Home model) {
        this.model = model;

        this.lstDevices.getItems().addAll(model.getDevices());

        this.factoryType.getItems().addAll(model.getFactories());

        this.factoryType.getSelectionModel().select(0);

        Map<String, Command> commands = model.getCommands();

        commands.forEach((name, command) -> {
            Button b = new Button(name);
            b.setOnAction((e) -> {
                command.execute();
                Device<?> device = this.lstDevices.getSelectionModel().getSelectedItem();
                if (device != null) {
                    this.lblStatus.setText(device.getStatus().toString());
                }
            });
            this.hBoxCommands.getChildren().add(b);
        });

        this.comboDeviceType.getItems().add("Light");
        this.comboDeviceType.getItems().add("Smart Plug");
        this.comboDeviceType.getItems().add("Thermostat");

        this.newDeviceCommands[0] = model::addLight;
        this.newDeviceCommands[1] = model::addSmartPlug;
        this.newDeviceCommands[2] = model::addThermostat;
    }

    @FXML
    private void deviceSelected() {
        Device<?> device = this.lstDevices.getSelectionModel().getSelectedItem();
        this.lblStatus.setText(device.getStatus().toString());
    }

    @FXML
    private void runDiagnostic() {
        Device<?> device = this.lstDevices.getSelectionModel().getSelectedItem();

        if (device != null) {
            DeviceVisitor visitor = new SimpleDiagnosticVisitor();
            device.accept(visitor);
        }
    }

    @FXML
    private void btnAddDeviceClick() {
        int index = this.comboDeviceType.getSelectionModel().getSelectedIndex();
        String name = this.txtDeviceName.getText();

        DeviceFactory factory = this.factoryType.getSelectionModel().getSelectedItem();

        if (index > -1 && name != null && factory != null) {
            this.model.setFactory(factory);
            this.newDeviceCommands[index].execute(name);
            this.lstDevices.getItems().clear();
            this.lstDevices.getItems().addAll(this.model.getDevices());
        }
    }

    @FXML
    private void txtFilterDevicesOnChange() {
        String text = this.txtFilterDevices.getText();

        this.lstDevices.getItems().clear();

        List<Device<?>> devices = this.model.getDevices();

        if (text == null || text.equals("")) {
            this.lstDevices.getItems().addAll(devices);
        } else {
            List<Device<?>> filtered = devices.stream()
                    .filter((d) -> {
                        String name = d.getName().toLowerCase();
                        String search = text.toLowerCase();

                        return name.startsWith(search);

                    }).collect(Collectors.toList());

            this.lstDevices.getItems().addAll(filtered);
        }
    }

    @FXML
    private void btnCreateRoutineClick() {
        try {
            MainApp.setScene("SmartHomeRoutine", this.model);
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Cannot open the SmartHomeRoutine scene");
            alert.show();
        }
    }

    @FXML
    private void btnRunCommandClick() {
        String text = this.txtCommand.getText();

        if (text != null) {
            /*
             * Attempt to get a command with this name from the model, throw an exception if
             * it doesn't exist
             */
            try {
                Command command = this.model.getCommand(text);
                command.execute();

                /* Update the device status label, if selected */
                Device<?> device = this.lstDevices.getSelectionModel().getSelectedItem();
                if (device != null) {
                    this.lblStatus.setText(device.getStatus().toString());
                }
            } catch (CommandNotExistException ex) {
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText(ex.getMessage());
                a.show();
            }
        }
    }

    @FXML
    private void btnSaveClick() {
        try {
            HomeData.save(this.model, "smarthome.csv");
        } catch (IOException ex) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText(ex.getMessage());
            a.show();

        }
    }

    public static interface NewDeviceCommand {
        public Device<?> execute(String deviceName);
    }

}

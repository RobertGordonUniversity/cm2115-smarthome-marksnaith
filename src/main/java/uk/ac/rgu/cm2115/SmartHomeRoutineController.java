package uk.ac.rgu.cm2115;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import uk.ac.rgu.cm2115.commands.Command;
import uk.ac.rgu.cm2115.commands.RoutineCommand;

public class SmartHomeRoutineController extends Controller<Home> {

    @FXML
    private ListView<String> lstCommands;

    @FXML
    private ListView<String> lstRoutine;

    @FXML
    private TextField txtRoutineName;

    @FXML
    private TextField txtFilterCommands;

    @Override
    public void setModel(Home model) {
        this.model = model;

        this.lstCommands.getItems().addAll(model.getCommands().keySet());
    }

    @FXML
    /**
     * Method to add the selected command to the routine
     */
    private void btnAddToRoutineClick() {
        String commandName = this.lstCommands.getSelectionModel().getSelectedItem();

        if (commandName != null) {
            this.lstRoutine.getItems().add(commandName);
        }
    }

    @FXML
    /**
     * Method to remove the selected command from the routine
     */
    private void btnRemoveFromRoutineClick() {
        int i = this.lstRoutine.getSelectionModel().getSelectedIndex();

        if (i != -1) {
            this.lstRoutine.getItems().remove(i);
        }
    }

    @FXML
    /**
     * Method to save a routine with the given name, and the specified commands
     * @throws IOException
     */
    private void btnSaveRoutineClick() throws Exception {
        String routineName = this.txtRoutineName.getText();

        if (routineName == null || routineName.equals("")) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Please specify a routine name");
            a.show();
            return;
        }

        int numCommands = this.lstRoutine.getItems().size();

        Command[] commands = new Command[numCommands];

        for (int i = 0; i < numCommands; i++) {
            commands[i] = this.model.getCommand(this.lstRoutine.getItems().get(i));
        }

        this.model.addCommand(routineName, new RoutineCommand(commands));

        MainApp.setScene("SmartHomeMain", this.model);
    }

    @FXML
    /**
     * Method to return to the main scene without saving a routine
     * @throws IOException
     */
    private void btnExitClick() throws Exception {
        MainApp.setScene("SmartHomeMain", this.model);
    }

    @FXML
    /**
     * Method to filter commands when text is entered into the relevant TextField
     */
    private void txtFilterCommandsOnChange() {
        String text = this.txtFilterCommands.getText();

        this.lstCommands.getItems().clear();

        if (text == null || text.equals("")) {
            this.lstCommands.getItems().addAll(this.model.getCommands().keySet());
        } else {
            Set<String> filtered = this.model.getCommands().keySet().stream()
                    .filter((d) -> {
                        String search = text.toLowerCase();
                        return d.toLowerCase().startsWith(search);
                    })
                    .collect(Collectors.toSet());

            this.lstCommands.getItems().addAll(filtered);
        }
    }
}

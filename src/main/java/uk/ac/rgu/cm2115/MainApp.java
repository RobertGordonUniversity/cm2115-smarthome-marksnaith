package uk.ac.rgu.cm2115;

import java.io.IOException;
import java.lang.reflect.Method;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import uk.ac.rgu.cm2115.devices.Device;
import uk.ac.rgu.cm2115.devices.DeviceFactory;
import uk.ac.rgu.cm2115.devices.Light;
import javafx.stage.Stage;
import uk.ac.rgu.cm2115.devices.SmartPlug;
import uk.ac.rgu.cm2115.devices.Thermostat;
import uk.ac.rgu.cm2115.devices.Apple.AppleDeviceFactory;
import uk.ac.rgu.cm2115.devices.amazon.AmazonDeviceFactory;
import uk.ac.rgu.cm2115.devices.diagnostics.DeviceVisitor;
import uk.ac.rgu.cm2115.devices.diagnostics.SimpleDiagnosticVisitor;

/**
 * JavaFX App
 */
public class MainApp extends Application {

    public static void main(String[] args) {

        // JavaFX launch code - we'll get to this later
        launch();
    }

    /* Code below is for JavaFX - we'll get to this later in the module! */

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            scene = new Scene(loadFXML("SmartHomeMain"), 640, 480);
            stage.setScene(scene);
            stage.show();

            DeviceFactory factory = new AmazonDeviceFactory();
            DeviceFactory factory2 = new AppleDeviceFactory();

            Home home = new Home(factory);

            home.addFactory(factory);
            home.addFactory(factory2);

            setScene("SmartHomeMain", home);
        } catch (Exception ex) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Cannot load SmartHomeMain");
            a.show();
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static <T> void setScene(String fxml, T model) throws Exception {

        if (fxml.endsWith(".fxml")) {
            fxml = fxml.replace(".fxml", "");
        }

        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));
        Parent root = loader.load();

        @SuppressWarnings("unchecked")
        Controller<T> controller = (Controller<T>) loader.getController();
        controller.setModel(model);

        scene.setRoot(root);
    }

}
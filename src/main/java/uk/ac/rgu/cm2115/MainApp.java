package uk.ac.rgu.cm2115;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import uk.ac.rgu.cm2115.devices.Light;
import javafx.stage.Stage;
import uk.ac.rgu.cm2115.devices.SmartPlug;
import uk.ac.rgu.cm2115.devices.Thermostat;

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
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("SmartHomeMain"), 640, 480);
        stage.setScene(scene);
        stage.show();

        Home home = new Home();
        
        Light light = new Light("Living room");
        SmartPlug plug = new SmartPlug("Kettle");
        Thermostat thrm = new Thermostat("Whole house");

        home.addDevice(light);
        home.addDevice(plug);
        home.addDevice(thrm);

        setScene("SmartHomeMain", home);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    
    public static <T> void setScene(String fxml, T model) throws IOException{

        if(fxml.endsWith(".fxml")){
            fxml = fxml.replace(".fxml","");
        }

        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));
        Parent root = loader.load();

        @SuppressWarnings("unchecked")
        Controller<T> controller = (Controller<T>)loader.getController();
        controller.setModel(model);

        scene.setRoot(root);
    }


}
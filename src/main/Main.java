package main;

import java.util.Properties;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.core.RoombaState;
import org.apache.log4j.PropertyConfigurator;

public class Main extends Application {

  public static boolean shutdown = false;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {

    // Redirect RoombaJSSC Log4j output to TextArea
    Properties properties = new Properties();
    properties.load(getClass().getResourceAsStream("/main/res/log4j.properties"));
    PropertyConfigurator.configure(properties);

    // Start app
    Parent root = FXMLLoader.load(getClass().getResource("/main/root/Root.fxml"));
    primaryStage.setOnCloseRequest(new CloseRequest());
    primaryStage.setTitle("Rapid Roomba Test Tool - EARLY BUILD");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();

  }

  /*
  * Custom WindowEvent that performs the following actions:
  * 1. Stops ongoing threads
  * 2. Powers down Roomba
  * 3. Exits the application
  * */
  private class CloseRequest implements EventHandler<WindowEvent> {

    @Override
    public void handle(WindowEvent event) {
      shutdown = true;
      if (RoombaState.isConnected()) {
        RoombaState.powerOff();
      }
      Platform.exit();
    }
  }

}

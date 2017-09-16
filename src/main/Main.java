package main;

import java.util.Properties;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.PropertyConfigurator;

public class Main extends Application {

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
    Parent root = FXMLLoader.load(getClass().getResource("/main/ui/root/Root.fxml"));
    primaryStage.setTitle("RRTT - EARLY BUILD");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();

  }

}

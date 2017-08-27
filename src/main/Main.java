package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.PropertyConfigurator;

import java.util.Properties;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream( "/main/res/log4j.properties" ));
        PropertyConfigurator.configure(properties);
        Parent root = FXMLLoader.load(getClass().getResource("/main/ui/root/Root.fxml"));
        primaryStage.setTitle("RRTT - EARLY BUILD");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

package main.ui.popup;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ConnectionManagementStage extends Stage {

  public ConnectionManagementStage(Window window) throws IOException {
    URL url = getClass().getClassLoader().getResource("main/ui/root/ConnectionManagement.fxml");
    assert url != null;
    Parent root = FXMLLoader.load(url);
    setScene(new Scene(root));
    setTitle("Connection Manager");
    initModality(Modality.APPLICATION_MODAL);
    initOwner(window);
    setResizable(false);
  }

}

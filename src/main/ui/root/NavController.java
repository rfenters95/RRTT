package main.ui.root;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NavController {

  @FXML
  private void loadConnectionManager(ActionEvent event) {
    try {
      Stage stage = new Stage();
      URL url = getClass().getClassLoader().getResource("main/ui/root/ConnectionManagement.fxml");
      assert url != null;
      Parent root = FXMLLoader.load(url);
      stage.setScene(new Scene(root));
      stage.setTitle("Connection Manager");
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.initOwner(((Node) event.getSource()).getScene().getWindow());
      stage.showAndWait();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void loadControllers(ActionEvent event) {

  }

}

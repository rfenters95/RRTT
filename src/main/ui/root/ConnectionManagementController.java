package main.ui.root;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.core.Injectable;
import main.core.RoombaStartMode;
import main.core.RoombaState;

public class ConnectionManagementController implements Initializable, Injectable {

  @FXML
  private VBox root;

  @FXML
  private JFXComboBox<String> deviceComboBox;

  @FXML
  private JFXComboBox<RoombaStartMode> modeComboBox;

  @FXML
  private JFXButton connectButton;

  @FXML
  private JFXButton refreshButton;

  private RootController rootController;

  @FXML
  private void connect(ActionEvent event) {
    String port = deviceComboBox.getSelectionModel().getSelectedItem();
    RoombaStartMode startMode = modeComboBox.getSelectionModel().getSelectedItem();
    if (port != null && startMode != null) {
      RoombaState.connect(port, startMode);
    }
    ((Stage) root.getScene().getWindow()).close();
  }

  @Override
  public void inject(RootController rootController) {
    this.rootController = rootController;
  }

  @FXML
  private void refresh(ActionEvent event) {
    deviceComboBox.getItems().clear();
    deviceComboBox.getItems().add("DEBUG");
    deviceComboBox.getItems().addAll(RoombaState.getRoomba().portList());
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    deviceComboBox.getItems().addAll(RoombaState.getRoomba().portList());
    deviceComboBox.getItems().add("DEBUG");
    modeComboBox.getItems().addAll(RoombaStartMode.values());
  }

}

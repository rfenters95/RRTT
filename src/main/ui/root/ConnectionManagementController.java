package main.ui.root;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.core.RoombaJSSCSingleton;
import main.core.RoombaStartMode;

public class ConnectionManagementController implements Initializable {

  @FXML
  private JFXComboBox<String> deviceComboBox;

  @FXML
  private JFXComboBox<RoombaStartMode> modeComboBox;

  @FXML
  private JFXButton connectButton;

  @FXML
  private JFXButton refreshButton;

  @FXML
  private void connect(ActionEvent event) {
    String port = deviceComboBox.getSelectionModel().getSelectedItem();
    RoombaStartMode startMode = modeComboBox.getSelectionModel().getSelectedItem();
    // connect
  }

  @FXML
  private void refresh(ActionEvent event) {
    deviceComboBox.getItems().clear();
    deviceComboBox.getItems().addAll(RoombaJSSCSingleton.getRoombaJSSC().portList());
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    deviceComboBox.getItems().addAll(RoombaJSSCSingleton.getRoombaJSSC().portList());
    modeComboBox.getItems().addAll(RoombaStartMode.values());
  }

}

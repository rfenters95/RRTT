package main.ui.alerts;

import javafx.scene.control.Alert;

public class InvalidSensorAlert extends Alert {

  public InvalidSensorAlert(String header) {
    super(AlertType.INFORMATION);
    setHeaderText(header);
    setContentText("You must select a sensor first!");
    getDialogPane().getScene().getStylesheets().add("main/ui/root/dialog.css");
  }

}

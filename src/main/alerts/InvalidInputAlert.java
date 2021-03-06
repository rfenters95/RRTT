package main.alerts;

import javafx.scene.control.Alert;

public class InvalidInputAlert extends Alert {

  public InvalidInputAlert(String headerText, String contentText) {
    super(AlertType.ERROR);
    setHeaderText(headerText);
    setContentText(contentText);
    getDialogPane().getScene().getStylesheets().add("main/root/dialog.css");
  }

}

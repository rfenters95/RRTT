package main.ui.alerts;

import javafx.scene.control.Alert;

public class NotConnectedAlert extends Alert {

  public NotConnectedAlert() {
    super(AlertType.INFORMATION);
    setHeaderText("Roomba not connected!");
    setContentText("To connect the Roomba use the Connection Management tool!");
    getDialogPane().getScene().getStylesheets().add("main/ui/root/dialog.css");
  }


}

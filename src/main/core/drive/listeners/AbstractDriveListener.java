package main.core.drive.listeners;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import main.core.drive.modes.AbstractDriveMode;

public abstract class AbstractDriveListener implements ChangeListener<String> {

  AbstractDriveMode driveMode;
  AbstractDriveMode.Position position;
  JFXTextField textField;

  AbstractDriveListener(AbstractDriveMode driveMode, AbstractDriveMode.Position position,
      JFXTextField textField) {
    this.driveMode = driveMode;
    this.position = position;
    this.textField = textField;
    this.textField.textProperty().addListener(this);
  }

  boolean hasInValidLength(String value) {
    return !value.matches("\\d{0,5}?");
  }

  private String removeLeadingZeros(String value) {
    return value.replaceFirst("^0+", "");
  }

  private boolean hasLeadingZeros(String value) {
    return value.startsWith("0") && value.length() > 1;
  }

  boolean format(String value) {
    if (value.isEmpty()) {
      textField.setText("0");
      return false;
    }
    if (hasLeadingZeros(value)) {
      textField.setText(removeLeadingZeros(value));
      return false;
    }
    return true;
  }

}

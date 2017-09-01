package main.core.drive.listeners;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import main.core.drive.modes.AbstractDriveMode;

/*
* Drive listeners in general are custom ChangeListeners for the DriveModuleController.
* They check both the value and format for velocity and radius in Roomba drive/driveDirect
* command parameters.
* */
public abstract class AbstractDriveListener implements ChangeListener<String> {

  /* *********************************************
  *
  * Package-private members
  *
  ********************************************** */
  AbstractDriveMode driveMode;
  AbstractDriveMode.Position position;
  JFXTextField textField;

  /*
  * Constructs AbstractDriveListener
  *
  * @param driveMode determines which Roomba Drive command to configure for.
  * @param position determines which parameter given the Drive command to configure for.
  * @param textField reference of TextField of which listener is attached.
  * */
  AbstractDriveListener(AbstractDriveMode driveMode, AbstractDriveMode.Position position,
      JFXTextField textField) {
    this.driveMode = driveMode;
    this.position = position;
    this.textField = textField;
    this.textField.textProperty().addListener(this);
  }

  /*
  * Check input length
  *
  * @param value Extracted TextField input.
  * @return True if value's length is 5 or less.
  * */
  boolean hasInValidLength(String value) {
    return !value.matches("\\d{0,5}?");
  }

  /*
  * @param value Extracted TextField input.
  * @return value without leading zeros
  * */
  private String removeLeadingZeros(String value) {
    return value.replaceFirst("^0+", "");
  }

  /*
  * @param value Extracted TextField input.
  * @return True if value has leading zeros
  * */
  private boolean hasLeadingZeros(String value) {
    return value.startsWith("0") && value.length() > 1;
  }

  /*
  * Consolidates formatting to simplify logic, and sets
  * empty String to zero (valid input).
  *
  * @param value Extracted TextField input.
  * @return True if value should be passed to value validation
  * */
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

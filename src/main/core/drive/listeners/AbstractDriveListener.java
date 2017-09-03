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
  boolean hasValidLength(String value) {
    if (value.startsWith("-")) {
      return value.length() <= 6;
    } else {
      return value.length() <= 5;
    }
  }

  boolean isValidString(String value) {
    return value.equals("-") || value.matches("^-\\d+") || value.matches("^\\d+");
  }

  /*
  * @param value Extracted TextField input.
  * @return value without leading zeros
  * */
  private String removeLeadingZeros(String value) {
    return value.replaceFirst("[0]+", "");
  }

  /*
  * @param value Extracted TextField input.
  * @return True if value has leading zeros
  * */
  private boolean hasLeadingZeros(String value) {
    return (value.startsWith("0") && value.length() > 1) || value.startsWith("-0");
  }

  /*
  * Consolidates formatting to simplify logic, and sets
  * empty String to zero (valid input).
  *
  * @param value Extracted TextField input.
  * @return True if value should be passed to value validation
  * */
  boolean format(String newValue) {
    if (newValue.isEmpty()) {
      textField.setText("0");
      return false; // Prevents ghost change
    }
    if (hasLeadingZeros(newValue)) {
      textField.setText(removeLeadingZeros(newValue));
      return false; // Prevents ghost change
    }
    if (!isValidString(newValue)) {
      newValue = newValue.replaceAll("[^0-9-]", "");
      if (newValue.matches("^-{2,}\\d+") || newValue.matches("^-{2,}")) {
        newValue = newValue.replaceAll("-{2,}", "-");
      }
      textField.setText(newValue);
      return false;
    }
    return true;
  }

}

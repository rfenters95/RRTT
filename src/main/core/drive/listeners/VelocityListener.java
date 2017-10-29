package main.core.drive.listeners;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import main.core.drive.modes.AbstractDriveMode;

/*
* Custom ChangeListener for the DriveModuleController.
* Checks both the value and format for velocity in Roomba drive/driveDirect
* command parameters.
* */
public class VelocityListener extends AbstractDriveListener {

  /*
  * Constructs VelocityListener
  *
  * @param driveMode determines which Roomba Drive command to configure for.
  * @param position determines which parameter given the Drive command to configure for.
  * @param textField reference of TextField of which listener is attached.
  * */
  public VelocityListener(AbstractDriveMode driveMode, AbstractDriveMode.Position position,
      JFXTextField textField) {
    super(driveMode, position, textField);
  }

  /*
  * @param value Extracted TextField input.
  * @return True if value is an acceptable velocity parameter.
  * */
  private boolean hasValidVelocity(String value) {
    if (!value.equals("-")) {
      int velocity = Integer.parseInt(value);
      return (velocity >= -500 && velocity <= 500);
    } else {
      return false;
    }
  }

  @Override
  public void changed(ObservableValue<? extends String> observable, String oldValue,
      String newValue) {

    if (hasValidLength(newValue)) {

      if (format(newValue)) {

        if (isValidString(newValue) && hasValidVelocity(newValue)) {

          if (position == AbstractDriveMode.Position.LEFT) {
            textField.setPromptText(driveMode.getTextField1Prompt());
          } else {
            textField.setPromptText(driveMode.getTextField2Prompt());
          }

          textField.getStylesheets().clear();
          textField.getStylesheets().add("main/modules/main.css");

        } else {
          textField.setPromptText(AbstractDriveMode.getVelocityErrorPrompt());
          textField.getStylesheets().clear();
          textField.getStylesheets().add("main/modules/error.css");
        }

      }

    } else {
      textField.setText(oldValue);
    }

  }

}

package main.core.drive.listeners;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import main.core.drive.modes.AbstractDriveMode;

/*
* Custom ChangeListener for the DriveTabController.
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
    int velocity = Integer.parseInt(value);
    return (velocity >= 0 && velocity <= 500);
  }

  @Override
  public void changed(ObservableValue<? extends String> observable, String oldValue,
      String newValue) {

    if (!hasInValidLength(newValue)) {

      if (format(newValue)) {

        if (hasValidVelocity(newValue)) {

          if (position == AbstractDriveMode.Position.LEFT) {
            textField.setPromptText(driveMode.getTextField1Prompt());
          } else {
            textField.setPromptText(driveMode.getTextField2Prompt());
          }

          textField.getStylesheets().clear();
          textField.getStylesheets().add("main/ui/tabs/main.css");
          driveMode.setEnabled(true);

        } else {
          textField.setPromptText(AbstractDriveMode.getVelocityErrorPrompt());
          textField.getStylesheets().clear();
          textField.getStylesheets().add("main/ui/tabs/error.css");
          driveMode.setEnabled(false);
        }

      }

    } else {
      textField.setText(oldValue);
    }

  }

}

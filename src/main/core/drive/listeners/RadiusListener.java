package main.core.drive.listeners;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import main.core.drive.modes.AbstractDriveMode;

/*
* Custom ChangeListener for the DriveModuleController.
* Checks both the value and format for radius in Roomba drive/driveDirect
* command parameters.
* */
public class RadiusListener extends AbstractDriveListener {

  /*
  * Constructs RadiusListener
  *
  * @param driveMode determines which Roomba Drive command to configure for.
  * @param position determines which parameter given the Drive command to configure for.
  * @param textField reference of TextField of which listener is attached.
  * */
  public RadiusListener(AbstractDriveMode driveMode, AbstractDriveMode.Position position,
      JFXTextField textField) {
    super(driveMode, position, textField);
  }

  /*
  * @param value Extracted TextField input.
  * @return True if value is an acceptable radius parameter.
  * */
  private boolean hasValidRadius(String value) {
    if (!value.equals("-")) {
      int radius = Integer.parseInt(value);
      return ((radius >= -2000 && radius <= 2000) || (radius == 32767 || radius == 32768));
    } else {
      return false;
    }
  }

  @Override
  public void changed(ObservableValue<? extends String> observable, String oldValue,
      String newValue) {

    if (hasValidLength(newValue)) {

      if (format(newValue)) {

        if (isValidString(newValue) && hasValidRadius(newValue)) {
          textField.setPromptText(driveMode.getTextField2Prompt());
          textField.getStylesheets().clear();
          textField.getStylesheets().add("main/ui/modules/main.css");
          driveMode.setEnabled(true);
        } else {
          textField.setPromptText(AbstractDriveMode.getRadiusErrorPrompt());
          textField.getStylesheets().clear();
          textField.getStylesheets().add("main/ui/modules/error.css");
          driveMode.setEnabled(false);
        }

      }

    } else {
      textField.setText(oldValue);
    }

  }

}

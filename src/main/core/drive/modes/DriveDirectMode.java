package main.core.drive.modes;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import main.core.RoombaJSSCSingleton;
import main.core.drive.listeners.VelocityListener;

/*
* Configures DriveModuleController for Roomba DriveDirect commands
* by setting parameter listeners and button actions
* for DriveDirect commands.
* */
public class DriveDirectMode extends AbstractDriveMode {

  public DriveDirectMode() {
    setTextField1Prompt("Velocity (mm/s) - Left");
    setTextField2Prompt("Velocity (mm/s) - Right");
  }

  @Override
  public void move(int input1, int input2) {
    if (isEnabled()) {
      RoombaJSSCSingleton.getRoombaJSSC().driveDirect(input1, input2);
    } else {
      if (!hasValidVelocity(input1)) {
        parameterOneErrorAlert();
      } else {
        parameterTwoErrorAlert();
      }
    }
  }

  @Override
  public void parameterOneErrorAlert() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setHeaderText("Velocity - Left");
    alert.setContentText(getVelocityErrorPrompt());
    alert.show();
  }

  @Override
  public void parameterTwoErrorAlert() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setHeaderText("Velocity - Right");
    alert.setContentText(getVelocityErrorPrompt());
    alert.show();
  }

  @Override
  public void initialize() {

    // Set TextField listeners
    AbstractDriveMode.textField1listener = new VelocityListener(this, Position.LEFT, textField1);
    AbstractDriveMode.textField1.textProperty().addListener(AbstractDriveMode.textField1listener);
    AbstractDriveMode.textField2listener = new VelocityListener(this, Position.RIGHT, textField2);
    AbstractDriveMode.textField2.textProperty().addListener(AbstractDriveMode.textField2listener);

    // Set default text for TextFields
    setDefaultText();
    setDefaultPromptText();

  }

  @Override
  public void swapListener() {

    // Remove text properties from textField1 set by DriveMode.
    AbstractDriveMode.textField1.textProperty()
        .removeListener(AbstractDriveMode.textField1listener);

    // Add text properties to textField1 for DriveDirectMode.
    AbstractDriveMode.textField1listener = new VelocityListener(this, Position.LEFT,
        AbstractDriveMode.textField1);
    AbstractDriveMode.textField1.textProperty().addListener(AbstractDriveMode.textField1listener);

    // Remove text properties from textField2 set by DriveMode.
    AbstractDriveMode.textField2.textProperty()
        .removeListener(AbstractDriveMode.textField2listener);

    // Add text properties to textField2 for DriveDirectMode.
    AbstractDriveMode.textField2listener = new VelocityListener(this, Position.RIGHT,
        AbstractDriveMode.textField2);
    AbstractDriveMode.textField2.textProperty().addListener(AbstractDriveMode.textField2listener);

    // Set default text for TextFields
    setDefaultText();
    setDefaultPromptText();
    setEnabled(true);

  }

  @Override
  public String toString() {
    return "DRIVE_DIRECT";
  }

}

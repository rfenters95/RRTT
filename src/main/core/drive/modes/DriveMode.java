package main.core.drive.modes;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import main.core.RoombaJSSCSingleton;
import main.core.drive.listeners.RadiusListener;
import main.core.drive.listeners.VelocityListener;

/*
* Configures DriveModuleController for Roomba Drive commands
* by setting parameter listeners and button actions
* for DriveDirect commands.
* */
public class DriveMode extends AbstractDriveMode {

  public DriveMode() {
    setTextField1Prompt("Velocity (mm/s)");
    setTextField2Prompt("Radius (mm)");
  }

  @Override
  public void move(int input1, int input2) {
    if (isEnabled()) {
      RoombaJSSCSingleton.getRoombaJSSC().drive(input1, input2);
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
    alert.setHeaderText("Velocity");
    alert.setContentText("Invalid Input! Range [0, 500]");
    alert.show();
  }

  @Override
  public void parameterTwoErrorAlert() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setHeaderText("Radius");
    alert.setContentText("Invalid Input! Range [0, 2000]");
    alert.show();
  }

  @Override
  public void initialize() {

    // Set TextField listeners
    AbstractDriveMode.textField1listener = new VelocityListener(this, Position.LEFT, textField1);
    AbstractDriveMode.textField1.textProperty().addListener(AbstractDriveMode.textField1listener);
    AbstractDriveMode.textField2listener = new RadiusListener(this, Position.RIGHT, textField2);
    AbstractDriveMode.textField2.textProperty().addListener(AbstractDriveMode.textField2listener);

    // Set default text for TextFields
    setDefaultText();
    setDefaultPromptText();

  }

  @Override
  public void swapListener() {

    // Remove text properties from textField1 set by DriveDirectMode.
    AbstractDriveMode.textField1.textProperty()
        .removeListener(AbstractDriveMode.textField1listener);

    // Add text properties to textField1 for DriveMode.
    AbstractDriveMode.textField1listener = new VelocityListener(this, Position.LEFT,
        AbstractDriveMode.textField1);
    AbstractDriveMode.textField1.textProperty().addListener(AbstractDriveMode.textField1listener);

    // Remove text properties from textField2 set by DriveDirectMode.
    AbstractDriveMode.textField2.textProperty()
        .removeListener(AbstractDriveMode.textField2listener);

    // Add text properties to textField2 for DriveMode.
    AbstractDriveMode.textField2listener = new RadiusListener(this, Position.RIGHT,
        AbstractDriveMode.textField2);
    AbstractDriveMode.textField2.textProperty().addListener(AbstractDriveMode.textField2listener);

    // Set default text for TextFields
    setDefaultText();
    setDefaultPromptText();
    setEnabled(true);

  }

  @Override
  public String toString() {
    return "DRIVE";
  }
}

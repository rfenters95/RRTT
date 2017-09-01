package main.core.drive.modes;

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
    RoombaJSSCSingleton.getRoombaJSSC().driveDirect(input1, input2);
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

    /*
    * Remove text properties from textField1 set by DriveMode, and add
    * text properties for DriveDirectMode
    * */
    AbstractDriveMode.textField1.textProperty()
        .removeListener(AbstractDriveMode.textField1listener);
    AbstractDriveMode.textField1listener = new VelocityListener(this, Position.LEFT,
        AbstractDriveMode.textField1);
    AbstractDriveMode.textField1.textProperty().addListener(AbstractDriveMode.textField1listener);

    /*
    * Remove text properties from textField1 set by DriveMode, and add
    * text properties for DriveDirectMode
    * */
    AbstractDriveMode.textField2.textProperty()
        .removeListener(AbstractDriveMode.textField2listener);
    AbstractDriveMode.textField2listener = new VelocityListener(this, Position.RIGHT,
        AbstractDriveMode.textField2);
    AbstractDriveMode.textField2.textProperty().addListener(AbstractDriveMode.textField2listener);

    // Set default text for TextFields
    setDefaultText();
    setDefaultPromptText();

  }

  @Override
  public String toString() {
    return "DRIVE_DIRECT";
  }

}

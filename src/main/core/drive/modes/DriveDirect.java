package main.core.drive.modes;

import javafx.scene.control.ContextMenu;
import main.alerts.InvalidInputAlert;
import main.core.RoombaState;
import main.core.drive.listeners.VelocityListener;
import main.core.menu.VelocityMenu;

/*
* Configures DriveModuleController for Roomba DriveDirect commands
* by setting parameter listeners and button actions
* for DriveDirect commands.
* */
public class DriveDirect extends AbstractDriveMode {

  public DriveDirect() {
    setTextField1Prompt("Velocity (mm/s) - Right");
    setTextField2Prompt("Velocity (mm/s) - Left");
  }

  @Override
  public boolean isInput1Valid() {
    return hasValidVelocity(getTextField1Input());
  }

  @Override
  public boolean isInput2Valid() {
    return hasValidVelocity(getTextField2Input());
  }

  @Override
  public void move() {
    RoombaState.getRoomba().driveDirect(getTextField1Input(), getTextField2Input());
  }

  @Override
  public void stop() {
    RoombaState.getRoomba().driveDirect(0, 0);
  }

  @Override
  public void parameterOneErrorAlert() {
    InvalidInputAlert invalidInputAlert = new InvalidInputAlert("Velocity - Right",
        getVelocityErrorPrompt());
    invalidInputAlert.show();
  }

  @Override
  public void parameterTwoErrorAlert() {
    InvalidInputAlert invalidInputAlert = new InvalidInputAlert("Velocity - Left",
        getVelocityErrorPrompt());
    invalidInputAlert.show();
  }

  @Override
  public void initialize() {

    // Set TextField listeners
    textField1listener = new VelocityListener(this, Position.LEFT, textField1);
    textField1.textProperty().addListener(textField1listener);
    textField2listener = new VelocityListener(this, Position.RIGHT, textField2);
    textField2.textProperty().addListener(textField2listener);

    // Set default text for TextFields
    setDefaultText();
    setDefaultPromptText();

    ContextMenu velocityMenu1 = new VelocityMenu(textField1);
    textField1.setContextMenu(velocityMenu1);

    ContextMenu velocityMenu2 = new VelocityMenu(textField2);
    textField2.setContextMenu(velocityMenu2);

  }

  @Override
  public void swapListener() {

    // Remove text properties from textField1 set by Drive.
    textField1.textProperty().removeListener(textField1listener);

    // Add text properties to textField1 for DriveDirect.
    textField1listener = new VelocityListener(this, Position.LEFT, textField1);
    textField1.textProperty().addListener(textField1listener);

    // Remove text properties from textField2 set by Drive.
    textField2.textProperty().removeListener(textField2listener);

    // Add text properties to textField2 for DriveDirect.
    textField2listener = new VelocityListener(this, Position.RIGHT, textField2);
    textField2.textProperty().addListener(textField2listener);

    // Set default text for TextFields
    setDefaultText();
    setDefaultPromptText();

    ContextMenu velocityMenu1 = new VelocityMenu(textField1);
    textField1.setContextMenu(velocityMenu1);

    ContextMenu velocityMenu2 = new VelocityMenu(textField2);
    textField2.setContextMenu(velocityMenu2);

  }

}

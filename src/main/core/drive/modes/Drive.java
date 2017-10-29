package main.core.drive.modes;

import javafx.scene.control.ContextMenu;
import main.core.RoombaState;
import main.core.drive.listeners.RadiusListener;
import main.core.drive.listeners.VelocityListener;
import main.core.menu.RadiusMenu;
import main.core.menu.VelocityMenu;
import main.ui.alerts.InvalidInputAlert;

/*
* Configures DriveModuleController for Roomba Drive commands
* by setting parameter listeners and button actions
* for DriveDirect commands.
* */
public class Drive extends AbstractDriveMode {

  public Drive() {
    setTextField1Prompt("Velocity (mm/s)");
    setTextField2Prompt("Radius (mm)");
  }

  @Override
  public boolean isInput1Valid() {
    return hasValidVelocity(getTextField1Input());
  }

  @Override
  public boolean isInput2Valid() {
    return hasValidRadius(getTextField2Input());
  }

  @Override
  public void move() {
    RoombaState.getRoomba().drive(getTextField1Input(), getTextField2Input());
  }

  @Override
  public void stop() {
    RoombaState.getRoomba().drive(0, 0);
  }

  @Override
  public void parameterOneErrorAlert() {
    InvalidInputAlert invalidInputAlert = new InvalidInputAlert("Velocity",
        getVelocityErrorPrompt());
    invalidInputAlert.show();
  }

  @Override
  public void parameterTwoErrorAlert() {
    InvalidInputAlert invalidInputAlert = new InvalidInputAlert("Radius", getRadiusErrorPrompt());
    invalidInputAlert.show();
  }

  @Override
  public void initialize() {

    // Set TextField listeners
    textField1listener = new VelocityListener(this, Position.LEFT, textField1);
    textField1.textProperty().addListener(textField1listener);
    textField2listener = new RadiusListener(this, Position.RIGHT, textField2);
    textField2.textProperty().addListener(textField2listener);

    // Set default text for TextFields
    setDefaultText();
    setDefaultPromptText();

    ContextMenu velocityMenu = new VelocityMenu(textField1);
    textField1.setContextMenu(velocityMenu);

    ContextMenu radiusMenu = new RadiusMenu(textField2);
    textField2.setContextMenu(radiusMenu);

  }

  @Override
  public void swapListener() {

    // Remove text properties from textField1 set by DriveDirect.
    textField1.textProperty().removeListener(textField1listener);

    // Add text properties to textField1 for Drive.
    textField1listener = new VelocityListener(this, Position.LEFT, textField1);
    textField1.textProperty().addListener(textField1listener);

    // Remove text properties from textField2 set by DriveDirect.
    textField2.textProperty().removeListener(textField2listener);

    // Add text properties to textField2 for Drive.
    textField2listener = new RadiusListener(this, Position.RIGHT, textField2);
    textField2.textProperty().addListener(textField2listener);

    // Set default text for TextFields
    setDefaultText();
    setDefaultPromptText();

    ContextMenu velocityMenu = new VelocityMenu(textField1);
    textField1.setContextMenu(velocityMenu);

    ContextMenu radiusMenu = new RadiusMenu(textField2);
    textField2.setContextMenu(radiusMenu);

  }

}

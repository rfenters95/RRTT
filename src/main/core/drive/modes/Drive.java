package main.core.drive.modes;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import main.core.RoombaJSSCSingleton;
import main.core.drive.listeners.RadiusListener;
import main.core.drive.listeners.VelocityListener;
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
  public boolean move(int input1, int input2) {
    if (isEnabled()) {
      RoombaJSSCSingleton.getRoombaJSSC().drive(input1, input2);
      return true;
    } else {
      if (!hasValidVelocity(input1)) {
        parameterOneErrorAlert();
      } else {
        parameterTwoErrorAlert();
      }
      return false;
    }
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

    final ContextMenu contextMenuTextField1 = new ContextMenu();
    final MenuItem maxVelocityTextField1 = new MenuItem("Special: Max Forward");
    maxVelocityTextField1.setOnAction(e -> textField1.setText("500"));
    final MenuItem fullStopTextField1 = new MenuItem("Special: Full Stop");
    fullStopTextField1.setOnAction(e -> textField1.setText("0"));
    final MenuItem minVelocityTextField1 = new MenuItem("Special: Max Reverse");
    minVelocityTextField1.setOnAction(e -> textField1.setText("-500"));
    contextMenuTextField1.getItems().add(maxVelocityTextField1);
    contextMenuTextField1.getItems().add(fullStopTextField1);
    contextMenuTextField1.getItems().add(minVelocityTextField1);
    textField1.setContextMenu(contextMenuTextField1);

    final ContextMenu contextMenuTextField2 = new ContextMenu();
    final MenuItem specialStraightTextField2 = new MenuItem("Special: Straight");
    specialStraightTextField2.setOnAction(e -> textField2.setText("32768"));
    final MenuItem specialClockwiseTextField2 = new MenuItem("Special: Clockwise");
    specialClockwiseTextField2.setOnAction(e -> textField2.setText("-1"));
    final MenuItem specialCounterClockwiseTextField2 = new MenuItem("Special: Counter Clockwise");
    specialCounterClockwiseTextField2.setOnAction(e -> textField2.setText("1"));
    contextMenuTextField2.getItems().add(specialStraightTextField2);
    contextMenuTextField2.getItems().add(specialClockwiseTextField2);
    contextMenuTextField2.getItems().add(specialCounterClockwiseTextField2);
    textField2.setContextMenu(contextMenuTextField2);

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
    setEnabled(true);

    final ContextMenu contextMenuTextField1 = new ContextMenu();
    final MenuItem maxVelocityTextField1 = new MenuItem("Special: Max Forward");
    maxVelocityTextField1.setOnAction(e -> textField1.setText("500"));
    final MenuItem fullStopTextField1 = new MenuItem("Special: Full Stop");
    fullStopTextField1.setOnAction(e -> textField1.setText("0"));
    final MenuItem minVelocityTextField1 = new MenuItem("Special: Max Reverse");
    minVelocityTextField1.setOnAction(e -> textField1.setText("-500"));
    contextMenuTextField1.getItems().add(maxVelocityTextField1);
    contextMenuTextField1.getItems().add(fullStopTextField1);
    contextMenuTextField1.getItems().add(minVelocityTextField1);
    textField1.setContextMenu(contextMenuTextField1);

    final ContextMenu contextMenuTextField2 = new ContextMenu();
    final MenuItem specialStraightTextField2 = new MenuItem("Special: Straight");
    specialStraightTextField2.setOnAction(e -> textField2.setText("32768"));
    final MenuItem specialClockwiseTextField2 = new MenuItem("Special: Clockwise");
    specialClockwiseTextField2.setOnAction(e -> textField2.setText("-1"));
    final MenuItem specialCounterClockwiseTextField2 = new MenuItem("Special: Counter Clockwise");
    specialCounterClockwiseTextField2.setOnAction(e -> textField2.setText("1"));
    contextMenuTextField2.getItems().add(specialStraightTextField2);
    contextMenuTextField2.getItems().add(specialClockwiseTextField2);
    contextMenuTextField2.getItems().add(specialCounterClockwiseTextField2);
    textField2.setContextMenu(contextMenuTextField2);

  }

}

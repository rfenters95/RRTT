package main.core.drive.modes;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
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
    alert.setContentText(getVelocityErrorPrompt());
    alert.show();
  }

  @Override
  public void parameterTwoErrorAlert() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setHeaderText("Radius");
    alert.setContentText(getRadiusErrorPrompt());
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

    ContextMenu contextMenuTextField1 = new ContextMenu();

    MenuItem maxVelocityTextField1 = new MenuItem("Max Velocity");
    maxVelocityTextField1.setOnAction(e -> textField1.setText("500"));

    MenuItem minVelocityTextField1 = new MenuItem("Min Velocity");
    minVelocityTextField1.setOnAction(e -> textField1.setText("-500"));

    contextMenuTextField1.getItems().add(maxVelocityTextField1);
    contextMenuTextField1.getItems().add(minVelocityTextField1);
    textField1.setContextMenu(contextMenuTextField1);

    ContextMenu contextMenuTextField2 = new ContextMenu();

    MenuItem specialStraightTextField2 = new MenuItem("Special: Straight");
    specialStraightTextField2.setOnAction(e -> textField2.setText("32768"));

    MenuItem specialClockwiseTextField2 = new MenuItem("Special: Clockwise");
    specialClockwiseTextField2.setOnAction(e -> textField2.setText("-1"));

    MenuItem specialCounterClockwiseTextField2 = new MenuItem("Special: Counter Clockwise");
    specialCounterClockwiseTextField2.setOnAction(e -> textField2.setText("1"));

    contextMenuTextField2.getItems().add(specialStraightTextField2);
    contextMenuTextField2.getItems().add(specialClockwiseTextField2);
    contextMenuTextField2.getItems().add(specialCounterClockwiseTextField2);
    textField2.setContextMenu(contextMenuTextField2);

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

    ContextMenu contextMenuTextField1 = new ContextMenu();

    MenuItem maxVelocityTextField1 = new MenuItem("Max Velocity");
    maxVelocityTextField1.setOnAction(e -> textField1.setText("500"));

    MenuItem minVelocityTextField1 = new MenuItem("Min Velocity");
    minVelocityTextField1.setOnAction(e -> textField1.setText("-500"));

    contextMenuTextField1.getItems().add(maxVelocityTextField1);
    contextMenuTextField1.getItems().add(minVelocityTextField1);
    textField1.setContextMenu(contextMenuTextField1);

    ContextMenu contextMenuTextField2 = new ContextMenu();

    MenuItem specialStraightTextField2 = new MenuItem("Special: Straight");
    specialStraightTextField2.setOnAction(e -> textField2.setText("32768"));

    MenuItem specialClockwiseTextField2 = new MenuItem("Special: Clockwise");
    specialClockwiseTextField2.setOnAction(e -> textField2.setText("-1"));

    MenuItem specialCounterClockwiseTextField2 = new MenuItem("Special: Counter Clockwise");
    specialCounterClockwiseTextField2.setOnAction(e -> textField2.setText("1"));

    contextMenuTextField2.getItems().add(specialStraightTextField2);
    contextMenuTextField2.getItems().add(specialClockwiseTextField2);
    contextMenuTextField2.getItems().add(specialCounterClockwiseTextField2);
    textField2.setContextMenu(contextMenuTextField2);

  }

  @Override
  public String toString() {
    return "DRIVE";
  }
}

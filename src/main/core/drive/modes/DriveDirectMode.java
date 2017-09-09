package main.core.drive.modes;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuItem;
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
    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.getScene().getStylesheets().add("main/ui/root/dialog.css");
    alert.setHeaderText("Velocity - Left");
    alert.setContentText(getVelocityErrorPrompt());
    alert.show();
  }

  @Override
  public void parameterTwoErrorAlert() {
    Alert alert = new Alert(AlertType.ERROR);
    DialogPane dialogPane = alert.getDialogPane();
    dialogPane.getScene().getStylesheets().add("main/ui/root/dialog.css");
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
    final MenuItem maxVelocityTextField2 = new MenuItem("Special: Max Forward");
    maxVelocityTextField2.setOnAction(e -> textField2.setText("500"));
    final MenuItem fullStopTextField2 = new MenuItem("Special: Full Stop");
    fullStopTextField2.setOnAction(e -> textField2.setText("0"));
    final MenuItem minVelocityTextField2 = new MenuItem("Special: Max Reverse");
    minVelocityTextField2.setOnAction(e -> textField2.setText("-500"));
    contextMenuTextField2.getItems().add(maxVelocityTextField2);
    contextMenuTextField2.getItems().add(fullStopTextField2);
    contextMenuTextField2.getItems().add(minVelocityTextField2);
    textField2.setContextMenu(contextMenuTextField2);

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
    final MenuItem maxVelocityTextField2 = new MenuItem("Special: Max Forward");
    maxVelocityTextField2.setOnAction(e -> textField2.setText("500"));
    final MenuItem fullStopTextField2 = new MenuItem("Special: Full Stop");
    fullStopTextField2.setOnAction(e -> textField2.setText("0"));
    final MenuItem minVelocityTextField2 = new MenuItem("Special: Max Reverse");
    minVelocityTextField2.setOnAction(e -> textField2.setText("-500"));
    contextMenuTextField2.getItems().add(maxVelocityTextField2);
    contextMenuTextField2.getItems().add(fullStopTextField2);
    contextMenuTextField2.getItems().add(minVelocityTextField2);
    textField2.setContextMenu(contextMenuTextField2);

  }

  @Override
  public String toString() {
    return "DRIVE_DIRECT";
  }

}

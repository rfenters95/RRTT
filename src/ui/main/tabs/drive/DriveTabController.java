package ui.main.tabs.drive;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import core.Injectable;
import core.RoombaDriveMode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ui.RootController;

import java.net.URL;
import java.util.ResourceBundle;

public class DriveTabController implements Initializable, Injectable {

    private RootController rootController;

    @FXML
    private AnchorPane driveTab;

    @FXML
    private JFXComboBox<RoombaDriveMode> driveModeComboBox;

    @FXML
    private JFXTextField input1TextField;

    @FXML
    private JFXTextField input2TextField;

    @FXML
    private VBox checkBoxVBox;

    @FXML
    private JFXButton forwardButton;

    @FXML
    private JFXButton rotateLeftButton;

    @FXML
    private JFXButton stopButton;

    @FXML
    private JFXButton rotateRightButton;

    @FXML
    private JFXButton reverseButton;

    private boolean isValid() {

        RoombaDriveMode driveMode = driveModeComboBox.getSelectionModel().getSelectedItem();
        boolean hasEmptyFields = input1TextField.getText().isEmpty() || input2TextField.getText().isEmpty();
        if (hasEmptyFields) return false;

        input1TextField.setText(input1TextField.getText().replaceFirst("^0+(?!$)", ""));
        input2TextField.setText(input2TextField.getText().replaceFirst("^0+(?!$)", ""));

        if (driveMode == RoombaDriveMode.DRIVE) {
            int velocity = Integer.parseInt(input1TextField.getText());
            int radius = Integer.parseInt(input2TextField.getText());
            boolean isValid;
            isValid = velocity >= 0 && velocity <= 500;
            isValid = isValid && radius >= 0 && radius <= 2000;
            return isValid;
        }
        if (driveMode == RoombaDriveMode.DRIVE_DIRECT) {
            int velocityLeft = Integer.parseInt(input1TextField.getText());
            int velocityRight = Integer.parseInt(input2TextField.getText());
            boolean isValid;
            isValid = velocityLeft >= 0 && velocityLeft <= 500;
            isValid = isValid && velocityRight >= 0 && velocityRight <= 500;
            return isValid;
        }

        return false;

    }

    private void resetText(RoombaDriveMode driveMode) {

        if (driveMode == RoombaDriveMode.DRIVE) {
            input1TextField.setPromptText("Velocity (mm/s)");
            input2TextField.setPromptText("Radius (mm)");
        } else if (driveMode == RoombaDriveMode.DRIVE_DIRECT) {
            input1TextField.setPromptText("Velocity (mm/s) - Left");
            input2TextField.setPromptText("Velocity (mm/s) - Right");
        }

        input1TextField.setText("");
        input2TextField.setText("");

    }

    @Override
    public void inject(RootController rootController) {
        this.rootController = rootController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /* ********************************************
        *
        * ComboBox logic
        *
        ********************************************* */

        driveModeComboBox.getItems().setAll(RoombaDriveMode.values());
        driveModeComboBox.setOnAction(e -> {
            RoombaDriveMode driveMode = driveModeComboBox.getSelectionModel().getSelectedItem();
            if (driveMode == RoombaDriveMode.DRIVE) {
                rootController.console.appendText("Starting drive...\n");
                resetText(driveMode);
            } else if (driveMode == RoombaDriveMode.DRIVE_DIRECT) {
                rootController.console.appendText("Starting drive direct...\n");
                resetText(driveMode);
            }
        });

        /* ********************************************
        *
        * TextField logic
        *
        ********************************************* */

        input1TextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,4}?")) {
                input1TextField.setText(oldValue);
            }
        });

        input2TextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,4}?")) {
                input2TextField.setText(oldValue);
            }
        });

        /* ********************************************
        *
        * Button logic
        *
        ********************************************* */

        //TODO perform a input value check; 0 - 500 (mm/s); 0 - 2000 (mm);

        forwardButton.setOnAction(e -> {
            if (isValid()) {
                rootController.console.appendText("Test!\n");
            } else {
                rootController.console.appendText("Invalid input!\n");
            }
        });

        reverseButton.setOnAction(e -> {
            rootController.console.appendText("Test!\n");
            // RoombaJSSCSingleton.getRoombaJSSC().stop();
        });

        stopButton.setOnAction(e -> {
            rootController.console.appendText("Test!\n");
            // RoombaJSSCSingleton.getRoombaJSSC().stop();
        });

        rotateLeftButton.setOnAction(e -> {
            rootController.console.appendText("Test!\n");
            // RoombaJSSCSingleton.getRoombaJSSC().stop();
        });

        rotateRightButton.setOnAction(e -> {
            rootController.console.appendText("Test!\n");
            // RoombaJSSCSingleton.getRoombaJSSC().stop();
        });

    }

}

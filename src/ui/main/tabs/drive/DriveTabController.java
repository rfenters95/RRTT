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
    private JFXTextField textField1;

    @FXML
    private JFXTextField textField2;

    @FXML
    private VBox checkBoxVBox; //TODO add preset tests

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

    @Override
    public void inject(RootController rootController) {
        this.rootController = rootController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /* *********************************************
        *
        * ComboBox logic
        *
        ********************************************** */

        driveModeComboBox.getItems().setAll(RoombaDriveMode.values());
        driveModeComboBox.setOnAction(e -> {
            RoombaDriveMode driveMode = driveModeComboBox.getSelectionModel().getSelectedItem();
            driveMode.configTextFields(textField1, textField2);
        });

        /* *********************************************
        *
        * TextField logic
        *
        ********************************************** */

        //TODO simplify and clean up; Consider custom components;

        textField1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,4}?")) {
                textField1.setText(oldValue);
            } else {
                if (!textField1.getText().isEmpty()) {
                    int velocity = Integer.parseInt(textField1.getText());
                    if (velocity < 0 || velocity > 500) {
                        textField1.setText(oldValue);
                    }
                }
            }
        });

        textField2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,4}?")) {
                textField2.setText(oldValue);
            } else {
                RoombaDriveMode driveMode = driveModeComboBox.getSelectionModel().getSelectedItem();
                switch (driveMode) {
                    case DRIVE:
                        int radius = Integer.parseInt(textField2.getText());
                        if (radius < 0 || (radius > 2000 && radius != 32768 && radius != 32767)) {
                            textField2.setText(oldValue);
                        }
                        break;
                    case DRIVE_DIRECT:
                        int velocity = Integer.parseInt(textField2.getText());
                        if (velocity < 0 || velocity > 500) {
                            textField2.setText(oldValue);
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        /* *********************************************
        *
        * Button logic
        *
        ********************************************** */

        forwardButton.setOnAction(e -> {
            RoombaDriveMode driveMode = driveModeComboBox.getSelectionModel().getSelectedItem();
            if (driveMode.hasValidParameters(textField1, textField2)) {
                rootController.console.appendText("valid Test!\n");
                //Get roombaJSSC
                //Convert input to int
                //Switch
                //Send drive instructions
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

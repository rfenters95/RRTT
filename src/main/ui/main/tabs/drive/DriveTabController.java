package main.ui.main.tabs.drive;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.maschel.roomba.RoombaJSSC;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.core.Injectable;
import main.core.RoombaDriveMode;
import main.core.RoombaJSSCSingleton;
import main.ui.RootController;

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
        driveModeComboBox.getSelectionModel().selectFirst();
        driveModeComboBox.getSelectionModel().getSelectedItem().configTextFields(textField1, textField2);

        /* *********************************************
        *
        * TextField logic
        *
        ********************************************** */

        textField1.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d{0,5}?")) {

                textField1.setText(oldValue); // triggers another check

            } else {

                RoombaDriveMode roombaDriveMode = driveModeComboBox.getSelectionModel().getSelectedItem();

                if (roombaDriveMode.isInput1Valid(textField1)) {

                    roombaDriveMode.resetStyle(RoombaDriveMode.TextFieldPosition.ONE, textField1);
                    rootController.console.appendText("Valid\n");

                } else {

                    roombaDriveMode.setErrorStyle(RoombaDriveMode.TextFieldPosition.ONE, textField1);

                }

            }

        });

        textField2.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d{0,5}?")) {

                textField2.setText(oldValue);

            } else {

                RoombaDriveMode roombaDriveMode = driveModeComboBox.getSelectionModel().getSelectedItem();

                if (roombaDriveMode.isInput2Valid(textField2)) {

                    roombaDriveMode.resetStyle(RoombaDriveMode.TextFieldPosition.TWO, textField2);
                    rootController.console.appendText("Valid\n");

                } else {

                    roombaDriveMode.setErrorStyle(RoombaDriveMode.TextFieldPosition.TWO, textField2);

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
            if (driveMode.isInputsValid(textField1, textField2)) {
                //Get roombaJSSC
                RoombaJSSC roombaJSSC = RoombaJSSCSingleton.getRoombaJSSC();
                //Convert input to int
                int input1 = Integer.parseInt(textField1.getText());
                int input2 = Integer.parseInt(textField2.getText());
                //Switch
                //roombaJSSC.drive(input1, input2);
                //Send drive instructions
                rootController.console.appendText(RoombaJSSCSingleton.logDate() + "\n");
            } else {
                rootController.console.appendText("Invalid input!\n");
            }
        });

        reverseButton.setOnAction(e -> {
            rootController.console.appendText("Test!\n");
        });

        stopButton.setOnAction(e -> {
            rootController.console.appendText("Test!\n");
        });

        rotateLeftButton.setOnAction(e -> {
            rootController.console.appendText("Test!\n");
        });

        rotateRightButton.setOnAction(e -> {
            rootController.console.appendText("Test!\n");
        });

    }

}

package main.ui.tabs.drive;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.core.Injectable;
import main.core.drive.modes.AbstractDriveMode;
import main.core.drive.modes.DirectDriveMode;
import main.core.drive.modes.DriveMode;
import main.ui.root.RootController;

import java.net.URL;
import java.util.ResourceBundle;

public class DriveTabController implements Initializable, Injectable {

    private RootController rootController;

    @FXML
    private AnchorPane driveTab;

    @FXML
    private JFXComboBox<AbstractDriveMode> driveModeComboBox;

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

    @FXML
    void forward(ActionEvent event) {

    }

    @FXML
    void reverse(ActionEvent event) {

    }

    @FXML
    void rotateLeft(ActionEvent event) {

    }

    @FXML
    void rotateRight(ActionEvent event) {

    }

    @FXML
    void stop(ActionEvent event) {

    }

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

        AbstractDriveMode.setTextFields(textField1, textField2);
        AbstractDriveMode driveMode = new DriveMode();
        AbstractDriveMode directDriveMode = new DirectDriveMode();

        driveModeComboBox.getItems().setAll(driveMode, directDriveMode);
        driveModeComboBox.setOnAction(e -> {
            AbstractDriveMode mode = driveModeComboBox.getSelectionModel().getSelectedItem();
            mode.swapListener();
        });
        driveModeComboBox.getSelectionModel().selectFirst();
        driveModeComboBox.getSelectionModel().getSelectedItem().initialize();

        /* *********************************************
        *
        * Button logic
        *
        ********************************************** */

        forwardButton.setOnAction(e -> {
            rootController.console.appendText("Test!\n");
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

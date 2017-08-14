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

    @Override
    public void inject(RootController rootController) {
        this.rootController = rootController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        driveModeComboBox.getItems().setAll(RoombaDriveMode.values());
        driveModeComboBox.setOnAction(e -> {
            RoombaDriveMode driveMode = driveModeComboBox.getSelectionModel().getSelectedItem();
            if (driveMode == RoombaDriveMode.DRIVE) {
                rootController.console.appendText("Starting drive...\n");
                //TODO change TextField text
            } else if (driveMode == RoombaDriveMode.DRIVE_DIRECT) {
                rootController.console.appendText("Starting drive direct...\n");
                //TODO change TextField text
            }
        });

        stopButton.setOnAction(e -> {
            rootController.console.appendText("Test!\n");
            // RoombaJSSCSingleton.getRoombaJSSC().stop();
        });
    }

}

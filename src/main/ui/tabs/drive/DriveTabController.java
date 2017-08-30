package main.ui.tabs.drive;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.core.Injectable;
import main.core.drive.modes.AbstractDriveMode;
import main.core.drive.modes.DriveDirectMode;
import main.core.drive.modes.DriveMode;
import main.ui.root.RootController;

public class DriveTabController implements Initializable, Injectable {

    /* *********************************************
    *
    * FXML fields
    *
    ********************************************** */

  @FXML
  private AnchorPane driveTab;

  @FXML
  private JFXComboBox<AbstractDriveMode> driveModeComboBox;

  @FXML
  private JFXTextField textField1;

  @FXML
  private JFXTextField textField2;

    /* *********************************************
    *
    * Instance fields
    *
    ********************************************** */

  private AbstractDriveMode mode;
  private RootController rootController;

    /* *********************************************
    *
    * Instance methods
    *
    ********************************************** */

  private int getParsedInteger(JFXTextField textField) {
    return Integer.parseInt(textField.getText());
  }

    /* *********************************************
    *
    * FXML methods
    *
    ********************************************** */

  //TODO implement custom abstract drive methods in AbstractDriveMode

  @FXML
  void forward(ActionEvent event) {
    mode.forward(getParsedInteger(textField1), getParsedInteger(textField2));
  }

  @FXML
  void reverse(ActionEvent event) {
    mode.reverse(getParsedInteger(textField1), getParsedInteger(textField2));
  }

  @FXML
  void rotateLeft(ActionEvent event) {
    mode.rotateLeft(getParsedInteger(textField1), getParsedInteger(textField2));
  }

  @FXML
  void rotateRight(ActionEvent event) {
    mode.rotateRight(getParsedInteger(textField1), getParsedInteger(textField2));
  }

  @FXML
  void stop(ActionEvent event) {
    mode.stop();
  }

    /* *********************************************
    *
    * Initialization methods
    *
    ********************************************** */

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
    AbstractDriveMode driveDirectMode = new DriveDirectMode();

    driveModeComboBox.getItems().setAll(driveMode, driveDirectMode);
    driveModeComboBox.setOnAction(e -> {
      mode = driveModeComboBox.getSelectionModel().getSelectedItem();
      mode.swapListener();
    });
    driveModeComboBox.getSelectionModel().selectFirst();
    driveModeComboBox.getSelectionModel().getSelectedItem().initialize();
    mode = driveModeComboBox.getSelectionModel().getSelectedItem();

  }

}

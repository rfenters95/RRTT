package main.ui.modules.drive;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import main.core.Injectable;
import main.core.drive.modes.AbstractDriveMode;
import main.core.drive.modes.DriveDirectMode;
import main.core.drive.modes.DriveMode;
import main.ui.root.RootController;

/*
* Manages interaction between the user and the Roomba.
* DriveModule allows users to set parameters for the various
* Roomba Drive commands and send those commands to the Roomba.
* */
public class DriveModuleController implements Initializable, Injectable {

  /* *********************************************
  *
  * FXML members
  *
  ********************************************** */

  @FXML
  private VBox driveModule;

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
  * FXML methods
  *
  ********************************************** */

  //TODO implement custom abstract drive methods in AbstractDriveMode

  @FXML
  void start(ActionEvent event) {

    Integer input1 = null;
    Integer input2 = null;

    try {
      input1 = AbstractDriveMode.getTextField1Input();
      input2 = AbstractDriveMode.getTextField2Input();
      mode.move(input1, input2);
    } catch (NumberFormatException e) {
      if (input1 == null) {
        mode.parameterOneErrorAlert();
      } else if (input2 == null) {
        mode.parameterTwoErrorAlert();
      }
    }

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

    driveModule.setStyle("-fx-background-color: #47494c; -fx-background-radius: 10;");

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

package main.ui.modules.drive;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import main.core.Injectable;
import main.core.RoombaJSSCSingleton;
import main.core.drive.modes.AbstractDriveMode;
import main.core.drive.modes.Drive;
import main.core.drive.modes.DriveDirect;
import main.ui.alerts.NotConnectedAlert;
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

  @FXML
  private JFXButton toggle;

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

  private boolean hasStarted = false;

  @FXML
  void start(ActionEvent event) {

    if (RoombaJSSCSingleton.isConnected()) {

      if (!hasStarted) {

        Integer input1 = null;
        Integer input2 = null;

        try {
          input1 = AbstractDriveMode.getTextField1Input();
          input2 = AbstractDriveMode.getTextField2Input();
          mode.move(input1, input2);
          ImageView imageView = new ImageView("main/res/stop.png");
          imageView.setFitWidth(25);
          imageView.setFitHeight(25);
          toggle.setGraphic(imageView);
        } catch (NumberFormatException e) {
          if (input1 == null) {
            mode.parameterOneErrorAlert();
          } else if (input2 == null) {
            mode.parameterTwoErrorAlert();
          }
        }
      } else {
        mode.move(0, 0);
        ImageView imageView = new ImageView("main/res/play.png");
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        toggle.setGraphic(imageView);
      }

      hasStarted = !hasStarted;

    } else {

      NotConnectedAlert connectionAlert = new NotConnectedAlert();
      connectionAlert.show();

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

    driveModule.setOnKeyPressed(e -> {
      switch (e.getCode()) {
        case SPACE:
          if (hasStarted) {
            toggle.fire();
          }
          break;
        default:
          break;
      }
    });

    driveModule.setOnKeyPressed(event -> {
      switch (event.getCode()) {
        case SPACE:
          toggle.fire();
          break;
        default:
          break;
      }
    });

    /* *********************************************
    *
    * ComboBox logic
    *
    ********************************************** */

    AbstractDriveMode.setTextFields(textField1, textField2);
    AbstractDriveMode driveMode = new Drive();
    AbstractDriveMode driveDirectMode = new DriveDirect();

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

package main.ui.modules.sensor;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import main.core.DateFormat;
import main.core.RoombaState;
import main.core.sensor.bool.AbstractBooleanSensor;
import main.core.sensor.bool.BumpCenter;
import main.core.sensor.bool.BumpLeft;
import main.core.sensor.bool.BumpRight;
import main.core.sensor.bool.CliffFrontLeft;
import main.core.sensor.bool.CliffFrontRight;
import main.core.sensor.bool.CliffLeft;
import main.core.sensor.bool.CliffRight;
import main.core.sensor.bool.LightBumperCenterLeft;
import main.core.sensor.bool.LightBumperCenterRight;
import main.core.sensor.bool.LightBumperFrontLeft;
import main.core.sensor.bool.LightBumperFrontRight;
import main.core.sensor.bool.LightBumperLeft;
import main.core.sensor.bool.LightBumperRight;
import main.core.sensor.signal.AbstractSignalSensor;
import main.core.sensor.signal.CliffFrontLeftSignal;
import main.core.sensor.signal.CliffFrontRightSignal;
import main.core.sensor.signal.CliffLeftSignal;
import main.core.sensor.signal.CliffRightSignal;
import main.core.sensor.signal.LightBumpCenterLeftSignal;
import main.core.sensor.signal.LightBumpCenterRightSignal;
import main.core.sensor.signal.LightBumpFrontLeftSignal;
import main.core.sensor.signal.LightBumpFrontRightSignal;
import main.core.sensor.signal.LightBumpLeftSignal;
import main.core.sensor.signal.LightBumpRightSignal;
import main.core.sensor.signal.Wall;
import main.ui.alerts.InvalidSensorAlert;
import main.ui.alerts.NotConnectedAlert;
import main.ui.modules.ModuleController;

/*
* Manages interaction between the user and the Roomba.
* SensorModule allows users to set parameters for the Roomba
* Sensor command and send that command to the Roomba.
* */
public class SensorModuleController extends ModuleController implements Initializable {

  /* *********************************************
  *
  * FXML members
  *
  ********************************************** */

  @FXML
  private JFXComboBox<AbstractBooleanSensor> booleanSensorComboBox;

  @FXML
  private JFXButton toggleBooleanSensorButton;

  @FXML
  private JFXComboBox<AbstractSignalSensor> signalSensorComboBox;

  @FXML
  private JFXButton toggleSignalSensorButton;

  @FXML
  private VBox sensorModule;

  private boolean booleanToggleEnabled;
  private boolean signalToggleEnabled;
  private final Service<Void> booleanToggleService = new BooleanToggleService();
  private final Service<Void> signalToggleService = new SignalToggleService();

  private boolean isComboBoxNull(JFXComboBox comboBox) {
    return comboBox.getSelectionModel().getSelectedItem() == null;
  }

  @Override
  public void play(ActionEvent event) {
  }

  @FXML
  void readBooleanSensor(ActionEvent event) {
    // Do nothing, if Roomba is not connected.
    if (!RoombaState.isConnected()) {
      NotConnectedAlert connectionAlert = new NotConnectedAlert();
      connectionAlert.show();
      return;
    }

    // Invalid parameters detected. Alert user and exit method.
    if (isComboBoxNull(booleanSensorComboBox)) {
      InvalidSensorAlert alert = new InvalidSensorAlert("Boolean Sensor");
      alert.show();
      return;
    }

    if (!signalToggleEnabled && booleanToggleEnabled) {
      booleanSensorComboBox.setDisable(false);
      booleanToggleService.cancel();
      rootController.setImage(toggleBooleanSensorButton, "main/res/play.png");
    } else {
      booleanSensorComboBox.setDisable(true);
      booleanToggleService.reset();
      booleanToggleService.start();
      rootController.setImage(toggleBooleanSensorButton, "main/res/stop.png");
    }
    booleanToggleEnabled = !booleanToggleEnabled;
  }

  @FXML
  void readSignalSensor(ActionEvent event) {
    // Do nothing, if Roomba is not connected.
    if (!RoombaState.isConnected()) {
      NotConnectedAlert connectionAlert = new NotConnectedAlert();
      connectionAlert.show();
      return;
    }

    // Invalid parameters detected. Alert user and exit method.
    if (isComboBoxNull(booleanSensorComboBox)) {
      InvalidSensorAlert alert = new InvalidSensorAlert("Signal Sensor");
      alert.show();
      return;
    }

    if (!booleanToggleEnabled && signalToggleEnabled) {
      signalSensorComboBox.setDisable(false);
      signalToggleService.cancel();
      rootController.setImage(toggleSignalSensorButton, "main/res/play.png");
    } else {
      signalSensorComboBox.setDisable(true);
      signalToggleService.reset();
      signalToggleService.start();
      rootController.setImage(toggleSignalSensorButton, "main/res/stop.png");
    }
    signalToggleEnabled = !signalToggleEnabled;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    /* *********************************************
    *
    * Add stop module to Space key
    *
    ********************************************** */
    sensorModule.setOnKeyPressed(e -> {
      switch (e.getCode()) {
        case SPACE:
          if (booleanToggleEnabled) {
            toggleBooleanSensorButton.fire();
          } else {
            if (signalToggleEnabled) {
              toggleSignalSensorButton.fire();
            }
          }
          break;
        default:
          break;
      }
    });

    /* *********************************************
    *
    * Add boolean sensors
    *
    ********************************************** */

    booleanSensorComboBox.setVisibleRowCount(5);
    booleanSensorComboBox.getItems().add(new BumpCenter());
    booleanSensorComboBox.getItems().add(new BumpLeft());
    booleanSensorComboBox.getItems().add(new BumpRight());
    booleanSensorComboBox.getItems().add(new CliffLeft());
    booleanSensorComboBox.getItems().add(new CliffFrontLeft());
    booleanSensorComboBox.getItems().add(new CliffFrontRight());
    booleanSensorComboBox.getItems().add(new CliffRight());
    booleanSensorComboBox.getItems().add(new LightBumperLeft());
    booleanSensorComboBox.getItems().add(new LightBumperFrontLeft());
    booleanSensorComboBox.getItems().add(new LightBumperCenterLeft());
    booleanSensorComboBox.getItems().add(new LightBumperCenterRight());
    booleanSensorComboBox.getItems().add(new LightBumperFrontRight());
    booleanSensorComboBox.getItems().add(new LightBumperRight());
    booleanSensorComboBox.getSelectionModel().selectFirst();


    /* *********************************************
    *
    * Add signal sensors
    *
    ********************************************** */

    signalSensorComboBox.setVisibleRowCount(5);
    signalSensorComboBox.getItems().add(new Wall());
    signalSensorComboBox.getItems().add(new CliffLeftSignal());
    signalSensorComboBox.getItems().add(new CliffFrontLeftSignal());
    signalSensorComboBox.getItems().add(new CliffFrontRightSignal());
    signalSensorComboBox.getItems().add(new CliffRightSignal());
    signalSensorComboBox.getItems().add(new LightBumpLeftSignal());
    signalSensorComboBox.getItems().add(new LightBumpFrontLeftSignal());
    signalSensorComboBox.getItems().add(new LightBumpCenterLeftSignal());
    signalSensorComboBox.getItems().add(new LightBumpCenterRightSignal());
    signalSensorComboBox.getItems().add(new LightBumpFrontRightSignal());
    signalSensorComboBox.getItems().add(new LightBumpRightSignal());
    signalSensorComboBox.getSelectionModel().selectFirst();

  }

  /*
  * Creates a Service (thread) that reads and displays
  * the value of a selected Roomba boolean sensor
  * to the console until the service is canceled.
  * */
  private class BooleanToggleService extends Service<Void> {
    /*
    * TODO Move to a separate file
    * Preventative issue is that the booleanSensorComboBox and rootController
    * variables are null at the time they are pasted to constructors
    * need an alternative.
    * */

    @Override
    protected Task<Void> createTask() {
      return new Task<Void>() {
        @Override
        protected Void call() throws Exception {
          AbstractBooleanSensor sensor = booleanSensorComboBox.getSelectionModel()
              .getSelectedItem();
          while (!isCancelled()) {
            boolean value = sensor.read();
            String message = DateFormat.logDate() + "\t<---> " + value + "\n";
            Platform.runLater(() -> rootController.console.appendText(message));
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
              if (isCancelled()) {
                break;
              }
            }
          }
          return null;
        }
      };
    }
  }

  /*
  * Creates a Service (thread) that reads and displays
  * the value of a selected Roomba signal sensor
  * to the console until the service is canceled.
  * */
  private class SignalToggleService extends Service<Void> {
    /*
    * TODO Move to a separate file
    * Preventative issue is that the signalSensorComboBox and rootController
    * variables are null at the time they are pasted to constructors
    * need an alternative.
    * */

    @Override
    protected Task<Void> createTask() {
      return new Task<Void>() {
        @Override
        protected Void call() throws Exception {
          AbstractSignalSensor sensor = signalSensorComboBox.getSelectionModel().getSelectedItem();
          while (!isCancelled()) {
            int value = sensor.read();
            String message = DateFormat.logDate() + "\t<---> " + value + "\n";
            Platform.runLater(() -> rootController.console.appendText(message));
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
              if (isCancelled()) {
                break;
              }
            }
          }
          return null;
        }
      };
    }
  }

}

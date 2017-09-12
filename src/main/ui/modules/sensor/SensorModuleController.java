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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import main.core.Injectable;
import main.core.RoombaJSSCSingleton;
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
import main.ui.root.RootController;

/*
* Manages interaction between the user and the Roomba.
* SensorModule allows users to set parameters for the Roomba
* Sensor command and send that command to the Roomba.
* */
public class SensorModuleController implements Initializable, Injectable {

  private RootController rootController;

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

  /*
  * TODO Fix application thread flooding problem
  * See https://stackoverflow.com/questions/31408363/javafx-changelistener-not-always-working/31414801#31414801
  * for more info.
  *
  * Look into swapping out the TextArea for a ListView, as they are
  * a virtual control that only renders text shown
  * instead of all containing text.
  *
  * See https://stackoverflow.com/questions/33078241/javafx-application-freeze-when-i-append-log4j-to-textarea
  * and http://www.rshingleton.com/javafx-log4j-textarea-log-appender/
  * for more info.
  *
  * Update: solution may have been found.
  * I modified the TextAreaAppender to write to the TextArea
  * from within a Platform.runLater() since then problem has
  * not occurred, but more testing is needed.
  * */

  private Service<Void> booleanToggleService = new Service<Void>() {

    @Override
    protected Task<Void> createTask() {

      return new Task<Void>() {
        @Override
        protected Void call() throws Exception {

          AbstractBooleanSensor sensor = booleanSensorComboBox.getSelectionModel()
              .getSelectedItem();
          while (!isCancelled()) {
            boolean value = sensor.read();
            String message = RoombaJSSCSingleton.logDate() + "\t<---> " + value + "\n";
            Platform.runLater(() -> {
              rootController.console.appendText(message);
            });
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

  };

  private Service<Void> signalToggleService = new Service<Void>() {

    @Override
    protected Task<Void> createTask() {

      return new Task<Void>() {
        @Override
        protected Void call() throws Exception {

          AbstractSignalSensor sensor = signalSensorComboBox.getSelectionModel().getSelectedItem();
          while (!isCancelled()) {
            int value = sensor.read();
            String message = RoombaJSSCSingleton.logDate() + "\t<---> " + value + "\n";
            Platform.runLater(() -> {
              rootController.console.appendText(message);
            });
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

  };

  private boolean isComboBoxNull(JFXComboBox comboBox) {
    return comboBox.getSelectionModel().getSelectedItem() == null;
  }

  @FXML
  void readBooleanSensor(ActionEvent event) {
    // Don't run if other thread is running
    if (!signalToggleEnabled) {
      if (!isComboBoxNull(booleanSensorComboBox)) {
        if (booleanToggleEnabled) {
          booleanSensorComboBox.setDisable(false);
          booleanToggleService.cancel();
          ImageView imageView = new ImageView("main/res/play.png");
          imageView.setFitWidth(25);
          imageView.setFitHeight(25);
          toggleBooleanSensorButton.setGraphic(imageView);
        } else {
          booleanSensorComboBox.setDisable(true);
          booleanToggleService.reset();
          booleanToggleService.start();
          ImageView imageView = new ImageView("main/res/stop.png");
          imageView.setFitWidth(25);
          imageView.setFitHeight(25);
          toggleBooleanSensorButton.setGraphic(imageView);
        }
        booleanToggleEnabled = !booleanToggleEnabled;
      } else {
        Alert alert = new Alert(AlertType.INFORMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getScene().getStylesheets().add("main/ui/root/dialog.css");
        alert.setHeaderText("Boolean Sensor");
        alert.setContentText("You must select a sensor first!");
        alert.show();
      }
    }
  }

  @FXML
  void readSignalSensor(ActionEvent event) {
    // Don't run if other thread is running
    if (!booleanToggleEnabled) {
      if (!isComboBoxNull(signalSensorComboBox)) {
        if (signalToggleEnabled) {
          signalSensorComboBox.setDisable(false);
          signalToggleService.cancel();
          ImageView imageView = new ImageView("main/res/play.png");
          imageView.setFitWidth(25);
          imageView.setFitHeight(25);
          toggleSignalSensorButton.setGraphic(imageView);
        } else {
          signalSensorComboBox.setDisable(true);
          signalToggleService.reset();
          signalToggleService.start();
          ImageView imageView = new ImageView("main/res/stop.png");
          imageView.setFitWidth(25);
          imageView.setFitHeight(25);
          toggleSignalSensorButton.setGraphic(imageView);
        }
        signalToggleEnabled = !signalToggleEnabled;
      } else {
        Alert alert = new Alert(AlertType.INFORMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getScene().getStylesheets().add("main/ui/root/dialog.css");
        alert.setHeaderText("Signal Sensor");
        alert.setContentText("You must select a sensor first!");
        alert.show();
      }
    }
  }

  @Override
  public void inject(RootController rootController) {
    this.rootController = rootController;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

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

    sensorModule.setStyle("-fx-background-color: #47494c; -fx-background-radius: 10;");

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

  }

}

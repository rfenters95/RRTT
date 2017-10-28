package main.ui.modules.led;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import main.core.RoombaJSSCSingleton;
import main.core.led.listeners.PowerColorListener;
import main.core.led.listeners.PowerIntensityListener;
import main.core.menu.PowerColorMenu;
import main.core.menu.PowerIntensityMenu;
import main.ui.alerts.InvalidInputAlert;
import main.ui.alerts.InvalidPowerColorAlert;
import main.ui.alerts.InvalidPowerIntensityAlert;
import main.ui.alerts.NotConnectedAlert;
import main.ui.modules.ModuleController;

/*
* Manages interaction between the user and the Roomba.
* LightModule allows users to set parameters for the Roomba
* LED command and send that command to the Roomba.
* */
public class LedModuleController extends ModuleController implements Initializable {

  @FXML
  private VBox lightModule;

  @FXML
  private JFXCheckBox debrisCB;

  @FXML
  private JFXCheckBox spotCB;

  @FXML
  private JFXCheckBox dockCB;

  @FXML
  private JFXCheckBox checkRobotCB;

  @FXML
  private JFXTextField powerColorTF;

  @FXML
  private JFXTextField powerIntensityTF;

  /*
  * Extracts int from TextFields
  * @param textField TextField from which to extract int.
  * @return int value in textField.
  * */
  private int extractTextFieldInteger(JFXTextField textField) {
    return Integer.parseInt(textField.getText());
  }

  /*
  * Determines if led power color value is valid.
  * @return True if power color is valid.
  * */
  private boolean isValidPowerColor(int color) {
    return (color >= 0 && color <= 255);
  }

  /*
  * Determines if led power intensity value is valid.
  * @return True if power intensity is valid.
  * */
  private boolean isValidPowerIntensity(int intensity) {
    return (intensity >= 0 && intensity <= 255);
  }

  /*
  * Determines if led parameters are valid.
  * @return True if parameters are valid.
  * */
  private boolean isValidPowerParameters(int color, int intensity) {
    return isValidPowerColor(color) && isValidPowerIntensity(intensity);
  }

  /*
  * Get integer power color value from TextField.
  * @return Power color value as int.
  * */
  private int getPowerColor() {
    return extractTextFieldInteger(powerColorTF);
  }

  /*
  * Get integer power intensity value from TextField.
  * @return Power intensity value as int.
  * */
  private int getPowerIntensity() {
    return extractTextFieldInteger(powerIntensityTF);
  }

  /*
  * Enables/Disables all module fields.
  * @param value Determines if fields are enabled or disabled.
  * */
  private void setDisableFields(boolean value) {
    debrisCB.setDisable(value);
    spotCB.setDisable(value);
    dockCB.setDisable(value);
    checkRobotCB.setDisable(value);
    powerColorTF.setDisable(value);
    powerIntensityTF.setDisable(value);
  }

  @Override
  public void play(ActionEvent event) {
    // Do nothing, if Roomba is not connected.
    if (!RoombaJSSCSingleton.isConnected()) {
      NotConnectedAlert connectionAlert = new NotConnectedAlert();
      connectionAlert.show();
      return;
    }

    // Invalid parameters detected. Alert user and exit method.
    if (!isValidPowerParameters(getPowerColor(), getPowerIntensity())) {
      InvalidInputAlert inputAlert;
      if (!isValidPowerColor(getPowerColor())) {
        inputAlert = new InvalidPowerColorAlert();
      } else {
        inputAlert = new InvalidPowerIntensityAlert();
      }
      inputAlert.show();
      return;
    }

    if (isPlaying) {
      // Turn off leds and reset play image.
      RoombaJSSCSingleton.getRoombaJSSC().leds(false, false, false, false, 0, 0);
      setDisableFields(false);
      rootController.setImage(playButton, "main/res/play.png");
    } else {
      // Turn on leds using input parameters and set stop image.
      RoombaJSSCSingleton.getRoombaJSSC().leds(
          debrisCB.isSelected(),
          spotCB.isSelected(),
          dockCB.isSelected(),
          checkRobotCB.isSelected(),
          getPowerColor(),
          getPowerIntensity()
      );
      setDisableFields(true);
      rootController.setImage(playButton, "main/res/stop.png");
    }
    isPlaying = !isPlaying;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    lightModule.setOnKeyPressed(e -> {
      switch (e.getCode()) {
        case SPACE:
          if (isPlaying) {
            playButton.fire();
          }
          break;
        default:
          break;
      }
    });

    PowerColorMenu powerColorMenu = new PowerColorMenu(powerColorTF);
    powerColorTF.setContextMenu(powerColorMenu);

    PowerIntensityMenu powerIntensityMenu = new PowerIntensityMenu(powerIntensityTF);
    powerIntensityTF.setContextMenu(powerIntensityMenu);

    powerColorTF.setText("0");
    powerIntensityTF.setText("0");
    powerColorTF.textProperty().addListener(new PowerColorListener(powerColorTF));
    powerIntensityTF.textProperty()
        .addListener(new PowerIntensityListener(powerIntensityTF));

  }

}

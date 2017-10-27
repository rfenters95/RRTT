package main.ui.modules.led;

import com.jfoenix.controls.JFXButton;
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

  /* *********************************************
  *
  * FXML members
  *
  ********************************************** */

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

  @FXML
  private JFXButton toggle;

  private int extractTextFieldInteger(JFXTextField textField) {
    return Integer.parseInt(textField.getText());
  }

  private boolean isValidPowerColor(int color) {
    return (color >= 0 && color <= 255);
  }

  private boolean isValidPowerIntensity(int intensity) {
    return (intensity >= 0 && intensity <= 255);
  }

  private boolean isValidPowerParameters(int color, int intensity) {
    return isValidPowerColor(color) && isValidPowerIntensity(intensity);
  }

  private int getPowerColor() {
    return extractTextFieldInteger(powerColorTF);
  }

  private int getPowerIntensity() {
    return extractTextFieldInteger(powerIntensityTF);
  }

  @Override
  public void play(ActionEvent event) {
    if (RoombaJSSCSingleton.isConnected()) {
      if (!isPlaying) {
        if (isValidPowerParameters(getPowerColor(), getPowerIntensity())) {
          RoombaJSSCSingleton.getRoombaJSSC().leds(
              debrisCB.isSelected(),
              spotCB.isSelected(),
              dockCB.isSelected(),
              checkRobotCB.isSelected(),
              getPowerColor(),
              getPowerIntensity()
          );
          rootController.setImage((JFXButton) event.getSource(), "main/res/stop.png");
          isPlaying = !isPlaying;
        } else {
          InvalidInputAlert inputAlert;
          if (!isValidPowerColor(getPowerColor())) {
            inputAlert = new InvalidPowerColorAlert();
          } else {
            inputAlert = new InvalidPowerIntensityAlert();
          }
          inputAlert.show();
        }

      } else {
        RoombaJSSCSingleton.getRoombaJSSC().leds(false, false, false, false, 0, 0);
        rootController.setImage((JFXButton) event.getSource(), "main/res/play.png");
        isPlaying = !isPlaying;
      }
    } else {
      NotConnectedAlert connectionAlert = new NotConnectedAlert();
      connectionAlert.show();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    lightModule.setOnKeyPressed(e -> {
      switch (e.getCode()) {
        case SPACE:
          if (isPlaying) {
            toggle.fire();
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

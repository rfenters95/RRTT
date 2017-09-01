package main.ui.modules.led;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.core.Injectable;
import main.core.RoombaJSSCSingleton;
import main.core.led.listeners.PowerColorListener;
import main.core.led.listeners.PowerIntensityListener;
import main.ui.root.RootController;

/*
* Manages interaction between the user and the Roomba.
* LightTab allows users to set parameters for the Roomba
* LED command and send that command to the Roomba.
* */
public class LightModuleController implements Initializable, Injectable {

  private RootController rootController;

  @FXML
  private AnchorPane ledTab;

  @FXML
  private JFXCheckBox debrisCheckBox;

  @FXML
  private JFXCheckBox spotCheckBox;

  @FXML
  private JFXCheckBox dockCheckBox;

  @FXML
  private JFXCheckBox checkRobotCheckBox;

  @FXML
  private JFXTextField powerColorTextField;

  @FXML
  private JFXTextField powerIntensityTextField;

  @FXML
  void toggle(ActionEvent event) {

    int powerColor = Integer.parseInt(powerColorTextField.getText());
    int powerIntensity = Integer.parseInt(powerIntensityTextField.getText());
    boolean check1 = (powerColor >= 0 && powerColor <= 255);
    boolean check2 = (powerIntensity >= 0 && powerIntensity <= 255);

    if (check1 && check2) {
      boolean debris = debrisCheckBox.isSelected();
      boolean spot = spotCheckBox.isSelected();
      boolean dock = dockCheckBox.isSelected();
      boolean checkRobot = checkRobotCheckBox.isSelected();
      RoombaJSSCSingleton.getRoombaJSSC()
          .leds(debris, spot, dock, checkRobot, powerColor, powerIntensity);
    }

  }

  @Override
  public void inject(RootController rootController) {
    this.rootController = rootController;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    powerColorTextField.setText("0");
    powerIntensityTextField.setText("0");
    powerColorTextField.textProperty().addListener(new PowerColorListener(powerColorTextField));
    powerIntensityTextField.textProperty()
        .addListener(new PowerIntensityListener(powerIntensityTextField));
  }

}

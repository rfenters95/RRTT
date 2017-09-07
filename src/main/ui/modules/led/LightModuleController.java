package main.ui.modules.led;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import main.core.Injectable;
import main.core.RoombaJSSCSingleton;
import main.core.led.listeners.PowerColorListener;
import main.core.led.listeners.PowerIntensityListener;
import main.ui.root.RootController;

/*
* Manages interaction between the user and the Roomba.
* LightModule allows users to set parameters for the Roomba
* LED command and send that command to the Roomba.
* */
public class LightModuleController implements Initializable, Injectable {

  private RootController rootController;

  /* *********************************************
  *
  * FXML members
  *
  ********************************************** */

  @FXML
  private VBox lightModule;

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
  private JFXButton toggle;

  private boolean hasStarted = false;

  @FXML
  void toggle(ActionEvent event) {

    if (!hasStarted) {
      int powerColor = Integer.parseInt(powerColorTextField.getText());
      int powerIntensity = Integer.parseInt(powerIntensityTextField.getText());
      boolean check1 = (powerColor >= 0 && powerColor <= 100);
      boolean check2 = (powerIntensity >= 0 && powerIntensity <= 100);

      if (check1 && check2) {
        boolean debris = debrisCheckBox.isSelected();
        boolean spot = spotCheckBox.isSelected();
        boolean dock = dockCheckBox.isSelected();
        boolean checkRobot = checkRobotCheckBox.isSelected();
        RoombaJSSCSingleton.getRoombaJSSC()
            .leds(debris, spot, dock, checkRobot, powerColor, powerIntensity);
      } else {
        if (!check1) {
          Alert alert = new Alert(AlertType.ERROR);
          alert.setHeaderText("Power Color");
          alert.setContentText("Invalid Input! Range [0, 100]");
          alert.show();
        } else {
          Alert alert = new Alert(AlertType.ERROR);
          alert.setHeaderText("Power Intensity");
          alert.setContentText("Invalid Input! Range [0, 100]");
          alert.show();
        }
      }

      JFXButton button = (JFXButton) event.getSource();
      ImageView imageView = new ImageView("main/res/stop.png");
      imageView.setFitWidth(25);
      imageView.setFitHeight(25);
      button.setGraphic(imageView);

    } else {
      RoombaJSSCSingleton.getRoombaJSSC().leds(false, false, false, false, 0, 0);
      JFXButton button = (JFXButton) event.getSource();
      ImageView imageView = new ImageView("main/res/play.png");
      imageView.setFitWidth(25);
      imageView.setFitHeight(25);
      button.setGraphic(imageView);
    }

    hasStarted = !hasStarted;

  }

  @Override
  public void inject(RootController rootController) {
    this.rootController = rootController;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    lightModule.setStyle("-fx-background-color: #47494c; -fx-background-radius: 10;");
    lightModule.setOnKeyPressed(event -> {
      switch (event.getCode()) {
        case SPACE:
          toggle.fire();
          break;
        default:
          break;
      }
    });

    powerColorTextField.setText("0");
    powerIntensityTextField.setText("0");
    powerColorTextField.textProperty().addListener(new PowerColorListener(powerColorTextField));
    powerIntensityTextField.textProperty()
        .addListener(new PowerIntensityListener(powerIntensityTextField));

  }

}

package main.ui.modules.led;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import main.core.Injectable;
import main.core.RoombaJSSCSingleton;
import main.core.led.listeners.PowerColorListener;
import main.core.led.listeners.PowerIntensityListener;
import main.ui.alerts.InvalidInputAlert;
import main.ui.alerts.InvalidPowerColorAlert;
import main.ui.alerts.InvalidPowerIntensityAlert;
import main.ui.root.RootController;

/*
* Manages interaction between the user and the Roomba.
* LightModule allows users to set parameters for the Roomba
* LED command and send that command to the Roomba.
* */
public class LedModuleController implements Initializable, Injectable {

  private RootController rootController;

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

  private boolean ledIsOn = false;

  private void setImage(JFXButton button, String path) {
    ImageView imageView = new ImageView(path);
    imageView.setFitWidth(25);
    imageView.setFitHeight(25);
    button.setGraphic(imageView);
  }

  private int extractTextFieldInteger(JFXTextField textField) {
    return Integer.parseInt(textField.getText());
  }

  private boolean isValidPowerColor(int color) {
    return (color >= 0 && color <= 100);
  }

  private boolean isValidPowerIntensity(int intensity) {
    return (intensity >= 0 && intensity <= 100);
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

  @FXML
  void toggle(ActionEvent event) {
    if (!ledIsOn) {
      if (isValidPowerParameters(getPowerColor(), getPowerIntensity())) {
        RoombaJSSCSingleton.getRoombaJSSC().leds(
            debrisCB.isSelected(),
            spotCB.isSelected(),
            dockCB.isSelected(),
            checkRobotCB.isSelected(),
            getPowerColor(),
            getPowerIntensity()
        );
        setImage((JFXButton) event.getSource(), "main/res/stop.png");
        ledIsOn = !ledIsOn;
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
      setImage((JFXButton) event.getSource(), "main/res/play.png");
      ledIsOn = !ledIsOn;
    }

  }

  @Override
  public void inject(RootController rootController) {
    this.rootController = rootController;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    lightModule.setOnKeyPressed(e -> {
      switch (e.getCode()) {
        case SPACE:
          if (ledIsOn) {
            toggle.fire();
          }
          break;
        default:
          break;
      }
    });

    lightModule.setOnKeyPressed(event -> {
      switch (event.getCode()) {
        case SPACE:
          toggle.fire();
          break;
        default:
          break;
      }
    });

    final ContextMenu powerColorContextMenu = new ContextMenu();
    MenuItem powerColorMenuItemGreen = new MenuItem("Special: Color Green");
    powerColorMenuItemGreen.setOnAction(e -> powerColorTF.setText("0"));
    MenuItem powerColorMenuItemOrange = new MenuItem("Special: Color Orange");
    powerColorMenuItemOrange.setOnAction(e -> powerColorTF.setText("50"));
    MenuItem powerColorMenuItemYellow = new MenuItem("Special: Color Yellow");
    powerColorMenuItemYellow.setOnAction(e -> powerColorTF.setText("75"));
    MenuItem powerColorMenuItemRed = new MenuItem("Special: Color Red");
    powerColorMenuItemRed.setOnAction(e -> powerColorTF.setText("100"));
    powerColorContextMenu.getItems().add(powerColorMenuItemGreen);
    powerColorContextMenu.getItems().add(powerColorMenuItemOrange);
    powerColorContextMenu.getItems().add(powerColorMenuItemYellow);
    powerColorContextMenu.getItems().add(powerColorMenuItemRed);
    powerColorTF.setContextMenu(powerColorContextMenu);

    final ContextMenu powerIntensityContextMenu = new ContextMenu();
    MenuItem powerIntensityMenuItemOff = new MenuItem("Special: Intensity None");
    powerIntensityMenuItemOff.setOnAction(e -> powerIntensityTF.setText("0"));
    MenuItem powerIntensityMenuItemHalf = new MenuItem("Special: Intensity Half");
    powerIntensityMenuItemHalf.setOnAction(e -> powerIntensityTF.setText("50"));
    MenuItem powerIntensityMenuItemFull = new MenuItem("Special: Intensity Full");
    powerIntensityMenuItemFull.setOnAction(e -> powerIntensityTF.setText("100"));
    powerIntensityContextMenu.getItems().add(powerIntensityMenuItemOff);
    powerIntensityContextMenu.getItems().add(powerIntensityMenuItemHalf);
    powerIntensityContextMenu.getItems().add(powerIntensityMenuItemFull);
    powerIntensityTF.setContextMenu(powerIntensityContextMenu);

    powerColorTF.setText("0");
    powerIntensityTF.setText("0");
    powerColorTF.textProperty().addListener(new PowerColorListener(powerColorTF));
    powerIntensityTF.textProperty()
        .addListener(new PowerIntensityListener(powerIntensityTF));

  }

}

package main.ui.root;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.core.TextAreaAppender;
import main.ui.modules.drive.DriveModuleController;
import main.ui.modules.led.LightModuleController;
import main.ui.modules.sensor.SensorModuleController;

public class RootController implements Initializable {

  @FXML
  public VBox root;

  @FXML
  public SplitPane splitPane;

  @FXML
  public JFXHamburger hamburger;

  @FXML
  public JFXDrawer drawer;

  @FXML
  public TextArea console;

  @FXML
  private DriveModuleController driveModuleController;

  @FXML
  private LightModuleController lightModuleController;

  @FXML
  private SensorModuleController sensorModuleController;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    driveModuleController.inject(this);
    lightModuleController.inject(this);
    sensorModuleController.inject(this);

    TextAreaAppender.textArea = console;
    console.setWrapText(false);
    console.setEditable(false);

    try {
      HBox nav = FXMLLoader.load(getClass().getResource("NavDrawer.fxml"));
      drawer.setSidePane(nav);
    } catch (IOException e) {
      System.out.println("Nav failed to load");
    }

    HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(
        hamburger);
    transition.setRate(-1);
    hamburger.setOnMouseClicked(e -> {
      transition.setRate(transition.getRate() * -1);
      transition.play();

      if (drawer.isShown()) {
        drawer.close();
      } else {
        drawer.open();
      }
    });

  }

}

package main.ui.root;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.core.RoombaJSSCSingleton;
import main.core.TextAreaAppender;
import main.core.menu.ConsoleMenu;
import main.core.menu.SplitPaneMenu;
import main.ui.modules.drive.DriveModuleController;
import main.ui.modules.led.LedModuleController;
import main.ui.modules.sensor.SensorModuleController;
import main.ui.modules.song.SongModuleController;

public class RootController implements Initializable {

  /* *********************************************
  *
  * RootController components
  *
  ********************************************** */

  @FXML
  public VBox root;

  @FXML
  public SplitPane splitPane;

  @FXML
  public TextArea console;

  @FXML
  public Label batteryPercentageLabel;

  @FXML
  public JFXButton powerButton;

  @FXML
  public JFXTabPane controllerNavigationTabPane;

  /* *********************************************
  *
  * Nested Controller Containers
  *
  ********************************************** */

  @FXML
  private StackPane driveModuleContainer;

  @FXML
  private StackPane lightModuleContainer;

  @FXML
  private StackPane sensorModuleContainer;

  /* *********************************************
  *
  * Nested Controllers
  *
  ********************************************** */

  @FXML
  private DriveModuleController driveModuleController;

  @FXML
  private LedModuleController ledModuleController;

  @FXML
  private SensorModuleController sensorModuleController;

  @FXML
  private ControllerNavigationController controllerNavigationController;

  @FXML
  private SongModuleController songModuleController;

  /* *********************************************
  *
  * Instance methods
  *
  ********************************************** */

  public void setImage(JFXButton button, String path) {
    ImageView imageView = new ImageView(path);
    imageView.setFitWidth(25);
    imageView.setFitHeight(25);
    button.setGraphic(imageView);
  }

  /* *********************************************
  *
  * Actions
  *
  ********************************************** */

  @FXML
  private void togglePower(ActionEvent event) {
    if (RoombaJSSCSingleton.isConnected()) {
      RoombaJSSCSingleton.powerOff();
      setImage(powerButton, "main/res/powerOff.png");
      batteryPercentageLabel.setText("Not Connected!");
    } else {
      try {
        Stage stage = new Stage();
        URL url = getClass().getClassLoader().getResource("main/ui/root/ConnectionManagement.fxml");
        assert url != null;
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.setTitle("Connection Manager");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.setResizable(false);
        stage.showAndWait();
        if (RoombaJSSCSingleton.isConnected()) {
          BatteryUpdaterThread batteryUpdaterThread;
          batteryUpdaterThread = new BatteryUpdaterThread(batteryPercentageLabel);
          batteryUpdaterThread.start();
          setImage(powerButton, "main/res/powerOn.png");
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /* *********************************************
  *
  * RootController initialization
  *
  ********************************************** */

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    // Prevent highlighted button on start
    powerButton.setFocusTraversable(false);

    /* *********************************************
    *
    * Inject RootController
    *
    ********************************************** */

    driveModuleController.inject(this);
    ledModuleController.inject(this);
    sensorModuleController.inject(this);
    controllerNavigationController.inject(this);
    songModuleController.inject(this);

    /* *********************************************
    *
    * Set max Console height
    *
    ********************************************** */

    double initialDividerPosition = splitPane.getDividers().get(0).getPosition();
    splitPane.getDividers().get(0).positionProperty()
        .addListener((observable, oldValue, newValue) -> {
          if (newValue.doubleValue() < initialDividerPosition) {
            splitPane.setDividerPosition(0, initialDividerPosition);
          }
        });

    /* *********************************************
    *
    * Set splitPane ContextMenu
    *
    ********************************************** */

    ContextMenu splitPaneMenu = new SplitPaneMenu(splitPane);
    splitPane.setContextMenu(splitPaneMenu);

    /* *********************************************
    *
    * Configure console
    *
    ********************************************** */

    TextAreaAppender.textArea = console;
    console.setWrapText(false);
    console.setEditable(false);

    /* *********************************************
    *
    * Set console ContextMenu
    *
    ********************************************** */

    ContextMenu ConsoleMenu = new ConsoleMenu(root, console);
    console.setContextMenu(ConsoleMenu);

  }

}

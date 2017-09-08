package main.ui.root;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import main.core.RoombaJSSCSingleton;
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
  public JFXTabPane tabPane;

  @FXML
  public TextArea console;

  @FXML
  public JFXButton powerButton;

  @FXML
  private StackPane driveModuleContainer;

  @FXML
  private StackPane lightModuleContainer;

  @FXML
  private StackPane sensorModuleContainer;

  @FXML
  private DriveModuleController driveModuleController;

  @FXML
  private LightModuleController lightModuleController;

  @FXML
  private SensorModuleController sensorModuleController;

  @FXML
  private void togglePower(ActionEvent event) {
    // shutdown all functions in order
    RoombaJSSCSingleton.getRoombaJSSC().driveDirect(0, 0);
    RoombaJSSCSingleton.getRoombaJSSC().leds(false, false, false, false, 0, 0);
    // disconnect roomba
    RoombaJSSCSingleton.getRoombaJSSC().powerOff();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    // Give nested module controllers access to root controller
    driveModuleController.inject(this);
    lightModuleController.inject(this);
    sensorModuleController.inject(this);

    /*
    * driveModuleContainer.setStyle("-fx-background-color: red;");
    * lightModuleContainer.setStyle("-fx-background-color: white;");
    * sensorModuleContainer.setStyle("-fx-background-color: blue;");
    * */

    // Context menu for splitPane
    double initialDividerPosition = splitPane.getDividers().get(0).getPosition();
    ContextMenu splitPaneContextMenu = new ContextMenu();
    MenuItem showSplitPaneDivider = new MenuItem("Show/Reset Console");
    showSplitPaneDivider.setOnAction(e -> splitPane.setDividerPosition(0, initialDividerPosition));
    MenuItem hideSplitPaneDivider = new MenuItem("Hide Console");
    hideSplitPaneDivider.setOnAction(e -> splitPane.setDividerPosition(0, 1));
    splitPaneContextMenu.getItems().add(showSplitPaneDivider);
    splitPaneContextMenu.getItems().add(hideSplitPaneDivider);
    splitPane.setContextMenu(splitPaneContextMenu);

    // Configure TextArea
    TextAreaAppender.textArea = console;
    console.setWrapText(false);
    console.setEditable(false);

    ContextMenu consoleContextMenu = new ContextMenu();
    MenuItem quickSaveLogMenuItem = new MenuItem("Quick Save Log");
    quickSaveLogMenuItem.setOnAction(e -> {
      try {
        String desktopPath = System.getProperty("user.home") + "/Desktop/"
            + RoombaJSSCSingleton.logDate() + ".txt"; //TODO date must be file name safe
        System.out.println(desktopPath);
        File logFile = new File(desktopPath);
        PrintWriter printWriter = new PrintWriter(logFile);
        printWriter.print(console.getText());
        printWriter.close();
      } catch (FileNotFoundException x) {
        // alert
        x.getMessage();
      }
    });
    MenuItem saveLogMenuItem = new MenuItem("Save Log");
    saveLogMenuItem.setOnAction(e -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Open Resource File");
      fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
      Stage stage = (Stage) (root.getScene().getWindow());
      File selectedFile = fileChooser.showOpenDialog(stage);
      if (selectedFile != null) {
        // save file
      }
    });
    consoleContextMenu.getItems().add(quickSaveLogMenuItem);
    consoleContextMenu.getItems().add(saveLogMenuItem);
    console.setContextMenu(consoleContextMenu);

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

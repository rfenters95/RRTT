package main.ui.root;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;
import main.core.RoombaJSSCSingleton;
import main.core.TextAreaAppender;
import main.ui.modules.drive.DriveModuleController;
import main.ui.modules.led.LightModuleController;
import main.ui.modules.sensor.SensorModuleController;
import main.ui.modules.song.SongModuleController;

public class RootController implements Initializable {

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
  private ControllerNavigationController controllerNavigationController;

  @FXML
  private SongModuleController songModuleController;

  private boolean poweredOn;

  @FXML
  private void togglePower(ActionEvent event) {
    if (poweredOn) {
      RoombaJSSCSingleton.getRoombaJSSC().powerOff();
      ImageView imageView = new ImageView("main/res/powerOff.png");
      imageView.setFitWidth(25);
      imageView.setFitHeight(25);
      powerButton.setGraphic(imageView);
      poweredOn = !poweredOn;
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
      } catch (IOException e) {
        e.printStackTrace();
      }
      ImageView imageView = new ImageView("main/res/powerOn.png");
      imageView.setFitWidth(25);
      imageView.setFitHeight(25);
      powerButton.setGraphic(imageView);
      poweredOn = !poweredOn;
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    powerButton.setFocusTraversable(false);

    Thread batteryUpdaterThread = new Thread(() -> {
      while (!Main.shutdown) {
        System.out.println("Execute!");
        double batteryPercentage = RoombaJSSCSingleton.getRoombaJSSC().batteryCharge() / 65535.0;
        Platform.runLater(() -> batteryPercentageLabel.setText(String.valueOf(batteryPercentage)));
        try {
          final int numberOfSeconds = 30;
          for (int i = 0; i < numberOfSeconds; i++) {
            // If app is in shutdown, stop sleeping and finish loop.
            if (!Main.shutdown) {
              Thread.sleep(1000);
            } else {
              break;
            }
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    batteryUpdaterThread.start();

    // Give nested module controllers access to root controller
    driveModuleController.inject(this);
    lightModuleController.inject(this);
    sensorModuleController.inject(this);
    controllerNavigationController.inject(this);
    songModuleController.inject(this);

    /*driveModuleContainer.setStyle("-fx-background-color: red;");
    lightModuleContainer.setStyle("-fx-background-color: white;");
    sensorModuleContainer.setStyle("-fx-background-color: blue;");*/

    // Get init divider position
    double initialDividerPosition = splitPane.getDividers().get(0).getPosition();

    // Prevent console from getting too big
    splitPane.getDividers().get(0).positionProperty()
        .addListener((observable, oldValue, newValue) -> {
          if (newValue.doubleValue() < initialDividerPosition) {
            splitPane.setDividerPosition(0, initialDividerPosition);
          }
        });

    // Context menu for splitPane
    ContextMenu splitPaneContextMenu = new ContextMenu();
    MenuItem showSplitPaneDividerItem = new MenuItem("Show/Reset Console");
    showSplitPaneDividerItem
        .setOnAction(e -> splitPane.setDividerPosition(0, initialDividerPosition));
    MenuItem hideSplitPaneDividerItem = new MenuItem("Hide Console");
    hideSplitPaneDividerItem.setOnAction(e -> splitPane.setDividerPosition(0, 1));
    splitPaneContextMenu.getItems().add(showSplitPaneDividerItem);
    splitPaneContextMenu.getItems().add(hideSplitPaneDividerItem);
    splitPane.setContextMenu(splitPaneContextMenu);

    // Configure TextArea
    TextAreaAppender.textArea = console;
    console.setWrapText(false);
    console.setEditable(false);

    // Context menu for Save IO
    final ContextMenu consoleContextMenu = new ContextMenu();
    MenuItem saveLogMenuItem = new MenuItem("Log: Save");
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
    MenuItem quickSaveLogMenuItem = new MenuItem("Log: Quick Save");
    quickSaveLogMenuItem.setOnAction(e -> {
      try {
        String desktopPath = System.getProperty("user.home") + "/Desktop/log-"
            + RoombaJSSCSingleton.logDateForFileName() + ".txt";
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
    consoleContextMenu.getItems().add(saveLogMenuItem);
    consoleContextMenu.getItems().add(quickSaveLogMenuItem);
    console.setContextMenu(consoleContextMenu);

  }

}

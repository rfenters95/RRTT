package ui;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import ui.main.tabs.drive.DriveTabController;
import ui.main.tabs.led.LightTabController;
import ui.main.tabs.sensor.SensorTabController;

import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    @FXML
    public VBox root;

    @FXML
    public ToolBar toolBar;

    @FXML
    public SplitPane splitPane;

    @FXML
    public JFXTabPane tabPane;

    @FXML
    public TextArea console;

    @FXML
    private DriveTabController driveTabController;

    @FXML
    private LightTabController lightTabController;

    @FXML
    private SensorTabController sensorTabController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        driveTabController.inject(this);
    }

}

package main.ui.tabs.led;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.core.Injectable;
import main.core.led.listeners.PowerColorListener;
import main.core.led.listeners.PowerIntensityListener;
import main.ui.root.RootController;

import java.net.URL;
import java.util.ResourceBundle;

public class LightTabController implements Initializable, Injectable {

    private RootController rootController;

    @FXML
    private AnchorPane ledTab;

    @FXML
    private JFXCheckBox debrisCheckBox;

    @FXML
    private JFXCheckBox dockCheckBox;

    @FXML
    private JFXCheckBox spotCheckBox;

    @FXML
    private JFXCheckBox checkRobotCheckBox;

    @FXML
    private JFXTextField powerColorTextField;

    @FXML
    private JFXTextField powerIntensityTextField;

    @FXML
    void toggle(ActionEvent event) {

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
        powerIntensityTextField.textProperty().addListener(new PowerIntensityListener(powerIntensityTextField));
    }

}

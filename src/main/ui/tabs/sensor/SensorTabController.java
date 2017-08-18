package main.ui.tabs.sensor;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.core.Injectable;
import main.core.sensor.bool.AbstractBooleanSensor;
import main.core.sensor.signal.AbstractSignalSensor;
import main.ui.root.RootController;

import java.net.URL;
import java.util.ResourceBundle;

public class SensorTabController implements Initializable, Injectable {

    private RootController rootController;

    @FXML
    private AnchorPane sensorTab;

    @FXML
    private JFXComboBox<AbstractBooleanSensor> booleanSensorComboBox;

    @FXML
    private JFXComboBox<AbstractSignalSensor> signalSensorComboBox;

    @FXML
    void readBooleanSensor(ActionEvent event) {
        AbstractBooleanSensor sensor = booleanSensorComboBox.getSelectionModel().getSelectedItem();
        boolean value = sensor.read();
    }

    @FXML
    void readSignalSensor(ActionEvent event) {
        AbstractSignalSensor sensor = signalSensorComboBox.getSelectionModel().getSelectedItem();
        int value = sensor.read();
    }

    @Override
    public void inject(RootController rootController) {
        this.rootController = rootController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}

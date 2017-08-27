package main.ui.tabs.sensor;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.core.Injectable;
import main.core.RoombaJSSCSingleton;
import main.core.sensor.bool.AbstractBooleanSensor;
import main.core.sensor.bool.BumpCenter;
import main.core.sensor.bool.BumpLeft;
import main.core.sensor.bool.BumpRight;
import main.core.sensor.signal.AbstractSignalSensor;
import main.core.sensor.signal.Wall;
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
        for (int i = 0; i < 50; i++) {
            boolean value = sensor.read();
            rootController.console.appendText(RoombaJSSCSingleton.logDate() + "\t<---> " + value + "\n");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void readSignalSensor(ActionEvent event) {
        AbstractSignalSensor sensor = signalSensorComboBox.getSelectionModel().getSelectedItem();
        for (int i = 0; i < 10; i++) {
            int value = sensor.read();
            rootController.console.appendText(RoombaJSSCSingleton.logDate() + "\t<---> " + value + "\n");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void inject(RootController rootController) {
        this.rootController = rootController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /* *********************************************
        *
        * Add boolean sensors
        *
        ********************************************** */

        booleanSensorComboBox.setVisibleRowCount(3);
        booleanSensorComboBox.getItems().add(new BumpCenter());
        booleanSensorComboBox.getItems().add(new BumpLeft());
        booleanSensorComboBox.getItems().add(new BumpRight());

        /* *********************************************
        *
        * Add signal sensors
        *
        ********************************************** */

        signalSensorComboBox.setVisibleRowCount(3);
        signalSensorComboBox.getItems().add(new Wall());

    }

}

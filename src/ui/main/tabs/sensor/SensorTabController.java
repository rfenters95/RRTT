package ui.main.tabs.sensor;

import core.Injectable;
import javafx.fxml.Initializable;
import ui.RootController;

import java.net.URL;
import java.util.ResourceBundle;

public class SensorTabController implements Initializable, Injectable {

    private RootController rootController;

    @Override
    public void inject(RootController rootController) {
        this.rootController = rootController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}

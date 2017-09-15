package main.ui.root;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.core.Injectable;

public class ControllerNavigationController implements Initializable, Injectable {

  private RootController rootController;

  @FXML
  private Label leftLabel;

  @FXML
  private JFXButton leftButton;

  @FXML
  private Label rightLabel;

  @FXML
  private JFXButton rightButton;

  @Override
  public void inject(RootController rootController) {
    this.rootController = rootController;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    leftButton.setVisible(false);
    leftLabel.setVisible(false);

    rightButton.setText("Go");
    rightLabel.setText("Song Module");

  }

}

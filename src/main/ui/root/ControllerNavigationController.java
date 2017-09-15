package main.ui.root;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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

    ImageView leftButtonImage = new ImageView("main/res/left-arrow.png");
    leftButtonImage.setFitWidth(25);
    leftButtonImage.setFitHeight(25);
    leftButton.setGraphic(leftButtonImage);
    leftButton.setVisible(false);
    leftLabel.setVisible(false);

    ImageView rightButtonImage = new ImageView("main/res/right-arrow.png");
    rightButtonImage.setFitWidth(25);
    rightButtonImage.setFitHeight(25);
    rightButton.setGraphic(rightButtonImage);
    rightLabel.setText("Song Module");

  }

}

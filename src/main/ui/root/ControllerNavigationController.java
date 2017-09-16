package main.ui.root;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import main.core.Injectable;

public class ControllerNavigationController implements Initializable, Injectable {

  private RootController rootController;

  @FXML
  private BorderPane controllerNavigation;

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

    final boolean DISABLE = true;
    leftButton.setFocusTraversable(false);
    rightButton.setFocusTraversable(false);

    ImageView leftButtonImage = new ImageView("main/res/left-arrow.png");
    leftButtonImage.setFitWidth(25);
    leftButtonImage.setFitHeight(25);
    leftButton.setGraphic(leftButtonImage);
    leftLabel.setText("Core Modules");
    leftButton.setDisable(DISABLE);
    leftLabel.setDisable(DISABLE);

    ImageView rightButtonImage = new ImageView("main/res/right-arrow.png");
    rightButtonImage.setFitWidth(25);
    rightButtonImage.setFitHeight(25);
    rightButton.setGraphic(rightButtonImage);
    rightLabel.setText("Song Module");

    leftButton.setOnAction(e -> {
      JFXTabPane tabPane = rootController.controllerNavigationTabPane;
      int currentIndex = tabPane.getSelectionModel().getSelectedIndex();
      if (currentIndex - 1 >= 0) {
        tabPane.getSelectionModel().select(currentIndex - 1);
      }
      leftButton.setDisable(DISABLE);
      leftLabel.setDisable(DISABLE);
      rightButton.setDisable(!DISABLE);
      rightLabel.setDisable(!DISABLE);
    });

    rightButton.setOnAction(e -> {
      JFXTabPane tabPane = rootController.controllerNavigationTabPane;
      int currentIndex = tabPane.getSelectionModel().getSelectedIndex();
      final int TOTAL_TABS = tabPane.getTabs().size();
      if (currentIndex + 1 < TOTAL_TABS) {
        tabPane.getSelectionModel().select(currentIndex + 1);
      } else {
        System.out.println(TOTAL_TABS);
      }
      leftButton.setDisable(!DISABLE);
      leftLabel.setDisable(!DISABLE);
      rightButton.setDisable(DISABLE);
      rightLabel.setDisable(DISABLE);
    });

  }

}

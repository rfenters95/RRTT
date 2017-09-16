package main.ui.modules.song;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import main.core.Injectable;
import main.ui.root.RootController;

public class SongModuleController implements Initializable, Injectable {


  @FXML
  private ScrollPane songModule;

  private RootController rootController;

  @Override
  public void inject(RootController rootController) {
    this.rootController = rootController;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    songModule.setFocusTraversable(false);
  }

}

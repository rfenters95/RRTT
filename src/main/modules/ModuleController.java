package main.modules;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.core.Injectable;
import main.root.RootController;

public abstract class ModuleController implements Injectable {

  @FXML
  protected JFXButton playButton;

  protected RootController rootController;
  protected boolean isPlaying = false;

  @FXML
  public abstract void play(ActionEvent event);

  @Override
  public void inject(RootController rootController) {
    this.rootController = rootController;
  }

}

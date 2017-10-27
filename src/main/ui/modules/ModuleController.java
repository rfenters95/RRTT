package main.ui.modules;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.core.Injectable;
import main.ui.root.RootController;

public abstract class ModuleController implements Injectable {

  public RootController rootController;
  public boolean isPlaying = false;

  @FXML
  public abstract void play(ActionEvent event);

  @Override
  public void inject(RootController rootController) {
    this.rootController = rootController;
  }

}
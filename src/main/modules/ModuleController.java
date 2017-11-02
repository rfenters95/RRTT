package main.modules;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
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

  public class SpaceKeyAction implements EventHandler<KeyEvent> {

    @Override
    public void handle(KeyEvent event) {
      switch (event.getCode()) {
        case SPACE:
          if (isPlaying) {
            playButton.fire();
          }
          break;
        default:
          break;
      }
    }
  }

}

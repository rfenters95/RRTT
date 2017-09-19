package main.ui.modules.song;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import main.core.Injectable;
import main.ui.root.RootController;

public class SongModuleController implements Initializable, Injectable {


  @FXML
  private ScrollPane songModule;

  private RootController rootController;

  /*
  * Consider NoteParameter class / custom control
  * then adding NoteParameters to GridPane programmatically.
  * */
  @FXML
  public void playNote(ActionEvent event) {
    System.out.println("Yolo!");
  }

  @Override
  public void inject(RootController rootController) {
    this.rootController = rootController;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    songModule.setFocusTraversable(false);
  }

}

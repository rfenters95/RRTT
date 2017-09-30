package main.ui.modules.song;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import main.core.Injectable;
import main.ui.modules.song.note.NoteControl;
import main.ui.root.RootController;

public class SongModuleController implements Initializable, Injectable {

  @FXML
  private ScrollPane songModule;

  @FXML
  private JFXComboBox<String> songNumberCB;

  @FXML
  private JFXComboBox<String> songLengthCB;

  @FXML
  private JFXComboBox<String> outputCB;

  @FXML
  private JFXComboBox<String> sleepCB;

  @FXML
  private NoteControl noteControl1;

  @FXML
  private NoteControl noteControl2;

  @FXML
  private NoteControl noteControl3;

  @FXML
  private NoteControl noteControl4;

  @FXML
  private NoteControl noteControl5;

  @FXML
  private NoteControl noteControl6;

  @FXML
  private NoteControl noteControl7;

  @FXML
  private NoteControl noteControl8;

  @FXML
  private NoteControl noteControl9;

  @FXML
  private NoteControl noteControl10;

  @FXML
  private NoteControl noteControl11;

  @FXML
  private NoteControl noteControl12;

  @FXML
  private NoteControl noteControl13;

  @FXML
  private NoteControl noteControl14;

  @FXML
  private NoteControl noteControl15;

  @FXML
  private NoteControl noteControl16;

  private RootController rootController;

  @Override
  public void inject(RootController rootController) {
    this.rootController = rootController;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    // Enable based on value of noteCountComboBox;
    final NoteControl[] noteControls = {
        noteControl1, noteControl2, noteControl3, noteControl4,
        noteControl5, noteControl6, noteControl7, noteControl8,
        noteControl9, noteControl10, noteControl11, noteControl12,
        noteControl13, noteControl14, noteControl15, noteControl16
    };

    songModule.setFocusTraversable(false);

    for (int i = 0; i < 4; i++) {
      songNumberCB.getItems().add(String.valueOf(i + 1));
    }

    for (int i = 0; i < 16; i++) {
      songLengthCB.getItems().add(String.valueOf(i + 1));
    }

    outputCB.getItems().add("Roomba");
    outputCB.getItems().add("Speakers");

    sleepCB.getItems().add("1000");

    songNumberCB.getSelectionModel().selectFirst();
    songLengthCB.getSelectionModel().selectLast();
    outputCB.getSelectionModel().selectFirst();
    sleepCB.getSelectionModel().selectFirst();

    setEnabledNotes(noteControls);
    songLengthCB.setOnAction(e -> {
      setEnabledNotes(noteControls);
    });

    // Get Reference to NoteControlCB


  }

  private void setEnabledNotes(NoteControl[] noteControls) {
    int length = Integer.parseInt(songLengthCB.getSelectionModel().getSelectedItem());
    for (int i = 0; i < noteControls.length; i++) {
      if (i < length) {
        noteControls[i].setDisable(false);
      } else {
        noteControls[i].setDisable(true);
      }
    }
  }

}

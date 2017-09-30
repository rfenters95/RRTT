package main.ui.modules.song;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import main.core.Injectable;
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
  private HBox noteControl1;

  @FXML
  private HBox noteControl2;

  @FXML
  private HBox noteControl3;

  @FXML
  private HBox noteControl4;

  @FXML
  private HBox noteControl5;

  @FXML
  private HBox noteControl6;

  @FXML
  private HBox noteControl7;

  @FXML
  private HBox noteControl8;

  @FXML
  private HBox noteControl9;

  @FXML
  private HBox noteControl10;

  @FXML
  private HBox noteControl11;

  @FXML
  private HBox noteControl12;

  @FXML
  private HBox noteControl13;

  @FXML
  private HBox noteControl14;

  @FXML
  private HBox noteControl15;

  @FXML
  private HBox noteControl16;

  private RootController rootController;

  @Override
  public void inject(RootController rootController) {
    this.rootController = rootController;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    // Enable based on value of noteCountComboBox;
    final HBox[] noteControls = {
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

  private void setEnabledNotes(HBox[] noteControls) {
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

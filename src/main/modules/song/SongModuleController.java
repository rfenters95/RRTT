package main.modules.song;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jfoenix.controls.JFXComboBox;
import com.maschel.roomba.song.RoombaSongNote;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import main.core.Injectable;
import main.core.RoombaState;
import main.modules.song.note.NoteControl;
import main.root.RootController;

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

  private ArrayList<NoteControl> noteControls = new ArrayList<>(16);
  private ArrayList<RoombaSongNote> roombaSongNotes = new ArrayList<>(16);

  private void updateSongNotes() {
    roombaSongNotes.clear();
    for (NoteControl noteControl : noteControls) {
      if (!noteControl.isDisable()) {
        roombaSongNotes.add(noteControl.getRoombaSongNote());
      }
    }
  }

  private void updateSongNoteControls() {
    for (int i = 0; i < noteControls.size(); i++) {
      noteControls.get(i).setRoombaSongNote(roombaSongNotes.get(i));
    }
  }

  @FXML
  private void play(ActionEvent event) {
    updateSongNotes();
    int songNumber = Integer.parseInt(songNumberCB.getSelectionModel().getSelectedItem());
    int sleepTime = Integer.parseInt(sleepCB.getSelectionModel().getSelectedItem());
    RoombaSongNote[] songNotesArray = roombaSongNotes
        .toArray(new RoombaSongNote[roombaSongNotes.size()]);
    RoombaState.getRoomba().song(songNumber, songNotesArray, 125); // what is tempo?
    RoombaState.getRoomba().play(songNumber);
    RoombaState.getRoomba().sleep(sleepTime);
    /*readRoombaSongNotes();
    updateSongNoteControls();*/
  }

  @Override
  public void inject(RootController rootController) {
    this.rootController = rootController;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    songModule.setFocusTraversable(false);

    // Enable based on value of noteCountComboBox;
    noteControls.add(noteControl1);
    noteControls.add(noteControl2);
    noteControls.add(noteControl3);
    noteControls.add(noteControl4);
    noteControls.add(noteControl5);
    noteControls.add(noteControl6);
    noteControls.add(noteControl7);
    noteControls.add(noteControl8);
    noteControls.add(noteControl9);
    noteControls.add(noteControl10);
    noteControls.add(noteControl11);
    noteControls.add(noteControl12);
    noteControls.add(noteControl13);
    noteControls.add(noteControl14);
    noteControls.add(noteControl15);
    noteControls.add(noteControl16);

    for (int i = 0; i < 4; i++) {
      songNumberCB.getItems().add(String.valueOf(i + 1));
    }

    for (int i = 0; i < 16; i++) {
      songLengthCB.getItems().add(String.valueOf(i + 1));
    }

    outputCB.getItems().add("Roomba");
    outputCB.getItems().add("Speakers");
    outputCB.getSelectionModel().selectFirst();

    // Change to JFXTextField
    sleepCB.getItems().add("1000");
    sleepCB.getItems().add("2000");
    sleepCB.getSelectionModel().selectFirst();

    // Initialize ComboBoxes
    songNumberCB.getSelectionModel().selectFirst();
    songLengthCB.getSelectionModel().selectLast();
    outputCB.getSelectionModel().selectFirst();
    sleepCB.getSelectionModel().selectFirst();

    setEnabledNotes();
    songLengthCB.setOnAction(e -> setEnabledNotes());
  }

  private void setEnabledNotes() {
    int length = Integer.parseInt(songLengthCB.getSelectionModel().getSelectedItem());
    for (int i = 0; i < noteControls.size(); i++) {
      if (i < length) {
        noteControls.get(i).setDisable(false);
      } else {
        noteControls.get(i).setDisable(true);
      }
    }
  }

  private void saveRoombaSongNotes() {
    updateSongNotes();
    Gson gson = new Gson();
    try (FileWriter fileWriter = new FileWriter("sample.json")) {
      gson.toJson(roombaSongNotes, fileWriter);
    } catch (IOException ignored) {
    }
  }

  private void readRoombaSongNotes() {
    Gson gson = new Gson();
    try (Reader reader = new FileReader("sample.json")) {
      // Convert JSON to ArrayList of RoombaSongNote's
      Type listType = new TypeToken<ArrayList<RoombaSongNote>>() {
      }.getType();
      roombaSongNotes = gson.fromJson(reader, listType);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}

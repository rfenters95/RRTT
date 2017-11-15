package main.modules.song.note;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.maschel.roomba.RoombaJSSC;
import com.maschel.roomba.song.RoombaNote;
import com.maschel.roomba.song.RoombaNoteDuration;
import com.maschel.roomba.song.RoombaSongNote;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import main.alerts.InvalidInputAlert;
import main.core.RoombaState;

import java.io.IOException;

public class NoteControl extends HBox {

  @FXML
  private JFXComboBox<RoombaNote> roombaNoteComboBox;

  @FXML
  private JFXComboBox<RoombaNoteDuration> roombaNoteDurationComboBox;

  @FXML
  private JFXButton playButton;

  @FXML
  private Label noteLabel;

  public NoteControl() {
    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("Note.fxml"));
      loader.setRoot(this);
      loader.setController(this);
      loader.load();

      ImageView imageView = new ImageView("main/res/play.png");
      imageView.setFitWidth(25);
      imageView.setFitHeight(25);
      playButton.setGraphic(imageView);

      roombaNoteComboBox.getItems().addAll(RoombaNote.values());
      roombaNoteDurationComboBox.getItems().addAll(RoombaNoteDuration.values());

    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  @FXML
  public void play(ActionEvent event) {
    RoombaSongNote songNote = getRoombaSongNote();
    if (songNote != null) {
      RoombaJSSC roombaJSSC = RoombaState.getRoomba();
      RoombaSongNote[] songNotes = {songNote};
        roombaJSSC.song(0, songNotes, 125);
        roombaJSSC.play(0);
    } else {
      String header = getText();
      String content = "Invalid input! Please select a note and duration!";
      InvalidInputAlert invalidInputAlert = new InvalidInputAlert(header, content);
      invalidInputAlert.show();
    }
  }

  public String getText() {
    return textProperty().get();
  }

  public void setText(String value) {
    textProperty().set(value);
  }

  public StringProperty textProperty() {
    return noteLabel.textProperty();
  }

  public RoombaSongNote getRoombaSongNote() {
    RoombaNote note = roombaNoteComboBox.getSelectionModel().getSelectedItem();
    RoombaNoteDuration duration = roombaNoteDurationComboBox.getSelectionModel().getSelectedItem();
    if (note != null && duration != null) {
      return new RoombaSongNote(note, duration);
    }
    return null;
  }

  public JFXButton getPlayButton() {
    return playButton;
  }

  public Label getNoteLabel() {
    return noteLabel;
  }
}

package main.ui.modules.song.note;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import main.core.song.Note;

public class NoteControl extends HBox {

  @FXML
  private JFXComboBox<String> pitchComboBox;

  @FXML
  private JFXComboBox<String> durationComboBox;

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

      populateNoteComboBox();
      populateDurationComboBox();

    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  @FXML
  private void play(ActionEvent event) {
    String pitch = pitchComboBox.getSelectionModel().getSelectedItem();
    String duration = durationComboBox.getSelectionModel().getSelectedItem();
    Note note = new Note(pitch, duration);
    note.play();
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

  private void populateNoteComboBox() {
    pitchComboBox.getItems().add("A");
    pitchComboBox.getItems().add("A#");
    pitchComboBox.getItems().add("B");
    pitchComboBox.getItems().add("C");
    pitchComboBox.getItems().add("C#");
    pitchComboBox.getItems().add("D");
    pitchComboBox.getItems().add("D#");
    pitchComboBox.getItems().add("E");
    pitchComboBox.getItems().add("F");
    pitchComboBox.getItems().add("F#");
    pitchComboBox.getItems().add("G");
    pitchComboBox.getItems().add("G#");
  }

  private void populateDurationComboBox() {
    durationComboBox.getItems().add("0 sec");
    for (int i = 1; i < 64; i++) {
      durationComboBox.getItems().add(String.valueOf(i) + " / 64 sec");
    }
    durationComboBox.getItems().add("1 sec");
  }

  public JFXComboBox<String> getPitchComboBox() {
    return pitchComboBox;
  }

  public JFXComboBox<String> getDurationComboBox() {
    return durationComboBox;
  }

  public JFXButton getPlayButton() {
    return playButton;
  }

  public Label getNoteLabel() {
    return noteLabel;
  }
}

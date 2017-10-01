package main.ui.modules.song.note;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.maschel.roomba.song.RoombaNote;
import com.maschel.roomba.song.RoombaNoteDuration;
import com.maschel.roomba.song.RoombaSongNote;
import java.io.IOException;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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
  private void play(ActionEvent event) {
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
    return new RoombaSongNote(note, duration);
  }

  public JFXButton getPlayButton() {
    return playButton;
  }

  public Label getNoteLabel() {
    return noteLabel;
  }
}

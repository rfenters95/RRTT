package main.ui.modules.song.note;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class NoteControl extends HBox {

  @FXML
  private JFXComboBox<String> noteComboBox;

  @FXML
  private JFXComboBox<String> durationComboBox;

  @FXML
  private JFXButton playButton;

  @FXML
  private Label noteLabel;

  public NoteControl() {

    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Note.fxml"));
      fxmlLoader.setRoot(this);
      fxmlLoader.setController(this);
      fxmlLoader.load();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }

    ImageView imageView = new ImageView("main/res/play.png");
    imageView.setFitWidth(25);
    imageView.setFitHeight(25);
    playButton.setGraphic(imageView);

    populateNoteComboBox();
    populateDurationComboBox();

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
    noteComboBox.getItems().add("A");
    noteComboBox.getItems().add("A#");
    noteComboBox.getItems().add("B");
    noteComboBox.getItems().add("C");
    noteComboBox.getItems().add("C#");
    noteComboBox.getItems().add("D");
    noteComboBox.getItems().add("D#");
    noteComboBox.getItems().add("E");
    noteComboBox.getItems().add("F");
    noteComboBox.getItems().add("F#");
    noteComboBox.getItems().add("G");
    noteComboBox.getItems().add("G#");
  }

  private void populateDurationComboBox() {
    durationComboBox.getItems().add("0 sec");
    for (int i = 1; i < 64; i++) {
      durationComboBox.getItems().add(String.valueOf(i) + " / 64 sec");
    }
    durationComboBox.getItems().add("1 sec");
  }

}

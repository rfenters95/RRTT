package main.core.song;

import com.maschel.roomba.RoombaJSSC;
import main.core.RoombaJSSCSingleton;

public class Note {

  private String note;
  private String duration;

  public Note(String note, String duration) {
    this.note = note;
    this.duration = duration;
  }

  public void play() {
    RoombaJSSC roombaJSSC = RoombaJSSCSingleton.getRoombaJSSC();
  }

}

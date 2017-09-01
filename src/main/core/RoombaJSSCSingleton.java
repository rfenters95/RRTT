package main.core;

import com.maschel.roomba.RoombaJSSC;
import com.maschel.roomba.RoombaJSSCSerial;
import com.sun.javafx.binding.StringFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* Ensures only one instance of RoombaJSSC is created.
* */
public class RoombaJSSCSingleton {

  private static final RoombaJSSC roombaJSSC = new RoombaJSSCSerial();
  private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
      StringFormatter.format("%12s", "hh:mm:ss.SSS").getValue());

  private RoombaJSSCSingleton() {
  }

  public static RoombaJSSC getRoombaJSSC() {
    return roombaJSSC;
  }

  public static String logDate() {
    return simpleDateFormat.format(new Date());
  }
}

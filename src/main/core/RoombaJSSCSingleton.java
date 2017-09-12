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
      StringFormatter.format("%12s", "hh:mm:ss:SSS").getValue());
  private static final SimpleDateFormat fileSafeDateFormat = new SimpleDateFormat(
      StringFormatter.format("%s", "hh.mm.ss").getValue());
  private static boolean isConnected;

  private RoombaJSSCSingleton() {
  }

  public static void connect(String port, RoombaStartMode startMode) {
    roombaJSSC.connect(port);
    switch (startMode) {
      case FULL:
        roombaJSSC.fullMode();
        break;
      case SAFE:
        roombaJSSC.safeMode();
        break;
      default:
        break;
    }
    isConnected = true;
  }

  public static void powerOff() {
    roombaJSSC.powerOff();
    isConnected = false;
  }

  public static boolean isConnected() {
    return isConnected;
  }

  public static RoombaJSSC getRoombaJSSC() {
    return roombaJSSC;
  }

  public static String logDate() {
    return simpleDateFormat.format(new Date());
  }

  public static String logDateForFileName() {
    return fileSafeDateFormat.format(new Date());
  }

}

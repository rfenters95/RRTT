package main.core;

import com.maschel.roomba.RoombaJSSC;
import com.maschel.roomba.RoombaJSSCSerial;

/*
* Maintains Roomba state information such as connection
* information, etc.
*
* Implemented using the Singleton pattern to ensure only
* one instance of RoombaJSSC is created.
* */
public class RoombaState {

  /* *********************************************
  *
  * Singleton setup
  *
  * ********************************************** */
  private final static RoombaJSSC roomba = new RoombaJSSCSerial();

  private RoombaState() {
  }

  /* *********************************************
  *
  * Instance members
  *
  * ********************************************** */
  private static boolean connected = false;
  private static boolean locked = false;

  public static RoombaJSSC getRoomba() {
    return RoombaState.roomba;
  }

  /* *********************************************
  *
  * Instance methods
  *
  * ********************************************** */

  /*
  * Determines connection status of Roomba.
  * @return Roomba connection status.
  * */
  public static boolean isConnected() {
    return connected;
  }

  /*
  * Opens a connection with the Roomba over RoombaJSSC
  * using the port and RoombaStartMode provided.
  *
  * @param port Port address of Roomba
  * @param mode RoombaStartMode detailing which mode to Roomba in.
  * */
  public static void connect(String port, RoombaStartMode mode) {
    roomba.connect(port);
    roomba.start();
    switch (mode) {
      case FULL:
        roomba.fullMode();
        break;
      case SAFE:
        roomba.safeMode();
        break;
      default:
        break;
    }
    RoombaState.connected = true;
  }

  /*
  * Used to shutdown Roomba correctly.
  * */
  public static void powerOff() {
    roomba.stop();
    roomba.disconnect();
    RoombaState.connected = false;
  }
}

package main.core;

import com.maschel.roomba.RoombaJSSC;
import com.maschel.roomba.RoombaJSSCSerial;

public class RoombaState {

  /* *********************************************
  *
  * Singleton setup
  *
  * ********************************************** */
  private final static RoombaJSSC roomba = new RoombaJSSCSerial();

  private RoombaState() {
  }

  public static RoombaJSSC getInstance() {
    return RoombaState.roomba;
  }

  /* *********************************************
  *
  * Instance members
  *
  * ********************************************** */
  private boolean connected = false;
  private boolean locked = false;

  /* *********************************************
  *
  * Instance methods
  *
  * ********************************************** */

  /*
  * Opens a connection with the Roomba over RoombaJSSC
  * using the port and RoombaStartMode provided.
  *
  * @param port Port address of Roomba
  * @param mode RoombaStartMode detailing which mode to Roomba in.
  * */
  public void connect(String port, RoombaStartMode mode) {
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
    this.connected = true;
  }

  /*
  * Used to shutdown Roomba correctly.
  * */
  public void disconnect() {
    roomba.stop();
    roomba.disconnect();
    this.connected = false;
  }
}

package main.core;

import com.maschel.roomba.RoombaJSSC;
import com.maschel.roomba.RoombaJSSCSerial;

public class RoombaState {

  /* *********************************************
  *
  * Singleton setup
  *
  * ********************************************** */
  private static RoombaState instance = new RoombaState();
  /* *********************************************
  *
  * Instance members
  *
  * ********************************************** */
  private final RoombaJSSC roomba = new RoombaJSSCSerial();
  private RunnableModule module = null;
  private boolean connected = false;
  private boolean locked = false;

  private RoombaState() {
  }

  public static RoombaState getInstance() {
    return RoombaState.instance;
  }

  /* *********************************************
  *
  * Instance methods
  *
  * ********************************************** */
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

  public void disconnect() {
    roomba.stop();
    roomba.disconnect();
    this.connected = false;
  }

  public RoombaJSSC getRoomba() {
    return roomba;
  }

  public boolean isLocked() {
    return this.locked;
  }

  public void lock(RunnableModule module) {
    this.module = module;
    this.locked = true;
  }

  public void unlock() {
    this.module = null;
    this.locked = false;
  }
}

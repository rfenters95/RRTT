package main.core.sensor.signal;

import main.core.RoombaState;

public class LightBumpFrontLeftSignal extends AbstractSignalSensor {

  @Override
  public int read() {
    RoombaState.getRoomba().updateSensors();
    return RoombaState.getRoomba().lightBumperSignalFrontLeft();
  }

}
package main.core.sensor.signal;

import main.core.RoombaState;

public class LightBumpCenterLeftSignal extends AbstractSignalSensor {

  @Override
  public int read() {
    RoombaState.getRoomba().updateSensors();
    return RoombaState.getRoomba().lightBumperSignalLeft();
  }

}
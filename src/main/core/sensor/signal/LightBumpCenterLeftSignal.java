package main.core.sensor.signal;

import main.core.RoombaJSSCSingleton;

public class LightBumpCenterLeftSignal extends AbstractSignalSensor {

  @Override
  public int read() {
    RoombaJSSCSingleton.getRoombaJSSC().updateSensors();
    return RoombaJSSCSingleton.getRoombaJSSC().lightBumperSignalLeft();
  }

}
package main.core.sensor.signal;

import main.core.RoombaJSSCSingleton;

public class LightBumpCenterRightSignal extends AbstractSignalSensor {

  @Override
  public int read() {
    RoombaJSSCSingleton.getRoombaJSSC().updateSensors();
    return RoombaJSSCSingleton.getRoombaJSSC().lightBumperSignalCenterRight();
  }

}
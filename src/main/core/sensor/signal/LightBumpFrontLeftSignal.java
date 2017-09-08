package main.core.sensor.signal;

import main.core.RoombaJSSCSingleton;

public class LightBumpFrontLeftSignal extends AbstractSignalSensor {

  @Override
  public int read() {
    RoombaJSSCSingleton.getRoombaJSSC().updateSensors();
    return RoombaJSSCSingleton.getRoombaJSSC().lightBumperSignalFrontLeft();
  }

}
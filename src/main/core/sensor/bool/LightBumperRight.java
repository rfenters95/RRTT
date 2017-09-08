package main.core.sensor.bool;

import main.core.RoombaJSSCSingleton;

public class LightBumperRight extends AbstractBooleanSensor {

  @Override
  public boolean read() {
    RoombaJSSCSingleton.getRoombaJSSC().updateSensors();
    return RoombaJSSCSingleton.getRoombaJSSC().lightBumperRight();
  }

}
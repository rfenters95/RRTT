package main.core.sensor.bool;

import main.core.RoombaJSSCSingleton;

public class CliffFrontRight extends AbstractBooleanSensor {

  @Override
  public boolean read() {
    RoombaJSSCSingleton.getRoombaJSSC().updateSensors();
    return RoombaJSSCSingleton.getRoombaJSSC().cliffFrontRight();
  }

}
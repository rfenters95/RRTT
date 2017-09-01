package main.core.sensor.bool;

import main.core.RoombaJSSCSingleton;

/*
* Requests right bump sensor information from the Roomba.
* */
public class BumpRight extends AbstractBooleanSensor {

  @Override
  public boolean read() {
    RoombaJSSCSingleton.getRoombaJSSC().updateSensors();
    return RoombaJSSCSingleton.getRoombaJSSC().bumpRight();
  }

  @Override
  public String toString() {
    return "Bump Right";
  }

}

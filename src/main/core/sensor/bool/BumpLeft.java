package main.core.sensor.bool;

import main.core.RoombaJSSCSingleton;

/*
* Requests left bump sensor information from the Roomba.
* */
public class BumpLeft extends AbstractBooleanSensor {

  @Override
  public boolean read() {
    RoombaJSSCSingleton.getRoombaJSSC().updateSensors();
    return RoombaJSSCSingleton.getRoombaJSSC().bumpLeft();
  }

}

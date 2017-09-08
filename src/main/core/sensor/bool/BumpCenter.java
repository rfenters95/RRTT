package main.core.sensor.bool;

import main.core.RoombaJSSCSingleton;

/*
* Requests center bump sensor information from the Roomba.
* */
public class BumpCenter extends AbstractBooleanSensor {

  @Override
  public boolean read() {
    RoombaJSSCSingleton.getRoombaJSSC().updateSensors();
    boolean left = RoombaJSSCSingleton.getRoombaJSSC().bumpLeft();
    boolean right = RoombaJSSCSingleton.getRoombaJSSC().bumpRight();
    return left && right;
  }

  @Override
  public String toString() {
    return "Bump Center";
  }

}

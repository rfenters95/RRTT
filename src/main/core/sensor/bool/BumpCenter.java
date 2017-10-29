package main.core.sensor.bool;

import main.core.RoombaState;

/*
* Requests center bump sensor information from the Roomba.
* */
public class BumpCenter extends AbstractBooleanSensor {

  @Override
  public boolean read() {
    RoombaState.getRoomba().updateSensors();
    boolean left = RoombaState.getRoomba().bumpLeft();
    boolean right = RoombaState.getRoomba().bumpRight();
    return left && right;
  }

}

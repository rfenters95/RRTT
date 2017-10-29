package main.core.sensor.bool;

import main.core.RoombaState;

/*
* Requests right bump sensor information from the Roomba.
* */
public class BumpRight extends AbstractBooleanSensor {

  @Override
  public boolean read() {
    RoombaState.getRoomba().updateSensors();
    return RoombaState.getRoomba().bumpRight();
  }

}

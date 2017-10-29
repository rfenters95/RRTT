package main.core.sensor.bool;

import main.core.RoombaState;

/*
* Requests left bump sensor information from the Roomba.
* */
public class BumpLeft extends AbstractBooleanSensor {

  @Override
  public boolean read() {
    RoombaState.getRoomba().updateSensors();
    return RoombaState.getRoomba().bumpLeft();
  }

}

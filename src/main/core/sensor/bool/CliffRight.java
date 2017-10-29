package main.core.sensor.bool;

import main.core.RoombaState;

public class CliffRight extends AbstractBooleanSensor {

  @Override
  public boolean read() {
    RoombaState.getRoomba().updateSensors();
    return RoombaState.getRoomba().cliffRight();
  }

}
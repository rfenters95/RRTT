package main.core.sensor.signal;

import main.core.RoombaJSSCSingleton;

/*
* Requests wall signal sensor information from the Roomba.
* */
public class CliffFrontRightSignal extends AbstractSignalSensor {

  @Override
  public int read() {
    RoombaJSSCSingleton.getRoombaJSSC().updateSensors();
    return RoombaJSSCSingleton.getRoombaJSSC().cliffSignalFrontRight();
  }

}

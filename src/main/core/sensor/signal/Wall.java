package main.core.sensor.signal;

import main.core.RoombaJSSCSingleton;

public class Wall extends AbstractSignalSensor {

    @Override
    public int read() {
        RoombaJSSCSingleton.getRoombaJSSC().updateSensors();
        return RoombaJSSCSingleton.getRoombaJSSC().wallSignal();
    }

    @Override
    public String toString() {
        return "Wall Signal";
    }

}

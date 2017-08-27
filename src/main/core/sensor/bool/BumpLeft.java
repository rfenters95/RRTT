package main.core.sensor.bool;

import main.core.RoombaJSSCSingleton;

public class BumpLeft extends AbstractBooleanSensor {

    @Override
    public boolean read() {
        RoombaJSSCSingleton.getRoombaJSSC().updateSensors();
        return RoombaJSSCSingleton.getRoombaJSSC().bumpLeft();
    }

    @Override
    public String toString() {
        return "Bump Left";
    }

}

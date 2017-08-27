package main.core.sensor.bool;

import main.core.RoombaJSSCSingleton;

public class BumpCenter extends AbstractBooleanSensor {

    @Override
    public boolean read() {
        RoombaJSSCSingleton.getRoombaJSSC().updateSensors();
        return RoombaJSSCSingleton.getRoombaJSSC().bumpBoth();
    }

    @Override
    public String toString() {
        return "Bump Center";
    }

}

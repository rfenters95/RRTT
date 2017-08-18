package main.core.sensor.bool;

public class BumpLeft extends AbstractBooleanSensor {

    @Override
    public boolean read() {
        return false;
    }

    @Override
    public String toString() {
        return "Bump Left";
    }

}

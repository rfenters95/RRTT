package main.core.sensor.bool;

public class BumpRight extends AbstractBooleanSensor {

    @Override
    public boolean read() {
        return false;
    }

    @Override
    public String toString() {
        return "Bump Right";
    }

}

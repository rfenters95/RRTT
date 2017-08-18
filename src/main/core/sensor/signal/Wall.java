package main.core.sensor.signal;

public class Wall extends AbstractSignalSensor {

    @Override
    public int read() {
        return 0;
    }

    @Override
    public String toString() {
        return "Wall";
    }

}

package main.core.sensor.signal;

import java.util.ArrayList;

public abstract class AbstractSignalSensor {

    private static ArrayList<AbstractSignalSensor> list = new ArrayList<>();

    public AbstractSignalSensor(AbstractSignalSensor sensor) {
        list.add(sensor);
    }

    public abstract int read();

    public static ArrayList<AbstractSignalSensor> getList() {
        return list;
    }

}

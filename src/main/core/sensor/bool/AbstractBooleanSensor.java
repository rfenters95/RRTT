package main.core.sensor.bool;

import java.util.ArrayList;

public abstract class AbstractBooleanSensor {

    private static ArrayList<AbstractBooleanSensor> list = new ArrayList<>();

    public AbstractBooleanSensor(AbstractBooleanSensor sensor) {
        list.add(sensor);
    }

    public abstract boolean read();

    public static ArrayList<AbstractBooleanSensor> getList() {
        return list;
    }

}

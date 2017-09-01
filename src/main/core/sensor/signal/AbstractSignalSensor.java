package main.core.sensor.signal;

/*
* An abstract signal sensor used to make
* polymorphic calls to read signal sensors.
* */
public abstract class AbstractSignalSensor {

  /*
  * Reads a Roomba signal sensor.
  * @return int value of a concrete signal sensor.
  * */
  public abstract int read();

}

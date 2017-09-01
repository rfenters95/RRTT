package main.core.sensor.bool;

/*
* An abstract boolean sensor used to make
* polymorphic calls to read boolean sensors.
* */
public abstract class AbstractBooleanSensor {

  /*
  * Reads a Roomba boolean sensor.
  * @return boolean value of a concrete boolean sensor.
  * */
  public abstract boolean read();

}

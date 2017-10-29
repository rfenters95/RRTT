package main.alerts;

public class InvalidPowerIntensityAlert extends InvalidInputAlert {

  public InvalidPowerIntensityAlert() {
    super("Power Intensity (%)", "Invalid Input! Range [0, 255]");
  }

}

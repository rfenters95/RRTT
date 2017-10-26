package main.core.led.listeners;

import com.jfoenix.controls.JFXTextField;

/*
* Custom ChangeListener for the LightTabController.
* Checks both the value and format for PowerIntensity values in Roomba LED
* command parameters.
* */
public class PowerIntensityListener extends AbstractLEDListener {

  /*
  * Constructs PowerIntensityListener
  *
  * @param promptText promptText for TextField.
  * @param textField reference of TextField of which listener is attached.
  * */
  public PowerIntensityListener(JFXTextField textField) {
    super("Power Intensity", textField);
  }

}

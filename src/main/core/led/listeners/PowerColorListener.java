package main.core.led.listeners;

import com.jfoenix.controls.JFXTextField;

/*
* Custom ChangeListener for the LightTabController.
* Checks both the value and format for PowerColor values in Roomba LED
* command parameters.
* */
public class PowerColorListener extends AbstractLEDListener {

  /*
  * Constructs PowerColorListener
  *
  * @param promptText promptText for TextField.
  * @param textField reference of TextField of which listener is attached.
  * */
  public PowerColorListener(JFXTextField textField) {
      super("Power Color", textField);
  }

}

package main.core.led.listeners;

import com.jfoenix.controls.JFXTextField;

public class PowerColorListener extends AbstractLEDListener {

  public PowerColorListener(JFXTextField textField) {
    super("Power Color", textField);
  }

}

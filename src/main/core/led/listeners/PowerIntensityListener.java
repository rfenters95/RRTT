package main.core.led.listeners;

import com.jfoenix.controls.JFXTextField;

public class PowerIntensityListener extends AbstractLEDListener {

  public PowerIntensityListener(JFXTextField textField) {
    super("Power Intensity", textField);
  }
}

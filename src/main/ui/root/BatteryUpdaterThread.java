package main.ui.root;

import javafx.application.Platform;
import javafx.scene.control.Label;
import main.Main;
import main.core.RoombaJSSCSingleton;

public class BatteryUpdaterThread extends Thread {

  private Label batteryLabel;

  public BatteryUpdaterThread(Label batteryLabel) {
    this.batteryLabel = batteryLabel;
  }

  @Override
  public void run() {
    while (!Main.shutdown && RoombaJSSCSingleton.isConnected()) {
      System.out.println("Execute!");
      double batteryPercentage = RoombaJSSCSingleton.getRoombaJSSC().batteryCharge() / 65535.0;
      Platform.runLater(() -> batteryLabel.setText(String.valueOf(batteryPercentage)));
      try {
        final int numberOfSeconds = 30;
        for (int i = 0; i < numberOfSeconds; i++) {
          if (!Main.shutdown) {
            Thread.sleep(1000);
          } else {
            break;
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}

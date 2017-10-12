package main.ui.root;

import java.text.DecimalFormat;
import javafx.application.Platform;
import javafx.scene.control.Label;
import main.Main;
import main.core.RoombaJSSCSingleton;

public class BatteryUpdaterThread extends Thread {

  private Label batteryLabel;

  BatteryUpdaterThread(Label batteryLabel) {
    this.batteryLabel = batteryLabel;
  }

  /*
  * Determine if thread should end
  * @return true if app has not shutdown and Roomba is connected
  * */
  private boolean shouldRun() {
    return !Main.shutdown && RoombaJSSCSingleton.isConnected();
  }

  @Override
  public void run() {
    while (shouldRun()) {
      System.out.println("Execute!");
      double batteryPercentage = RoombaJSSCSingleton.getRoombaJSSC().batteryCharge() / 65535.0;
      DecimalFormat decimalFormat = new DecimalFormat("#0.00");
      Platform.runLater(() -> batteryLabel.setText(decimalFormat.format(batteryPercentage)));
      try {
        final int numberOfMinutes = 1;
        final int iterations = numberOfMinutes * 60 / 2;
        for (int i = 0; i < iterations; i++) {
          if (shouldRun()) {
            Thread.sleep(500);
          } else {
            break;
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Thread has died!");
  }

}

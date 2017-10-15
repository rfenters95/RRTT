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

  private void sleepMinutes() {
    try {
      final int minutes = 1;
      final int minuteToSecondConversionFactor = 60;
      final int halfMillisToSecondFactor = 2;
      int iterations = minutes * minuteToSecondConversionFactor * halfMillisToSecondFactor;
      for (int i = 0; i < iterations; i++) {
        if (shouldRun()) {
          Thread.sleep(500);
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private double getBatteryPercentage() {
    RoombaJSSCSingleton.getRoombaJSSC().updateSensors();
    RoombaJSSCSingleton.getRoombaJSSC().sleep(50);
    int batteryCharge = RoombaJSSCSingleton.getRoombaJSSC().batteryCharge();
    //System.out.println(batteryCharge);
    final double maxBatteryCharge = 65535.0;
    return (batteryCharge / maxBatteryCharge) * 100;
  }

  private String getFormattedBatteryPercentage() {
    DecimalFormat decimalFormat = new DecimalFormat("##0.00");
    return decimalFormat.format(getBatteryPercentage()) + "%";
  }

  @Override
  public void run() {
    while (shouldRun()) {
      System.out.println("Execute!");
      Platform.runLater(() -> batteryLabel.setText(getFormattedBatteryPercentage()));
      sleepMinutes();
    }
    System.out.println("Thread has died!");
  }

}

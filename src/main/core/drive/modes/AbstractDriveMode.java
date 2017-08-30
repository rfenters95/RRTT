package main.core.drive.modes;

import com.jfoenix.controls.JFXTextField;
import main.core.drive.listeners.AbstractDriveListener;

public abstract class AbstractDriveMode {

  private static final String velocityErrorPrompt = "Invalid Input! Range [0, 500]";
  private static final String radiusErrorPrompt = "Invalid Input! Range [0, 2000]";
  static JFXTextField textField1;
  static JFXTextField textField2;
  static AbstractDriveListener textField1listener;
  static AbstractDriveListener textField2listener;
  private String textField1Prompt;
  private String textField2Prompt;
  private boolean isEnabled;

  /* *********************************************
  *
  * Instance methods
  *
  ********************************************** */

  public static void setTextFields(JFXTextField textField1, JFXTextField textField2) {
    AbstractDriveMode.textField1 = textField1;
    AbstractDriveMode.textField2 = textField2;
  }

  public static String getVelocityErrorPrompt() {
    return velocityErrorPrompt;
  }

  public static String getRadiusErrorPrompt() {
    return radiusErrorPrompt;
  }

  void setDefaultText() {
    textField1.setText("0");
    textField2.setText("0");
  }

  void setDefaultPromptText() {
    textField1.setPromptText(getTextField1Prompt());
    textField2.setPromptText(getTextField2Prompt());
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean enabled) {
    isEnabled = enabled;
  }

  public abstract void forward(int input1, int input2);

  public abstract void reverse(int input1, int input2);

  /* *********************************************
  *
  * Static methods
  *
  ********************************************** */

  public abstract void rotateLeft(int input1, int input2);

  /* *********************************************
  *
  * Abstract methods
  *
  ********************************************** */

  public abstract void rotateRight(int input1, int input2);

  public abstract void stop();

  /* *********************************************
  *
  * Getters & Setters
  *
  ********************************************** */

  public abstract void initialize();

  public abstract void swapListener();

  public String getTextField1Prompt() {
    return textField1Prompt;
  }

  void setTextField1Prompt(String textField1Prompt) {
    this.textField1Prompt = textField1Prompt;
  }

  public String getTextField2Prompt() {
    return textField2Prompt;
  }

  void setTextField2Prompt(String textField2Prompt) {
    this.textField2Prompt = textField2Prompt;
  }

  /* *********************************************
  *
  * Enum
  *
  ********************************************** */

  public enum Position {
    LEFT, RIGHT
  }

}

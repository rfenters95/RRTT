package main.core.drive.modes;

import com.jfoenix.controls.JFXTextField;
import main.core.drive.listeners.AbstractDriveListener;

/*
* DriveModes configure the DriveModuleController by setting
* parameter listeners and button actions
* for specific Drive commands.
* */
public abstract class AbstractDriveMode {

  /* *********************************************
  *
  * Static members
  *
  ********************************************** */
  private static final String VELOCITY_ERROR_PROMPT = "Invalid Input! Range [-500, 500]";
  private static final String RADIUS_ERROR_PROMPT = "Invalid Input! Range [-2000, 2000]";
  static JFXTextField textField1;
  static JFXTextField textField2;
  static AbstractDriveListener textField1listener;
  static AbstractDriveListener textField2listener;

  /* *********************************************
  *
  * Instance members
  *
  ********************************************** */
  private String textField1Prompt;
  private String textField2Prompt;
  private boolean isEnabled;

  /* *********************************************
  *
  * Static methods
  *
  ********************************************** */

  public static void setTextFields(JFXTextField textField1, JFXTextField textField2) {
    AbstractDriveMode.textField1 = textField1;
    AbstractDriveMode.textField2 = textField2;
  }

  public static String getVelocityErrorPrompt() {
    return VELOCITY_ERROR_PROMPT;
  }

  public static String getRadiusErrorPrompt() {
    return RADIUS_ERROR_PROMPT;
  }

  public static void disableFields() {
    textField1.setDisable(true);
    textField2.setDisable(true);
  }

  public static void enableFields() {
    textField1.setDisable(false);
    textField2.setDisable(false);
  }

  /*
  * Determine if value is a valid Roomba velocity value.
  *
  * @param value Extracted TextField input.
  * @return True if value is an acceptable velocity parameter.
  * */
  boolean hasValidVelocity(int value) {
    return (value >= -500 && value <= 500);
  }

  /*
  * Determine if value is a valid Roomba radius value.
  *
  * @param value Extracted TextField input.
  * @return True if value is an acceptable radius parameter.
  * */
  boolean hasValidRadius(int value) {
    return ((value >= -2000 && value <= 2000) || (value == 32767 || value == 32768));
  }

  public boolean hasValidParameters() {
    return isInput1Valid() && isInput2Valid();
  }

  public void alert() {
    if (!isInput1Valid()) {
      parameterOneErrorAlert();
    } else {
      parameterTwoErrorAlert();
    }
  }

  /* *********************************************
  *
  * Abstract methods
  *
  ********************************************** */

  public abstract void initialize();

  public abstract void swapListener();

  public abstract void move();

  public abstract void stop();

  public abstract boolean isInput1Valid();

  public abstract boolean isInput2Valid();

  public abstract void parameterOneErrorAlert();

  public abstract void parameterTwoErrorAlert();

  /* *********************************************
  *
  * Getters & Setters
  *
  ********************************************** */

  public static int getTextField1Input() throws NumberFormatException {
    return Integer.parseInt(textField1.getText());
  }

  public static int getTextField2Input() throws NumberFormatException {
    return Integer.parseInt(textField2.getText());
  }

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

  boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean enabled) {
    isEnabled = enabled;
  }

  void setDefaultText() {
    textField1.setText("0");
    textField2.setText("0");
  }

  void setDefaultPromptText() {
    textField1.setPromptText(getTextField1Prompt());
    textField2.setPromptText(getTextField2Prompt());
  }

  /* *********************************************
  *
  * Enums
  *
  ********************************************** */

  public enum Position {
    LEFT, RIGHT
  }

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }

}

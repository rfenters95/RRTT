package main.core.led.listeners;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/*
* LED listeners in general are custom ChangeListeners for the LightTabController.
* They check both the value and format for LED values in Roomba LED
* command parameters.
* */
public abstract class AbstractLEDListener implements ChangeListener<String> {

  private static final String errorText = "Invalid Input! Range [0, 255]";
  private String promptText;
  private JFXTextField textField;

  /*
  * Constructs AbstractLEDListener
  *
  * @param promptText promptText for TextField.
  * @param textField reference of TextField of which listener is attached.
  * */
  AbstractLEDListener(String promptText, JFXTextField textField) {
    this.promptText = promptText;
    this.textField = textField;
  }

  /*
  * @param value Extracted TextField input.
  * @return True if value is an acceptable LED parameter.
  * */
  private boolean hasValidLEDValue(String value) {
    int LEDValue = Integer.parseInt(value);
    return (LEDValue >= 0 && LEDValue <= 255);
  }

  /*
  * Consolidates formatting to simplify logic, and sets
  * empty String to zero (valid input).
  *
  * @param value Extracted TextField input.
  * @return True if value should be passed to value validation
  * */
  private boolean format(String value) {
    if (value.isEmpty()) {
      textField.setText("0");
      return false;
    }
    if (hasLeadingZeros(value)) {
      textField.setText(removeLeadingZeros(value));
      return false;
    }
    return true;
  }

  /*
  * @param value Extracted TextField input.
  * @return value without leading zeros
  * */
  private String removeLeadingZeros(String value) {
    return value.replaceFirst("^0+", "");
  }

  /*
  * @param value Extracted TextField input.
  * @return True if value has leading zeros
  * */
  private boolean hasLeadingZeros(String value) {
    return value.startsWith("0") && value.length() > 1;
  }

  /*
  * Check input length
  *
  * @param value Extracted TextField input.
  * @return True if value's length is 3 or less.
  * */
  private boolean hasInValidLength(String value) {
    return !value.matches("\\d{0,3}?");
  }

  @Override
  public void changed(ObservableValue<? extends String> observable, String oldValue,
      String newValue) {

    if (!hasInValidLength(newValue)) {

      if (format(newValue)) {

        if (hasValidLEDValue(newValue)) {
          textField.setPromptText(promptText);
          textField.getStylesheets().clear();
          textField.getStylesheets().add("main/ui/tabs/main.css");
        } else {
          textField.setPromptText(errorText);
          textField.getStylesheets().clear();
          textField.getStylesheets().add("main/ui/tabs/error.css");
        }

      }

    } else {
      textField.setText(oldValue);
    }

  }

}

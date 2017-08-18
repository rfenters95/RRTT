package main.core.led.listeners;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public abstract class AbstractLEDListener implements ChangeListener<String> {

    private String promptText;
    private JFXTextField textField;
    private static final String errorText = "Invalid Input! Range [0, 255]";

    public AbstractLEDListener(String promptText, JFXTextField textField) {
        this.promptText = promptText;
        this.textField = textField;
    }

    private boolean hasValidLEDValue(String value) {
        int velocity = Integer.parseInt(value);
        return (velocity >= 0 && velocity <= 255);
    }

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

    private String removeLeadingZeros(String value) {
        return value.replaceFirst("^0+", "");
    }

    private boolean hasLeadingZeros(String value) {
        return value.startsWith("0") && value.length() > 1;
    }

    private boolean hasInValidLength(String value) {
        return !value.matches("\\d{0,3}?");
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

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

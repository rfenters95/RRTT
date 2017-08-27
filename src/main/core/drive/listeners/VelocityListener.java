package main.core.drive.listeners;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import main.core.drive.modes.AbstractDriveMode;

public class VelocityListener extends AbstractDriveListener {


    public VelocityListener(AbstractDriveMode driveMode, AbstractDriveMode.Position position, JFXTextField textField) {
        super(driveMode, position, textField);
    }

    private boolean hasValidVelocity(String value) {
        int velocity = Integer.parseInt(value);
        return (velocity >= 0 && velocity <= 500);
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

        if (!hasInValidLength(newValue)) {

            if (format(newValue)) {

                if (hasValidVelocity(newValue)) {

                    if (position == AbstractDriveMode.Position.LEFT) {
                        textField.setPromptText(driveMode.getTextField1Prompt());
                    } else {
                        textField.setPromptText(driveMode.getTextField2Prompt());
                    }

                    textField.getStylesheets().clear();
                    textField.getStylesheets().add("main/ui/tabs/main.css");
                    driveMode.setEnabled(true);

                } else {
                    textField.setPromptText(AbstractDriveMode.getVelocityErrorPrompt());
                    textField.getStylesheets().clear();
                    textField.getStylesheets().add("main/ui/tabs/error.css");
                    driveMode.setEnabled(false);
                }

            }

        } else {
            textField.setText(oldValue);
        }

    }

}

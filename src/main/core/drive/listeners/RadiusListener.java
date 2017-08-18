package main.core.drive.listeners;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import main.core.drive.modes.AbstractDriveMode;

public class RadiusListener extends AbstractDriveListener {


    public RadiusListener(AbstractDriveMode driveMode, AbstractDriveMode.Position position, JFXTextField textField) {
        super(driveMode, position, textField);
    }

    private boolean hasValidRadius(String value) {
        int radius = Integer.parseInt(value);
        return ((radius >= 0 && radius <= 2000) || (radius == 32767 || radius == 32768));
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

        if (!hasInValidLength(newValue)) {

            if (format(newValue)) {

                if (hasValidRadius(newValue)) {
                    textField.setPromptText(driveMode.getTextField2Prompt());
                    textField.getStylesheets().clear();
                    textField.getStylesheets().add("main/ui/tabs/main.css");
                } else {
                    textField.setPromptText(AbstractDriveMode.getRadiusErrorPrompt());
                    textField.getStylesheets().clear();
                    textField.getStylesheets().add("main/ui/tabs/error.css");
                }

            }

        } else {
            textField.setText(oldValue);
        }

    }

}

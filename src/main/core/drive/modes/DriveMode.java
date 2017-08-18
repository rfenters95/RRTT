package main.core.drive.modes;

import main.core.drive.listeners.RadiusListener;
import main.core.drive.listeners.VelocityListener;

public class DriveMode extends AbstractDriveMode {

    public DriveMode() {
        setTextField1Prompt("Velocity (mm/s)");
        setTextField2Prompt("Radius (mm)");
    }

    @Override
    public void initialize() {

        AbstractDriveMode.textField1listener = new VelocityListener(this, Position.LEFT, textField1);
        AbstractDriveMode.textField1.textProperty().addListener(AbstractDriveMode.textField1listener);
        AbstractDriveMode.textField2listener = new RadiusListener(this, Position.RIGHT, textField2);
        AbstractDriveMode.textField2.textProperty().addListener(AbstractDriveMode.textField2listener);

        textField1.setPromptText(getTextField1Prompt());
        textField1.setText("0");
        textField2.setPromptText(getTextField2Prompt());
        textField2.setText("0");

    }

    @Override
    public void swapListener() {

        AbstractDriveMode.textField1.textProperty().removeListener(AbstractDriveMode.textField1listener);
        AbstractDriveMode.textField1listener = new VelocityListener(this, Position.LEFT, AbstractDriveMode.textField1);
        AbstractDriveMode.textField1.textProperty().addListener(AbstractDriveMode.textField1listener);

        AbstractDriveMode.textField2.textProperty().removeListener(AbstractDriveMode.textField2listener);
        AbstractDriveMode.textField2listener = new RadiusListener(this, Position.RIGHT, AbstractDriveMode.textField2);
        AbstractDriveMode.textField2.textProperty().addListener(AbstractDriveMode.textField2listener);

        textField1.setPromptText(getTextField1Prompt());
        textField1.setText("0");
        textField2.setPromptText(getTextField2Prompt());
        textField2.setText("0");

    }

    @Override
    public String toString() {
        return "DRIVE";
    }
}

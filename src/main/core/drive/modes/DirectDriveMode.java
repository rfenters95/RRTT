package main.core.drive.modes;

import main.core.drive.listeners.VelocityListener;

public class DirectDriveMode extends AbstractDriveMode {

    public DirectDriveMode() {
        setTextField1Prompt("Velocity (mm/s) - Left");
        setTextField2Prompt("Velocity (mm/s) - Right");
    }

    @Override
    public void initialize() {

        AbstractDriveMode.textField1listener = new VelocityListener(this, Position.LEFT, textField1);
        AbstractDriveMode.textField1.textProperty().addListener(AbstractDriveMode.textField1listener);
        AbstractDriveMode.textField2listener = new VelocityListener(this, Position.RIGHT, textField2);
        AbstractDriveMode.textField2.textProperty().addListener(AbstractDriveMode.textField2listener);

        setDefaultText();
        setDefaultPromptText();

    }

    @Override
    public void swapListener() {

        AbstractDriveMode.textField1.textProperty().removeListener(AbstractDriveMode.textField1listener);
        AbstractDriveMode.textField1listener = new VelocityListener(this, Position.LEFT, AbstractDriveMode.textField1);
        AbstractDriveMode.textField1.textProperty().addListener(AbstractDriveMode.textField1listener);

        AbstractDriveMode.textField2.textProperty().removeListener(AbstractDriveMode.textField2listener);
        AbstractDriveMode.textField2listener = new VelocityListener(this, Position.RIGHT, AbstractDriveMode.textField2);
        AbstractDriveMode.textField2.textProperty().addListener(AbstractDriveMode.textField2listener);

        setDefaultText();
        setDefaultPromptText();

    }

    @Override
    public String toString() {
        return "DIRECT_DRIVE";
    }
}

package main.core.drive.modes;

import com.jfoenix.controls.JFXTextField;
import main.core.drive.listeners.AbstractDriveListener;

public abstract class AbstractDriveMode {

    private String textField1Prompt;
    private String textField2Prompt;

    static JFXTextField textField1;
    static JFXTextField textField2;
    static AbstractDriveListener textField1listener;
    static AbstractDriveListener textField2listener;

    private static final String velocityErrorPrompt = "Invalid Input! Range [0, 500]";
    private static final String radiusErrorPrompt = "Invalid Input! Range [0, 2000]";

    /* *********************************************
    *
    * Static
    *
    ********************************************** */

    public static void setTextFields(JFXTextField textField1, JFXTextField textField2) {
        AbstractDriveMode.textField1 = textField1;
        AbstractDriveMode.textField2 = textField2;
    }

    /* *********************************************
    *
    * Abstract
    *
    ********************************************** */

    public abstract void initialize();
    public abstract void swapListener();

    /* *********************************************
    *
    * Getters & Setters
    *
    ********************************************** */

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

    public static String getVelocityErrorPrompt() {
        return velocityErrorPrompt;
    }

    public static String getRadiusErrorPrompt() {
        return radiusErrorPrompt;
    }

    /* *********************************************
    *
    * Enum
    *
    ********************************************** */

    public enum Position {
        LEFT, RIGHT;
    }

}

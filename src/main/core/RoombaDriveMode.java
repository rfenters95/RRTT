package main.core;

import com.jfoenix.controls.JFXTextField;

public enum RoombaDriveMode {
    DRIVE, DRIVE_DIRECT;

    public static final String VELOCITY_PROMPT1 = "Velocity (mm/s)";
    public static final String VELOCITY_PROMPT2 = "Velocity (mm/s) - Left";
    public static final String VELOCITY_PROMPT3 = "Velocity (mm/s) - Right";
    public static final String VELOCITY_ERROR = "Invalid Input! Range [0, 500]";
    public static final String RADIUS_PROMPT = "Radius (mm)";
    public static final String RADIUS_ERROR = "Invalid Input! Range [0, 2000]";

    public void resetStyle(TextFieldPosition position, JFXTextField textField) {
        textField.getStylesheets().clear();
        textField.getStylesheets().add("main/ui/tabs/main.css");
        switch (this) {
            case DRIVE:
                switch (position) {
                    case ONE:
                        textField.setPromptText(VELOCITY_PROMPT1);
                        break;
                    case TWO:
                        textField.setPromptText(RADIUS_PROMPT);
                        break;
                    default:
                        break;
                }
                break;
            case DRIVE_DIRECT:
                switch (position) {
                    case ONE:
                        textField.setPromptText(VELOCITY_PROMPT2);
                        break;
                    case TWO:
                        textField.setPromptText(VELOCITY_PROMPT3);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    public void setErrorStyle(TextFieldPosition position, JFXTextField textField) {
        textField.getStylesheets().clear();
        textField.getStylesheets().add("main/ui/tabs/error.css");
        switch (this) {
            case DRIVE:
                switch (position) {
                    case ONE:
                        textField.setPromptText(VELOCITY_ERROR);
                        break;
                    case TWO:
                        textField.setPromptText(RADIUS_ERROR);
                        break;
                    default:
                        break;
                }
                break;
            case DRIVE_DIRECT:
                textField.setPromptText(VELOCITY_ERROR);
                break;
            default:
                break;
        }
    }

    public void configTextFields(JFXTextField textField1, JFXTextField textField2) {
        initFieldsPromptText(textField1, textField2);
        initFieldsText(textField1, textField2);
    }

    public boolean isInput1Valid(JFXTextField textField) {
        return isVelocityValid(textField);
    }

    private boolean isVelocityValid(JFXTextField textField) {
        format(textField);
        int velocity = Integer.parseInt(textField.getText());
        return (velocity >= 0 && velocity <= 500);
    }

    public boolean isInput2Valid(JFXTextField textField) {
        switch (this) {
            case DRIVE:
                return isRadiusValid(textField);
            case DRIVE_DIRECT:
                return isVelocityValid(textField);
            default:
                return false;
        }
    }

    private boolean isRadiusValid(JFXTextField textField) {
        format(textField);
        int radius = Integer.parseInt(textField.getText());
        return ((radius >= 0 && radius <= 2000) || (radius == 32767 || radius == 32768));
    }

    public boolean isInputsValid(JFXTextField textField1, JFXTextField textField2) {
        boolean b1, b2;
        switch (this) {
            case DRIVE:
                b1 = isVelocityValid(textField1);
                b2 = isRadiusValid(textField2);
                return b1 && b2;
            case DRIVE_DIRECT:
                b1 = isVelocityValid(textField1);
                b2 = isVelocityValid(textField2);
                return b1 && b2;
            default:
                break;
        }
        return false;
    }

    private void format(JFXTextField textField) {

        if (textField.getText().isEmpty()) {
            textField.setText("0");
        }
        textField.setText(textField.getText().replaceFirst("^0+(?!$)", ""));

    }

    private void initFieldsPromptText(JFXTextField textField1, JFXTextField textField2) {
        switch (this) {
            case DRIVE:
                textField1.setPromptText(VELOCITY_PROMPT1);
                textField2.setPromptText(RADIUS_PROMPT);
                break;
            case DRIVE_DIRECT:
                textField1.setPromptText(VELOCITY_PROMPT2);
                textField2.setPromptText(VELOCITY_PROMPT3);
                break;
            default:
                break;
        }
    }

    private void initFieldsText(JFXTextField textField1, JFXTextField textField2) {
        textField1.setText("0");
        textField2.setText("0");
    }

    public enum TextFieldPosition {
        ONE, TWO;
    }

}

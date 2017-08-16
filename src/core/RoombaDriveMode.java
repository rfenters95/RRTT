package core;

import com.jfoenix.controls.JFXTextField;

public enum RoombaDriveMode {
    DRIVE, DRIVE_DIRECT;

    public static void resetStyle(JFXTextField textField) {
        textField.getStylesheets().clear();
        textField.getStylesheets().add("ui/main/tabs/main.css");
    }

    public static void resetStyle(JFXTextField textField1, JFXTextField textField2) {
        resetStyle(textField1);
        resetStyle(textField2);
    }

    public static void setErrorStyle(JFXTextField textField) {
        textField.getStylesheets().clear();
        textField.getStylesheets().add("ui/main/tabs/error.css");
    }

    public static void setErrorStyle(JFXTextField textField1, JFXTextField textField2) {
        resetStyle(textField1);
        resetStyle(textField2);
    }

    public void configTextFields(JFXTextField textField1, JFXTextField textField2) {
        initFieldsPromptText(textField1, textField2);
        initFieldsText(textField1, textField2);
    }

    public boolean isInput1Valid(JFXTextField textField) {
        return isVelocityValid(textField);
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

    private boolean isVelocityValid(JFXTextField textField) {
        format(textField);
        int velocity = Integer.parseInt(textField.getText());
        return (velocity >= 0 && velocity <= 500);
    }

    private boolean isRadiusValid(JFXTextField textField) {
        format(textField);
        int radius = Integer.parseInt(textField.getText());
        return ((radius >= 0 && radius <= 2000) || (radius == 32767 || radius == 32768));
    }

    public boolean isValidInputs(JFXTextField textField1, JFXTextField textField2) {
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
                textField1.setPromptText("Velocity (mm/s)");
                textField2.setPromptText("Radius (mm)");
                break;
            case DRIVE_DIRECT:
                textField1.setPromptText("Velocity (mm/s) - Left");
                textField2.setPromptText("Velocity (mm/s) - Right");
                break;
            default:
                break;
        }
    }

    private void initFieldsText(JFXTextField textField1, JFXTextField textField2) {
        textField1.setText("0");
        textField2.setText("0");
    }

}

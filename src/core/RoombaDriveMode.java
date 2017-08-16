package core;

import com.jfoenix.controls.JFXTextField;

public enum RoombaDriveMode {
    DRIVE, DRIVE_DIRECT;

    public void configTextFields(JFXTextField textField1, JFXTextField textField2) {

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

        textField1.setText("");
        textField2.setText("");

    }

    public boolean hasValidParameters(JFXTextField textField1, JFXTextField textField2) {

        boolean hasEmptyFields = textField1.getText().isEmpty() || textField2.getText().isEmpty();
        if (hasEmptyFields) {
            return false;
        }

        textField1.setText(textField1.getText().replaceFirst("^0+(?!$)", ""));
        textField2.setText(textField2.getText().replaceFirst("^0+(?!$)", ""));

        switch (this) {

            case DRIVE:

                int velocity = Integer.parseInt(textField1.getText());
                int radius = Integer.parseInt(textField2.getText());
                boolean isValidVelocity = velocity >= 0 && velocity <= 500;
                boolean isValidRadius = radius >= 0 && radius <= 2000;
                return isValidVelocity && isValidRadius;

            case DRIVE_DIRECT:

                int velocityLeft = Integer.parseInt(textField1.getText());
                int velocityRight = Integer.parseInt(textField2.getText());
                boolean isValidVelocityLeft = velocityLeft >= 0 && velocityLeft <= 500;
                boolean isValidVelocityRight = velocityRight >= 0 && velocityRight <= 500;
                return isValidVelocityLeft && isValidVelocityRight;

            default:

                break;

        }

        return false;
    }

}

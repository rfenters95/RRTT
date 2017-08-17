package main.core;

import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoombaDriveModeTest {
    @BeforeEach
    void setUp() {
        new JFXPanel();
    }

    @Test
    void isInput1Valid() {

        RoombaDriveMode driveMode = RoombaDriveMode.DRIVE;
        assertEquals(driveMode.isInput1Valid(new JFXTextField("-1")), false);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("-123456")), false);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("0")), true);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("200")), true);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("500")), true);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("2000")), false);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("123456")), false);

        driveMode = RoombaDriveMode.DRIVE_DIRECT;
        assertEquals(driveMode.isInput1Valid(new JFXTextField("-1")), false);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("-123456")), false);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("0")), true);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("200")), true);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("500")), true);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("2000")), false);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("123456")), false);
    }

    @Test
    void isInput2Valid() {

        RoombaDriveMode driveMode = RoombaDriveMode.DRIVE;
        assertEquals(driveMode.isInput2Valid(new JFXTextField("-1")), false);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("-123456")), false);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("0")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("200")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("500")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("2000")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("2001")), false);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("32767")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("32768")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("123456")), false);

        driveMode = RoombaDriveMode.DRIVE_DIRECT;
        assertEquals(driveMode.isInput2Valid(new JFXTextField("-1")), false);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("-123456")), false);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("0")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("200")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("500")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("2000")), false);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("123456")), false);
    }

}
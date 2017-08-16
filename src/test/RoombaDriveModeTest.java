package test;

import com.jfoenix.controls.JFXTextField;
import core.RoombaDriveMode;
import javafx.embed.swing.JFXPanel;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoombaDriveModeTest {

    @BeforeClass
    public static void init() {
        new JFXPanel();
    }

    @Test
    public void DriveVelocityTest() {
        RoombaDriveMode driveMode = RoombaDriveMode.DRIVE;
        assertEquals(driveMode.isInput1Valid(new JFXTextField("-1")), false);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("-123456")), false);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("0")), true);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("200")), true);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("500")), true);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("2000")), false);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("123456")), false);
    }

    @Test
    public void DriveRadiusTest() {
        RoombaDriveMode driveMode = RoombaDriveMode.DRIVE;
        assertEquals(driveMode.isInput2Valid(new JFXTextField("-1")), false);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("-123456")), false);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("0")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("200")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("500")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("2000")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("2001")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("32767")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("32768")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("123456")), false);
    }

    @Test
    public void DriveDirectVelocityLeftTest() {
        RoombaDriveMode driveMode = RoombaDriveMode.DRIVE_DIRECT;
        assertEquals(driveMode.isInput1Valid(new JFXTextField("-1")), false);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("-123456")), false);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("0")), true);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("200")), true);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("500")), true);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("2000")), false);
        assertEquals(driveMode.isInput1Valid(new JFXTextField("123456")), false);
    }

    @Test
    public void DriveDirectVelocityRightTest() {
        RoombaDriveMode driveMode = RoombaDriveMode.DRIVE_DIRECT;
        assertEquals(driveMode.isInput2Valid(new JFXTextField("-1")), false);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("-123456")), false);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("0")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("200")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("500")), true);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("2000")), false);
        assertEquals(driveMode.isInput2Valid(new JFXTextField("123456")), false);
    }

}

package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by John Paul Ashour on 11/13/2016.
 */

public class GlyphSpinner {
    private Servo spinnerServo;
    private DigitalChannel spinnerTouch1;
    private DigitalChannel spinnerTouch2;

    private static final double FLIPPED_SERVO_POSITION = 0;
    private static final double UNFLIPPED_SERVO_POSITION = 0.93;

    private enum GlyphSpinnerEnum {
        UNFLIPPED,
        FLIPPED,
        UNFLIPPING,
        FLIPPING,
    }

    private GlyphSpinnerEnum glyphSpinnerState;

    public GlyphSpinner(String spinner, String touchSensor1,String touchSensor2, HardwareMap hardwareMap) {
        this.spinnerServo = hardwareMap.servo.get(spinner);
        spinnerTouch1 = hardwareMap.get(DigitalChannel.class, touchSensor1);
        spinnerTouch1.setMode(DigitalChannel.Mode.INPUT);

        spinnerTouch2 = hardwareMap.get(DigitalChannel.class, touchSensor2);
        spinnerTouch2.setMode(DigitalChannel.Mode.INPUT);

        this.initializePosition();
    }

    private void initializePosition(){
        this.spinnerServo.setPosition(UNFLIPPED_SERVO_POSITION);
        glyphSpinnerState = GlyphSpinnerEnum.UNFLIPPED;
    }

    public boolean isClearOfSwitches()
    {
        // Because getState() returns True if unpressed, if both buttons are True, both buttons are unpressed
        return spinnerTouch1.getState() && spinnerTouch2.getState();
    }

    public boolean isFlipping() {
        return glyphSpinnerState == GlyphSpinnerEnum.FLIPPING || glyphSpinnerState == GlyphSpinnerEnum.UNFLIPPING;
    }

    public boolean isFlipped() {
        return glyphSpinnerState == GlyphSpinnerEnum.FLIPPED;
    }

    public void flip() {
        if (this.isClearOfSwitches()) {
            if (glyphSpinnerState == GlyphSpinnerEnum.FLIPPED || glyphSpinnerState == GlyphSpinnerEnum.UNFLIPPING) {
                this.spinnerServo.setPosition(UNFLIPPED_SERVO_POSITION);
                glyphSpinnerState = GlyphSpinnerEnum.UNFLIPPED;
            } else {
                this.spinnerServo.setPosition(FLIPPED_SERVO_POSITION);
                glyphSpinnerState = GlyphSpinnerEnum.FLIPPED;
            }
        } else {
            if (glyphSpinnerState == GlyphSpinnerEnum.UNFLIPPED) {
                glyphSpinnerState = GlyphSpinnerEnum.FLIPPING;
            }
            if (glyphSpinnerState == GlyphSpinnerEnum.FLIPPED) {
                glyphSpinnerState = GlyphSpinnerEnum.UNFLIPPING;
            }
        }
    }

    @Override
    public String toString() {
        String telemetry = "";

        if (!isClearOfSwitches()) {
            telemetry += "BLOCKED ";
        }

        switch (glyphSpinnerState) {
            case UNFLIPPED:
                telemetry += "Unflipped";
                break;

            case FLIPPED:
                telemetry += "Flipped";
                break;

            case UNFLIPPING:
                telemetry += "Unflipping";
                break;

            case FLIPPING:
                telemetry += "Flipping";
                break;

            default:
                telemetry += "Unknown";
                break;
        }

        return telemetry;
    }
}

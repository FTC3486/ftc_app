package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * Created by John Paul Ashour on 11/13/2016.
 */

public class GlyphLift {
    private DcMotor lift;
    private DigitalChannel liftTouch;

    private enum GlyphLiftEnum {
        LIFTING,
        RETRACTING,
        STOPPED
    }
    private GlyphLiftEnum glyphLiftState;

    public GlyphLift(String lift, String touchSensor, HardwareMap hardwareMap) {
        this.lift = hardwareMap.dcMotor.get(lift);
        liftTouch = hardwareMap.get(DigitalChannel.class, touchSensor);
        liftTouch.setMode(DigitalChannel.Mode.INPUT);

        stop();
    }

    public boolean isFullyRetracted() {
        return !liftTouch.getState();
    }

    /**
     * Runs Glyph Lift up
     */
    public void lift() {
        lift.setPower(1.0);
        glyphLiftState = GlyphLiftEnum.LIFTING;
    }

    /**
     * Runs Glyph Lift down
     */
    public void retract() {
        if (isFullyRetracted()) {
            stop();
            reset();
        } else {
            lift.setPower(-1.0);
            glyphLiftState = GlyphLiftEnum.RETRACTING;
        }
    }

    /**
     * Stops Glyph Lift motion and holds current position
     */
    public void stop() {
        lift.setPower(0);
        glyphLiftState = GlyphLiftEnum.STOPPED;
    }

    private void reset() {
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public String toString() {
        switch (glyphLiftState) {
            case LIFTING:
                return "Lifting";

            case RETRACTING:
                return "Retracting";

            case STOPPED:
                String telemetry;
                if (isFullyRetracted()) {
                    telemetry = "Stopped: Fully Retracted";
                } else {
                    telemetry = "Stopped: Not Fully Retracted";
                }
                return telemetry;

            default:
                return "Unknown";
        }
    }
}

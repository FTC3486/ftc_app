package com.qualcomm.ftcrobotcontroller.opmodes.Subsystems;

import com.jacobamason.FTCRC_Extensions.ExtendedServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Matthew on 1/16/2016.
 */
public class TapeMeasure {
    ExtendedServo tapeMotor;
    ExtendedServo tapeTilt;

    public TapeMeasure(String tapeMotor, String tapeTilt, HardwareMap hardwareMap) {
        this.tapeMotor = new ExtendedServo(hardwareMap.servo.get(tapeMotor));
        this.tapeTilt = new ExtendedServo(hardwareMap.servo.get(tapeTilt));
        this.tapeMotor.setPosition(0.1);
        this.tapeTilt.setPosition(0.3);
    }


    public void extendTapeMeasure() {
        tapeMotor.setPosition(tapeMotor.getPosition() + 0.005);
    }

    public void retractTapeMeasure() {
        tapeMotor.setPosition(tapeMotor.getPosition() - 0.005);
    }

    public void stopTapeMeasure() {

    }

    public void tiltDown() {
        tapeTilt.setPosition(tapeTilt.getPosition() - 0.005);
    }

    public void tiltUp() {
        tapeTilt.setPosition(tapeTilt.getPosition() + 0.005);
    }

    public void stopTilt() {
        //TODO: signal that servo is stopped
    }
    //TODO: Add telemetry
}

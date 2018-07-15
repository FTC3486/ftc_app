package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Owner_2 on 11/26/2016.
 */

public class CapballHolder {
    public Servo capballHolderServo = null;
    public boolean isBallCaptured = false;

    private enum capballHolderServoEnum {Captured, Released, Colapsed}

    capballHolderServoEnum capballHolderServoState = capballHolderServoEnum.Colapsed;


    public CapballHolder(String capballHolderServo, HardwareMap hardwareMap) {
        this.capballHolderServo = hardwareMap.servo.get(capballHolderServo);
        this.colapsed();
    }

    public void released() {
        capballHolderServo.setPosition(0);
        isBallCaptured = false;
    }

    public void captured() {
        capballHolderServo.setPosition(0.3);
        isBallCaptured = true;
    }

    public void colapsed() {
        capballHolderServo.setPosition(0.95);
    }

    @Override
    public String toString() {
        switch (capballHolderServoState) {
            case Released:
                return "Ball Released";
            case Captured:
                return "Ball Captured";
            case Colapsed:
                return "Arm Colapsed";
            default:
                return "unknown";
        }
    }
}

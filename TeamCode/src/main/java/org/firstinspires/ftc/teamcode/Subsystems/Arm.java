package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    Servo armServo;
    public boolean isUp = false;

    private enum armServoStateEnum {Up, Down};
    armServoStateEnum armServoState;

    public Arm(String armServo, HardwareMap hardwareMap) {
        this.armServo = hardwareMap.servo.get(armServo);
        this.goDown();
    }

    public void goUp() {
        armServo.setPosition(0.9);
        armServoState = armServoStateEnum.Up;
        isUp = true;
    }

    public void goDown() {
        armServo.setPosition(0.3);
        armServoState = armServoStateEnum.Down;
        isUp = false;
    }

    @Override
    public String toString() {
        switch (armServoState) {
            case Up:
                return "Arm Up";

            case Down:
                return "Arm Down";

            default:
                return "unknown";
        }
    }
}
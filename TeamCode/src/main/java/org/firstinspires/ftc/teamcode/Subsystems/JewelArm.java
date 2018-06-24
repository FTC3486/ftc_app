package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class JewelArm {
    public Servo armServo;
    public ColorSensor jewelColor;
    public boolean isUp = false;



    double servoPosition;

    private enum armServoStateEnum {Up, Down};
    armServoStateEnum armServoState;

    public JewelArm(String armServo, String jewelColor, HardwareMap hardwareMap) {
        this.armServo = hardwareMap.servo.get(armServo);
        this.jewelColor = hardwareMap.colorSensor.get(jewelColor);
        this.up();
    }

    public void autoInit(){
        armServo.setPosition(0.24);
    }


    public void up() {
        armServo.setPosition(0.43);
        armServoState = armServoStateEnum.Up;
        isUp = true;
    }

    public void adjustUp(){
        servoPosition = servoPosition + 0.01;
        armServo.setPosition(servoPosition);
    }

    public void down() {
        armServo.setPosition(0.84);
        armServoState = armServoStateEnum.Down;
        isUp = false;
    }

    public void adjustDown(){
        servoPosition = servoPosition - 0.01;
        armServo.setPosition(servoPosition);
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
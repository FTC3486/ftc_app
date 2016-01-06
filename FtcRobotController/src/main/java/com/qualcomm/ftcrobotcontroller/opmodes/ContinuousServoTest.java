package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Matthew on 1/5/2016.
 */
public class ContinuousServoTest extends OpMode {
    Servo continuousServo;

    @Override
    public void init() {
        continuousServo = hardwareMap.servo.get("cs");
    }

    @Override
    public void loop() {
        continuousServo.setPosition(0.9);
    }

}

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.jacobamason.FTCRC_Extensions.Drive;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Matthew on 8/11/2015.
 */
public class TankDriveOpMode extends OpMode{
    Drive driver;
    DcMotor left, right;
    Servo servoArm;
    double armPosition = 0;
    TouchSensor touchSensor;
    OpticalDistanceSensor opDistSensor;
    int lightOn = 0;


    @Override
    public void init() {
        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");
        left.setDirection(DcMotor.Direction.REVERSE);
        driver = new Drive(this, 0.15f);

        servoArm = hardwareMap.servo.get("servoArm");
        servoArm.setPosition(armPosition);

        touchSensor = hardwareMap.touchSensor.get("touchSensor");
        opDistSensor = hardwareMap.opticalDistanceSensor.get("opDistSensor");

    }

    @Override
    public void loop() {
        driver.tank_drive(left, right);

        if(gamepad1.x) {
            armPosition = Range.clip(servoArm.getPosition() + 0.01, 0D, 1D);
            servoArm.setPosition(armPosition);
        }

        if(gamepad1.b) {
            armPosition = Range.clip(servoArm.getPosition() - 0.01, 0D, 1D);
            servoArm.setPosition(armPosition);
        }


        if(gamepad1.a) {
            if(lightOn == 0) {
                opDistSensor.enableLed(true);
                lightOn = 1;
            } else {
                opDistSensor.enableLed(false);
                lightOn = 0;
            }
        }

        telemetry.addData("isPressed", String.valueOf(touchSensor.isPressed()));
        telemetry.addData("Distance", opDistSensor.getLightDetectedRaw());
    }
}

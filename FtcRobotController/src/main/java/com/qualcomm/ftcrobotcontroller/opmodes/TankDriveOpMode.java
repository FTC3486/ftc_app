package com.qualcomm.ftcrobotcontroller.opmodes;

import com.jacobamason.FTCRC_Extensions.Drive;
import com.qualcomm.robotcore.hardware.ColorSensor;
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
    //Drive driver;
    //DcMotor leftfront,leftback,rightfront,rightback;
    Servo servoArm;
    double armPosition = 0.5;
    //TouchSensor touchSensor;
    //OpticalDistanceSensor opDistSensor;
    //ColorSensor colorSensor;

    @Override
    public void init() {
        /*leftfront = hardwareMap.dcMotor.get("leftfront");
        leftback = hardwareMap.dcMotor.get("leftback");
        rightfront = hardwareMap.dcMotor.get("rightfront");
        rightback = hardwareMap.dcMotor.get("rightback");
        driver = new Drive(this, 0.15f);*/

        servoArm = hardwareMap.servo.get("servoArm");
        servoArm.setPosition(armPosition);

        //touchSensor = hardwareMap.touchSensor.get("touchSensor");
        //opDistSensor = hardwareMap.opticalDistanceSensor.get("opDistSensor");
    }

    @Override
    public void loop() {
        //driver.tank_drive(leftfront, leftback, rightfront, rightback);

        if(gamepad1.x) {
            servoArm.setPosition(0.1);
        }

        if(gamepad1.b) {
            servoArm.setPosition(0.9);
        }

       // telemetry.addData("isPressed", String.valueOf(touchSensor.isPressed()));
        //telemetry.addData("Distance", opDistSensor.getLightDetectedRaw());
        //telemetry.addData("Blue:", colorSensor.blue());
       // telemetry.addData("Red:", colorSensor.red());
      //  telemetry.addData("Green:", colorSensor.green());
    }
}

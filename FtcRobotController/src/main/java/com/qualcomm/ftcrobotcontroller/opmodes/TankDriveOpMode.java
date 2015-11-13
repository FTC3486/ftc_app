package com.qualcomm.ftcrobotcontroller.opmodes;

import com.jacobamason.FTCRC_Extensions.Drive;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by Matthew on 8/11/2015.
 */
public class TankDriveOpMode extends OpMode{
    //DriveTrain
    Drive driver;
    DcMotor leftfront,leftback,rightfront,rightback;

    //Claw
    Servo arm;
    double armPosition = 0.5;
    DcMotor claw;

    //Scoop
    Servo lr;//left-right, up-down
    double centerPosition;
    double leftPosition;
    double rightPosition;

    Servo ud;
    double upPosition;
    double downPosition;

    //Zoop-Zoop
    Servo zleft, zright;
    double zUp;
    double zleftdown;
    double zrightdown;


    //TouchSensor touchSensor;
    //OpticalDistanceSensor opDistSensor;
    //ColorSensor colorSensor;
    //GyroSensor gyroSensor;

    @Override
    public void init() {
        //Drivetrain
        leftfront = hardwareMap.dcMotor.get("leftfront");
        leftback = hardwareMap.dcMotor.get("leftback");
        rightfront = hardwareMap.dcMotor.get("rightfront");
        rightback = hardwareMap.dcMotor.get("rightback");
        driver = new Drive(this, 0.15f);

        //Claw
        arm = hardwareMap.servo.get("arm");
        arm.setPosition(armPosition);
        claw = hardwareMap.dcMotor.get("claw");

        //Scoop
        lr = hardwareMap.servo.get("lr");
        lr.setPosition(centerPosition);

        ud = hardwareMap.servo.get("ud");
        ud.setPosition(downPosition);

        //Zoop-Zoop
        zleft = hardwareMap.servo.get("zleft");
        zleft.setPosition(zUp);

        zright = hardwareMap.servo.get("zright");
        zright.setPosition(zUp);

        //touchSensor = hardwareMap.touchSensor.get("touchSensor");
        //opDistSensor = hardwareMap.opticalDistanceSensor.get("opDistSensor");
        //gyroSensor = hardwareMap.gyroSensor.get("gyroSensor");
        //gyroSensor.calibrate();

    }

    @Override
    public void loop() {
        // Gamepad 1
        driver.tank_drive(leftfront, leftback, rightfront, rightback);


        //Gamepad2
        if(gamepad2.dpad_up) {
            arm.setPosition(arm.getPosition() + 0.1);
        }

        if(gamepad2.dpad_down) {
            arm.setPosition(arm.getPosition() - 0.1);
        }

        if(gamepad2.left_bumper) {
           // Raise Claw
            claw.setPower(0.5);
        }

        if(gamepad2.left_trigger > 0) {
            //Lower Claw
            claw.setPower(-0.5);
        }

        if(gamepad2.x) {
            if(ud.getPosition() == upPosition){
                lr.setPosition(leftPosition);
            }
            //Scoop lifted?
                //no: nothing happens, press Y to bring up scoop
                //yes: keep going
            //Turn Scoop Left
        }

        if(gamepad2.b) {
            if(ud.getPosition() == upPosition){
                lr.setPosition(rightPosition);
            }
            //Scoop lifted?
                //no: nothing happens, press Y to bring up scoop
                //yes: keep going
            //Turn Scoop Right
        }

        if(gamepad2.y) {
            if(ud.getPosition() == downPosition) {
                ud.setPosition(upPosition);
            }
            //Scoop lifted?
                //no: raise scoop
                //yes: keep going
            //Center Scoop
        }

        if(gamepad2.a) {
            lr.setPosition(centerPosition);
            ud.setPosition(downPosition);
            //Center Scoop
            //Lower scoop
        }

        if(gamepad2.right_bumper) {
            if(zright.getPosition() == zUp && zleft.getPosition() == zUp) {
                zleft.setPosition(zleftdown);
                zright.setPosition(zrightdown);
            } else if(zright.getPosition() == zrightdown && zleft.getPosition() == zleftdown) {
                zleft.setPosition(zUp);
                zright.setPosition(zUp);
            }
            //Is Zoop-zoop in?
                //yes: put zoop-zoop out
                //no: bring zoop-zoop in
        }




       // telemetry.addData("isPressed", String.valueOf(touchSensor.isPressed()));
        //telemetry.addData("Distance", opDistSensor.getLightDetectedRaw());
        //telemetry.addData("Blue:", colorSensor.blue());
       // telemetry.addData("Red:", colorSensor.red());
      //  telemetry.addData("Green:", colorSensor.green());
        //telemetry.addData("Gyro X:", gyroSensor.rawX());
       // telemetry.addData("Gyro Y:", gyroSensor.rawY());
        //telemetry.addData("Gyro Z:", gyroSensor.rawZ());
        //telemetry.addData("Gyro Heading:", gyroSensor.getHeading());
        //telemetry.addData("Gyro Rotation:", gyroSensor.getRotation());

    }
}

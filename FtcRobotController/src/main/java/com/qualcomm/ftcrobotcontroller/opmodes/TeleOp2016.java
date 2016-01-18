package com.qualcomm.ftcrobotcontroller.opmodes;
import com.jacobamason.FTCRC_Extensions.Drive;
import com.jacobamason.FTCRC_Extensions.GamepadWrapper;
import com.qualcomm.ftcrobotcontroller.opmodes.Subsystems.ParkingBrake;
import com.qualcomm.ftcrobotcontroller.opmodes.Subsystems.Pickup;
import com.qualcomm.ftcrobotcontroller.opmodes.Subsystems.Plow;
import com.qualcomm.ftcrobotcontroller.opmodes.Subsystems.TapeMeasure;
import com.qualcomm.ftcrobotcontroller.opmodes.Subsystems.Turret;
import com.qualcomm.ftcrobotcontroller.opmodes.Subsystems.Winch;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Matthew on 8/11/2015.
 */
public class TeleOp2016 extends OpMode{
    GamepadWrapper joy1;
    GamepadWrapper joy2;

    //DriveTrain
    Drive driver;
    DcMotor leftfront, leftback, rightfront, rightback;

    //Grappling Hook
    TapeMeasure tapeMeasure;

    Winch winch;
    ParkingBrake parkingBrake;
    Turret turret;
    Plow plow;
    Pickup pickup;

    @Override
    public void init() {
        joy1 = new GamepadWrapper();
        joy2 = new GamepadWrapper();

        //Drivetrain
        leftfront = hardwareMap.dcMotor.get("leftfront");
        leftback = hardwareMap.dcMotor.get("leftback");
        rightfront = hardwareMap.dcMotor.get("rightfront");
        rightback = hardwareMap.dcMotor.get("rightback");
        driver = new Drive(this, 0.15f);


        //Grappling Hook
        tapeMeasure = new TapeMeasure("tapeMotor", "tapeTilt", hardwareMap);
        winch = new Winch("winchMotor", hardwareMap);


        //Parking Brake
        parkingBrake = new ParkingBrake("parkingBrake", hardwareMap);

        //Turret
        turret = new Turret("swivel", "extender", "dumper", hardwareMap);

        //Plow
        plow = new Plow("leftPlow", "rightPlow", hardwareMap);

        //Pickup
        pickup = new Pickup("pickup", hardwareMap);
    }


    @Override
    public void loop() {
        joy1.update(gamepad1);
        joy2.update(gamepad2);

        // Gamepad 1
        driver.tank_drive(leftfront, leftback, rightfront, rightback);

        if(gamepad1.right_trigger > 0.7){
            winch.out();
        } else if(gamepad1.right_bumper){
            winch.in();
        } else winch.stop();

        if(joy1.toggle.left_bumper){
            plow.goDown();
        } else {
            plow.goUp();
        }

        if(joy1.toggle.a){
            parkingBrake.brake();
        } else {
            parkingBrake.release();
        }

        // Gamepad 2
        if(gamepad2.dpad_left) {
            tapeMeasure.extendTapeMeasure();
        } else if(gamepad2.dpad_right) {
            tapeMeasure.retractTapeMeasure();
        } else {
            tapeMeasure.stopTapeMeasure();
        }

        if(gamepad2.dpad_up) {
            tapeMeasure.tiltUp();
        } else if(gamepad2.dpad_down) {
            tapeMeasure.tiltDown();
        } else {
            tapeMeasure.stopTilt();
        }

        if(gamepad2.left_stick_x > 0.2) {
            turret.swivelRight();
        } else if(gamepad2.left_stick_x < -0.2) {
            turret.swivelLeft();
        } else {
            turret.swivelStop();
        }

        if(gamepad2.a) {
            turret.retract();
        } else if(gamepad2.y) {
            turret.extend();
        } else {
            turret.extenderStop();
        }

        if(gamepad2.x) {
            turret.dumpDebris();
        } else {
            turret.holdDebris();
        }

        if(gamepad2.right_bumper) {
           pickup.collect();
        } else {
            pickup.stop();
        }

        telemetry.addData("Turret ", turret);
        telemetry.addData("Parking Brake ", parkingBrake);
        telemetry.addData("Winch ", winch);
        telemetry.addData("Plow ", plow);
        telemetry.addData("Pickup ", pickup);
        telemetry.addData("tapeMotor ", tapeMeasure);
    }
}

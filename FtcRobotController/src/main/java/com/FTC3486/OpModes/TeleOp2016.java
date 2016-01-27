package com.FTC3486.OpModes;
import com.FTC3486.FTCRC_Extensions.DriveTrain;
import com.FTC3486.FTCRC_Extensions.Driver;
import com.FTC3486.FTCRC_Extensions.GamepadWrapper;
import com.FTC3486.Subsystems.ClimberDump;
import com.FTC3486.Subsystems.ParkingBrake;
import com.FTC3486.Subsystems.Pickup;
import com.FTC3486.Subsystems.Plow;
import com.FTC3486.Subsystems.TapeMeasure;
import com.FTC3486.Subsystems.Turret;
import com.FTC3486.Subsystems.Winch;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Matthew on 8/11/2015.
 */
public class TeleOp2016 extends OpMode{
    GamepadWrapper joy1;
    GamepadWrapper joy2;
    DriveTrain driveTrain;
    Driver driver;
    DcMotor leftfront, leftback, rightfront, rightback;
    TapeMeasure tapeMeasure;
    Winch winch;
    ParkingBrake parkingBrake;
    Turret turret;
    Plow plow;
    Pickup pickup;
    ClimberDump climberDump;

    @Override
    public void init() {
        joy1 = new GamepadWrapper();
        joy2 = new GamepadWrapper();

        driveTrain = new DriveTrain.Builder()
                .addLeftMotor(hardwareMap.dcMotor.get("leftfront"))
                .addLeftMotorWithEncoder(hardwareMap.dcMotor.get("leftback"))
                .addRightMotor(hardwareMap.dcMotor.get("rightfront"))
                .addRightMotorWithEncoder(hardwareMap.dcMotor.get("rightback"))
                .build();
        driver = new Driver(this, driveTrain);

        tapeMeasure = new TapeMeasure("tapeMotor", "tapeTilt", hardwareMap);
        winch = new Winch("winchMotor", hardwareMap);
        parkingBrake = new ParkingBrake("parkingBrake", hardwareMap);
        turret = new Turret("swivel", "extender", "dumper", hardwareMap);
        plow = new Plow("leftPlow", "rightPlow", hardwareMap);
        pickup = new Pickup("pickup", hardwareMap);
        climberDump = new ClimberDump("climberDump", hardwareMap);
    }


    @Override
    public void loop() {
        joy1.update(gamepad1);
        joy2.update(gamepad2);

        // Gamepad 1
        if(joy1.toggle.x) {
            driver.tank_drive(gamepad1, Driver.Direction.BACKWARD);
        } else {
            driver.tank_drive(gamepad1, Driver.Direction.FORWARD);
        }

        if(gamepad1.right_trigger > 0.7){
            winch.in();
        } else if(gamepad1.right_bumper){
            winch.out();
        } else winch.stop();

        if(joy1.toggle.left_bumper){
            plow.goUp();
        } else {
            plow.goDown();
        }

        if(joy1.toggle.a){
            parkingBrake.brake();
        } else {
            parkingBrake.release();
        }

        // Gamepad 2
        if(gamepad2.dpad_right) {
            tapeMeasure.extendTapeMeasure();
        } else if(gamepad2.dpad_left) {
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

        if(gamepad2.right_bumper) {
            turret.wholeDumpDebris();
        } else if(gamepad2.x) {
            turret.halfDumpDebris();
        } else {
            turret.holdDebris();
        }

        if(joy2.toggle.left_bumper) {
           pickup.collect();
        } else if(gamepad2.left_trigger > 0.2) {
            pickup.reverse();
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

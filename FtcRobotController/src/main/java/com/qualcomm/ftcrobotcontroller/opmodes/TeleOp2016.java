package com.qualcomm.ftcrobotcontroller.opmodes;
import com.jacobamason.FTCRC_Extensions.Drive;
import com.jacobamason.FTCRC_Extensions.ExtendedGamepad;
import com.jacobamason.FTCRC_Extensions.ExtendedServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by Matthew on 8/11/2015.
 */
public class TeleOp2016 extends OpMode{
    //DriveTrain
    Drive driver;
    DcMotor leftfront,leftback,rightfront,rightback;

    //Grappling Hook
    ExtendedServo tapeMotor;
    //ExtendedServo tapeTilt;
    //double initialTilt = 0.5;
    DcMotor winchMotor;

    //Parking Brake
    ExtendedServo parkingBrake;
    double inPosition = 0.337;
    double outPosition = 1.0;
    int gp1_a_state = 0;

    //Turret
    DcMotor swivel;
    DcMotor extender;
    //Servo debrisDumper;

    //Plow
    ExtendedServo leftPlow;
    double lPdown = 0.22;
    double lPup = 0.57;
    ExtendedServo rightPlow;
    double rPdown = .729;
    double rPup = .113;
    double gp1_left_bumper_state = 0;

    //Pickup
    //DcMotor pickup;

    @Override
    public void init() {
        //Drivetrain
        leftfront = hardwareMap.dcMotor.get("leftfront");
        leftback = hardwareMap.dcMotor.get("leftback");
        rightfront = hardwareMap.dcMotor.get("rightfront");
        rightback = hardwareMap.dcMotor.get("rightback");
        driver = new Drive(this, 0.15f);

        //Grappling Hook
        tapeMotor = new ExtendedServo(hardwareMap.servo.get("tapeM"));
        //tapeTilt = new ExtendedServo(hardwareMap.servo.get("tapeT"));
        //tapeTilt.setPosition(initialTilt);
        winchMotor = hardwareMap.dcMotor.get("wM");

        //Parking Brake
        parkingBrake = new ExtendedServo(hardwareMap.servo.get("pB"));
        parkingBrake.setPosition(0.0);

        //Turret
        swivel = hardwareMap.dcMotor.get("swivel");
        swivel.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        swivel.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        extender = hardwareMap.dcMotor.get("extender");
        //debrisDumper = hardwareMap.servo.get("dd");

        //Plow
        leftPlow = new ExtendedServo(hardwareMap.servo.get("lP"));
        leftPlow.setPosition(lPup);
        rightPlow =  new ExtendedServo(hardwareMap.servo.get("rP"));
        rightPlow.setPosition(rPup);

        //Pickup
        //pickup = hardwareMap.dcMotor.get("pickup");
    }

    public TeleOp2016()
    {
        // Get a better controller
        this.gamepad1 = new ExtendedGamepad();
        this.gamepad2 = new ExtendedGamepad();
    }

    @Override
    public void loop() {
        // Gamepad 1
        driver.tank_drive(leftfront, leftback, rightfront, rightback);

        if(gamepad1.right_trigger > 0.7){
            winchMotor.setPower(1.0);
        } else if(gamepad1.right_bumper){
            winchMotor.setPower(-1.0);
        } else winchMotor.setPower(0.0);

        if(gamepad1.left_bumper) {
            if(gp1_left_bumper_state == 0) {
                gp1_left_bumper_state = 1;
                if (leftPlow.getPosition() < 0.4) {
                    leftPlow.setPosition(lPup);
                    rightPlow.setPosition(rPup);
                } else if (leftPlow.getPosition() > 0.4) {
                    leftPlow.setPosition(lPdown);
                    rightPlow.setPosition(rPdown);
                }
            }
        } else gp1_left_bumper_state = 0;

        if(gamepad1.a) {
            if(gp1_a_state == 0) {
                gp1_a_state = 1;
                if (parkingBrake.getPosition() < 0.6) {
                    parkingBrake.setPosition(outPosition);
                } else if (parkingBrake.getPosition() > 0.6) {
                    parkingBrake.setPosition(inPosition);
                }
            }
        } else gp1_left_bumper_state = 0;

        // Gamepad 2

        /*tapeTilt.runIf(gamepad2.dpad_up, +0.005);
        tapeTilt.runIf(gamepad2.dpad_down, -0.005);
        tapeTilt.runServo();*/

        tapeMotor.runIf(gamepad2.dpad_up, +0.005);
        tapeMotor.runIf(gamepad2.dpad_down, -0.005);
        tapeMotor.runServo();

        if(gamepad2.left_stick_x > 0.2) {
            swivel.setPower(0.5);
        } else if(gamepad2.left_stick_x < -0.2) {
            swivel.setPower(-0.5);
        } else {
            swivel.setPower(0.0);
        }

        if(gamepad2.a) {
            extender.setPower(-0.5);
        } else if(gamepad2.y) {
            extender.setPower(0.5);
        } else {
            extender.setPower(0.0);
        }

        if(gamepad2.x) {
            //dump debris
        } else {
            //don't dump debris
        }

        if(gamepad2.right_bumper) {
           // pickup.setPower(1.0);
        }

        telemetry.addData("swivel value:", swivel.getCurrentPosition());
        telemetry.addData("parking brake", parkingBrake.getPosition());
        telemetry.addData("tapeMotor", tapeMotor.getPosition());
    }
}

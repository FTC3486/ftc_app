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
    DcMotor tapeMotor;
    ExtendedServo tapeTilt;
    double initialTilt = 0.5;
    DcMotor winchMotor;

    //Parking Brake

    //Turret
    DcMotor swivel;
    DcMotor extender;
    //int bottomPosition;
    //int middlePosition;
    //int highPosition;
    //Servo debrisDumper;

    //Plow

    //Pickup

    @Override
    public void init() {
        //Drivetrain
        leftfront = hardwareMap.dcMotor.get("leftfront");
        leftback = hardwareMap.dcMotor.get("leftback");
        leftback.setDirection(DcMotor.Direction.REVERSE);
        rightfront = hardwareMap.dcMotor.get("rightfront");
        rightback = hardwareMap.dcMotor.get("rightback");
        rightback.setDirection(DcMotor.Direction.REVERSE);
        driver = new Drive(this, 0.15f);

        //Grappling Hook
        tapeMotor = hardwareMap.dcMotor.get("tapeM");
        tapeTilt = new ExtendedServo(hardwareMap.servo.get("tapeT"));
        tapeTilt.setPosition(initialTilt);
        winchMotor = hardwareMap.dcMotor.get("wM");

        //Parking Brake

        //Turret
        swivel = hardwareMap.dcMotor.get("swivel");
        extender = hardwareMap.dcMotor.get("extender");
        extender.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        //debrisDumper = hardwareMap.servo.get("dd");
        //Plow

        //Pickup

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



        // Gamepad 2

        tapeTilt.runIf(gamepad2.dpad_up, +0.005);
        tapeTilt.runIf(gamepad2.dpad_down, -0.005);
        tapeTilt.runServo();

        if(gamepad2.dpad_right){
            tapeMotor.setPower(1.0);
        } else if(gamepad2.dpad_left) {
            tapeMotor.setPower(-1.0);
        } else tapeMotor.setPower(0.0);

        if(gamepad2.left_stick_x > 0.2) {
            swivel.setPower(0.5);
        } else if(gamepad2.left_stick_x < -0.2) {
            swivel.setPower(-0.5);
        } else {
            swivel.setPower(0.0);
        }

        if(gamepad2.a) {
            //swivel to center
            //extender.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            //extender.setTargetPosition(bottomPosition);
            //extender.setPower(0.5);
        } else if(gamepad2.b) {
            //extender.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            //extender.setTargetPosition(middlePosition);
            //extender.setPower(0.5);
        } else if(gamepad2.y) {
            //extender.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            //extender.setTargetPosition(highPosition);
            //extender.setPower(0.5);
        } else {
            //extender.setPower(0.0);
        }

        if(gamepad2.x) {
            //dump debris
        } else {
            //don't dumb debris
        }
    }
}

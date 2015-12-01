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
public class StateMachineAutonomous extends OpMode{
    //DriveTrain
    Drive driver;
    DcMotor leftfront,leftback,rightfront,rightback;

    //Grappling Hook
    DcMotor tapeMotor;
    ExtendedServo tapeTilt;
    double tapeTiltMonitorPosition;
    double initialTilt = 0.5;
    DcMotor winchMotor;

    //Scoop
    ExtendedServo lr;//left-right, up-down
    double r_state = 0;
    double centerPosition = 0.2; // left 8, center 47, right 85
    double leftPosition = 0.045;
    double rightPosition = 0.35;
    Servo ud;
    double upPosition = 0.55;
    double downPosition = 0.89;

    //Zoop-Zoop
    Servo zleft, zright;
    double zleftInit = 0.3; //0
    double zleftUp = .65;
    double zrightInit = 0.6; //255
    double zrightUp = .2;
    double zleftdown = .85; //140 down, 0 up
    double zrightdown = .01;//80 down, 255 up

    //BlockGate
    Servo blockGate;
    double bgopen = 0.7;
    double bgclose = 0.13;

    //Sweeper;
    DcMotor Sweeper;

    // Safety Hook
    DcMotor safetyHook;

    double b_state = 0;

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

        //Scoop
        lr = new ExtendedServo(hardwareMap.servo.get("lr"));
        lr.setPosition(centerPosition);

        ud = hardwareMap.servo.get("ud");
        ud.setPosition(downPosition);

        //Zoop-Zoop
        zleft = hardwareMap.servo.get("zleft");
        zleft.setPosition(zleftInit);

        zright = hardwareMap.servo.get("zright");
        zright.setPosition(zrightInit);

        //Block Gate
        blockGate = hardwareMap.servo.get("bG");
        blockGate.setPosition(bgclose);

        // Sweeper
        Sweeper = hardwareMap.dcMotor.get("sw");

        //Grappling Hook
        tapeMotor = hardwareMap.dcMotor.get("tapeM");
        tapeTilt = new ExtendedServo(hardwareMap.servo.get("tapeT"));
        tapeTilt.setPosition(initialTilt);
        winchMotor = hardwareMap.dcMotor.get("wM");

        // Safety hook
        safetyHook = hardwareMap.dcMotor.get("safetyHook");
    }
    @Override public void start ()

    {
        leftback.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightback.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    @Override public void loop ()

    {
        //----------------------------------------------------------------------
        //
        // State: Initialize (i.e. state_0).
        //
        switch (v_state) {
            case 0:
                leftback.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                rightback.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                v_state++;

                break;

            case 1:
                if(leftback.getCurrentPosition() == 0) {
                    v_state++;
                }
                break;


            case 2:
                zleft.setPosition(zleftdown);
                zright.setPosition(zrightdown);
                leftback.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                rightback.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                leftfront.setPower(.6f);
                leftback.setPower(.6f);
                rightfront.setPower(.6f);
                rightback.setPower(.6f);

                //
                // Have the motor shafts turned the required amount?
                //
                // If they haven't, then the op-mode remains in this state (i.e this
                // block will be executed the next time this method is called).
                //
                if (leftback.getCurrentPosition() > 1000) {
                    leftback.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    leftfront.setPower(0f);
                    leftback.setPower(0f);
                    rightfront.setPower(0f);
                    rightback.setPower(0f);
                    v_state++;
                }
                break;

            case 3:
                if(leftback.getCurrentPosition() == 0) {
                    v_state++;
                }
                break;

            case 4:
                leftback.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                rightback.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                leftfront.setPower(-.6f);
                leftback.setPower(-.6f);
                rightfront.setPower(.6f);
                rightback.setPower(.6f);

                //
                // Have the motor shafts turned the required amount?
                //
                // If they haven't, then the op-mode remains in this state (i.e this
                // block will be executed the next time this method is called).
                //
                if (leftback.getCurrentPosition() < -275) {
                    leftback.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    rightback.setMode(DcMotorController.RunMode.RESET_ENCODERS);

                    leftfront.setPower(0f);
                    leftback.setPower(0f);
                    rightfront.setPower(0f);
                    rightback.setPower(0f);
                    v_state++;
                }
                break;

            case 5:
                if(leftback.getCurrentPosition() == 0) {
                    v_state++;
                }
                break;

            case 6:
                leftback.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                rightback.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                leftfront.setPower(.6f);
                leftback.setPower(.6f);
                rightfront.setPower(.6f);
                rightback.setPower(.6f);

                //
                // Have the motor shafts turned the required amount?
                //
                // If they haven't, then the op-mode remains in this state (i.e this
                // block will be executed the next time this method is called).
                //
                if (leftback.getCurrentPosition() > 8300) {
                    leftback.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    rightback.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    leftfront.setPower(0f);
                    leftback.setPower(0f);
                    rightfront.setPower(0f);
                    rightback.setPower(0f);
                    v_state++;
                }
                break;

            case 7:
                if(leftback.getCurrentPosition() == 0) {
                    v_state++;
                }
                break;

            case 8:
                leftback.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                rightback.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                leftfront.setPower(.6f);
                leftback.setPower(.6f);
                rightfront.setPower(-.6f);
                rightback.setPower(-.6f);

                //
                // Have the motor shafts turned the required amount?
                //
                // If they haven't, then the op-mode remains in this state (i.e this
                // block will be executed the next time this method is called).
                //
                if (leftback.getCurrentPosition() > 1000) {
                    leftback.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    rightback.setMode(DcMotorController.RunMode.RESET_ENCODERS);

                    leftfront.setPower(0f);
                    leftback.setPower(0f);
                    rightfront.setPower(0f);
                    rightback.setPower(0f);
                    v_state++;
                }
                break;
        }
        telemetry.addData("state", v_state);

    }

    private int v_state = 0;

}


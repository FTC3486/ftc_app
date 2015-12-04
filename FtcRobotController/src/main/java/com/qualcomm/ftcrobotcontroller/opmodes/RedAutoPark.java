package com.qualcomm.ftcrobotcontroller.opmodes;
import com.jacobamason.FTCRC_Extensions.ExtendedServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by Matthew on 8/11/2015.
 */
public class RedAutoPark extends OpMode{
    //DriveTrain
    DcMotor leftfront,leftback,rightfront,rightback;

    //Grappling Hook
    DcMotor tapeMotor;
    ExtendedServo tapeTilt;
    double initialTilt = 0.5;
    DcMotor winchMotor;

    //Scoop
    ExtendedServo lr;//left-right, up-down
    double centerPosition = 0.2; // left 8, center 47, right 85

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

    // Gyro Sensor
    GyroSensor gyro;
    double gyroTarget;

    double waitTime;


    @Override
    public void init() {
        //Drivetrain
        leftfront = hardwareMap.dcMotor.get("leftfront");
        leftback = hardwareMap.dcMotor.get("leftback");
        leftback.setDirection(DcMotor.Direction.REVERSE);
        rightfront = hardwareMap.dcMotor.get("rightfront");
        rightback = hardwareMap.dcMotor.get("rightback");
        rightback.setDirection(DcMotor.Direction.REVERSE);

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
        Sweeper.setDirection(DcMotor.Direction.REVERSE);

        //Grappling Hook
        tapeMotor = hardwareMap.dcMotor.get("tapeM");
        tapeTilt = new ExtendedServo(hardwareMap.servo.get("tapeT"));
        tapeTilt.setPosition(initialTilt);
        winchMotor = hardwareMap.dcMotor.get("wM");

        // Safety hook
        safetyHook = hardwareMap.dcMotor.get("safetyHook");

        gyro = hardwareMap.gyroSensor.get("gyro");
        gyro.calibrate();

    }
    @Override public void start ()

    {
        Sweeper.setPower(1.0);
        while(gyro.isCalibrating()){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override public void loop ()

    {
        //----------------------------------------------------------------------
        //
        // State: Initialize (i.e. state_0).
        //
        switch (v_state) {
            case 0:
            case 3:
            case 8:
            case 11:
            case 16:
            case 19:
            case 26:
            case 30:
            case 35:
            case 38:
            case 42:
                leftfront.setPower(0f);
                leftback.setPower(0f);
                rightfront.setPower(0f);
                rightback.setPower(0f);
                leftback.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                rightback.setMode(DcMotorController.RunMode.RESET_ENCODERS);

                if(leftback.getCurrentPosition() == 0) {
                    v_state++;
                }
                break;

            case 1:
            case 4:
            case 9:
            case 12:
            case 17:
            case 20:
            case 27:
            case 31:
            case 36:
            case 39:
            case 43:
                leftback.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                rightback.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

                if(leftback.getMode() == DcMotorController.RunMode.RUN_USING_ENCODERS &&
                        rightback.getMode() == DcMotorController.RunMode.RUN_USING_ENCODERS) {
                    v_state++;
                }
                break;

            case 2:
                zleft.setPosition(zleftdown);
                zright.setPosition(zrightdown);
                leftfront.setPower(.6f);
                leftback.setPower(.6f);
                rightfront.setPower(.6f);
                rightback.setPower(.6f);
                telemetry.addData("lb encoder", leftback.getCurrentPosition());
                telemetry.addData("rb encoder", rightback.getCurrentPosition());
                if(Math.abs(leftback.getCurrentPosition()) > 1500 && Math.abs(rightback.getCurrentPosition()) > 1500) {
                    v_state++;
                }
                break;

            case 5:
                gyroTarget = 330;
                v_state++;
                break;

            //counterclockwise angles
            case 6:
            case 40:
                if(gyro.getHeading() < gyroTarget) {
                    leftfront.setPower(-.6);
                    leftback.setPower(-.6);
                    rightfront.setPower(.6);
                    rightback.setPower(.6);
                } else {
                    v_state++;
                }
                break;

            case 7:
            case 41:
                leftfront.setPower(-.6);
                leftback.setPower(-.6);
                rightfront.setPower(.6);
                rightback.setPower(.6);
                if(gyro.getHeading() <= gyroTarget) {
                    v_state++;
                }
                break;

            case 10:
                ud.setPosition(upPosition);
                leftfront.setPower(.6f);
                leftback.setPower(.6f);
                rightfront.setPower(.6f);
                rightback.setPower(.6f);
                telemetry.addData("lb encoder", leftback.getCurrentPosition());
                telemetry.addData("rb encoder", rightback.getCurrentPosition());
                if(Math.abs(leftback.getCurrentPosition()) >8300 && Math.abs(rightback.getCurrentPosition()) > 8300) {
                    v_state++;
                }
                break;

            //clockwise angles
            case 13:
                Sweeper.setPower(0);
                gyroTarget = 80;
                v_state++;
                break;

            case 14:
            case 33:
                if(gyro.getHeading() > gyroTarget) {
                    leftfront.setPower(.6);
                    leftback.setPower(.6);
                    rightfront.setPower(-.6);
                    rightback.setPower(-.6);
                } else {
                    v_state++;
                }
                break;

            case 15:
            case 34:
                leftfront.setPower(.6);
                leftback.setPower(.6);
                rightfront.setPower(-.6);
                rightback.setPower(-.6);
                if(gyro.getHeading() >= gyroTarget) {
                    v_state++;
                }
                break;

            case 18:
                leftfront.setPower(-.6f);
                leftback.setPower(-.6f);
                rightfront.setPower(-.6f);
                rightback.setPower(-.6f);
                telemetry.addData("lb encoder", leftback.getCurrentPosition());
                telemetry.addData("rb encoder", rightback.getCurrentPosition());
                if(Math.abs(leftback.getCurrentPosition()) > 1100 && Math.abs(rightback.getCurrentPosition()) > 1100) {
                    v_state++;
                }
                break;

            case 21:
                resetStartTime();
                waitTime = 0.5;
                v_state++;
                break;

            case 23:
                blockGate.setPosition(bgopen);
                resetStartTime();
                waitTime = 1;
                v_state++;
                break;

            case 22:
            case 24:
            case 29:
                if(getRuntime() >= waitTime)
                {
                    v_state++;
                }
                break;

            case 25:
                leftfront.setPower(.3f);
                leftback.setPower(.3f);
                rightfront.setPower(.3f);
                rightback.setPower(.3f);
                telemetry.addData("lb encoder", leftback.getCurrentPosition());
                telemetry.addData("rb encoder", rightback.getCurrentPosition());
                if(Math.abs(leftback.getCurrentPosition()) > 650 && Math.abs(rightback.getCurrentPosition()) > 650) {
                    blockGate.setPosition(bgclose);
                    resetStartTime();
                    waitTime = 2;
                    v_state++;
                }
                break;

            case 28:
                ud.setPosition(downPosition);
                v_state++;
                break;

            case 32:
                gyroTarget = 180;
                v_state++;
                break;

            case 37:
                leftfront.setPower(.6f);
                leftback.setPower(.6f);
                rightfront.setPower(.6f);
                rightback.setPower(.6f);
                telemetry.addData("lb encoder", leftback.getCurrentPosition());
                telemetry.addData("rb encoder", rightback.getCurrentPosition());
                if(Math.abs(leftback.getCurrentPosition()) > 1850 && Math.abs(rightback.getCurrentPosition()) > 1850) {
                    gyroTarget = 179;
                    v_state++;
                }
                break;

        }

        telemetry.addData("state", v_state);
    }
    private int v_state = 0;
}

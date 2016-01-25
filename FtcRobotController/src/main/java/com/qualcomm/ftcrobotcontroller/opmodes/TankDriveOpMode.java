package com.qualcomm.ftcrobotcontroller.opmodes;
import com.FTC3486.FTCRC_Extensions.Drive;
import com.FTC3486.FTCRC_Extensions.GamepadWrapper;
import com.FTC3486.FTCRC_Extensions.ExtendedServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by Matthew on 8/11/2015.
 */
public class TankDriveOpMode extends OpMode{
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
    double zleftInit = 0.2; //0
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
        Sweeper.setDirection(DcMotor.Direction.REVERSE);

        //Grappling Hook
        tapeMotor = hardwareMap.dcMotor.get("tapeM");
        tapeTilt = new ExtendedServo(hardwareMap.servo.get("tapeT"));
        tapeTilt.setPosition(initialTilt);
        winchMotor = hardwareMap.dcMotor.get("wM");

        // Safety hook
        safetyHook = hardwareMap.dcMotor.get("safetyHook");
    }

   /* public TankDriveOpMode()
    {
        // Get a better controller
        this.gamepad1 = new ExtendedGamepad();
        this.gamepad2 = new ExtendedGamepad();
    }*/

    @Override
    public void loop() {
        // Gamepad 1
        driver.forward_tank_drive(leftfront, leftback, rightfront, rightback);
        if(gamepad1.left_bumper) {
            if(b_state == 0) {
                b_state = 1;
                if (zleft.getPosition() < 0.75) {
                    zleft.setPosition(zleftdown);
                    zright.setPosition(zrightdown);
                } else if (zleft.getPosition() > 0.75) {
                    zleft.setPosition(zleftUp);
                    zright.setPosition(zrightUp);
                }
            }
        } else b_state = 0;

        if(gamepad1.right_trigger > 0.7){
            winchMotor.setPower(1.0);
        } else if(gamepad1.right_bumper){
            winchMotor.setPower(-1.0);
        } else winchMotor.setPower(0.0);



        // Gamepad 2

        if(Math.abs(gamepad2.right_stick_y) > 0.15f)
        {
            safetyHook.setPower(gamepad2.right_stick_y / 4.0);
        }
        else
        {
            safetyHook.setPower(0.0);
        }

        lr.runIf(gamepad2.b, +0.005);
        lr.runIf(gamepad2.x, -0.005);
        lr.runServo();

        if(gamepad2.right_bumper) {
            lr.setPosition(centerPosition);
        }

        if(gamepad2.y) {
            ud.setPosition(upPosition);
        } else if(gamepad2.a) {
            if(lr.getPosition() > .175 && lr.getPosition() < .225) {
                    ud.setPosition(downPosition);
                }
        }

        if(gamepad2.left_bumper) {
            if(ud.getPosition() < .69){
                blockGate.setPosition(bgopen);
            }
        } else blockGate.setPosition(bgclose);

        if(gamepad2.left_trigger > 0) {
            Sweeper.setPower(1.0);
        } else Sweeper.setPower(0.0);


        tapeTilt.runIf(gamepad2.dpad_up, +0.005);
        tapeTilt.runIf(gamepad2.dpad_down, -0.005);
        tapeTilt.runServo();

//        if(gamepad2.dpad_up){
//            tapeTilt.setPosition(tapeTilt.getPosition() > 0.995 ? 1.0 : tapeTilt.getPosition() + 0.005);
//        } else if(gamepad2.dpad_down){
//            tapeTilt.setPosition(tapeTilt.getPosition() < 0.005 ? 0.0 : tapeTilt.getPosition() - 0.005);
//        }

        if(gamepad2.dpad_right){
            tapeMotor.setPower(1.0);
        } else if(gamepad2.dpad_left) {
            tapeMotor.setPower(-1.0);
        } else tapeMotor.setPower(0.0);

        telemetry.addData("servo value:", lr.getPosition());
    }


}

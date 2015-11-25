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

    //Grappling Hook
    DcMotor tapeMotor;
    Servo tapeTilt;
    double tapeTiltMonitorPosition;
    double initialTilt = 0.4;
    DcMotor winchMotor;

   //Scoop
    Servo lr;//left-right, up-down
    double l_state = 0;
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

    double b_state = 0;

    @Override
    public void init() {
        //Drivetrain
        leftfront = hardwareMap.dcMotor.get("leftfront");
        leftback = hardwareMap.dcMotor.get("leftback");
        rightfront = hardwareMap.dcMotor.get("rightfront");
        rightback = hardwareMap.dcMotor.get("rightback");
        driver = new Drive(this, 0.15f);

        //Scoop
        lr = hardwareMap.servo.get("lr");
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
        tapeTilt = hardwareMap.servo.get("tapeT");
        tapeTilt.setPosition(initialTilt);
        winchMotor = hardwareMap.dcMotor.get("wM");

    }

    @Override
    public void loop() {
        // Gamepad 1
        driver.tank_drive(leftfront, leftback, rightfront, rightback);

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

        //Gamepad 2
        if(gamepad2.x) {
            lr.setPosition(lr.getPosition() - 0.05);
        }

        if(gamepad2.b) {
            l_state = 1;
            lr.setPosition(lr.getPosition() + 0.05);
        }

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
            Sweeper.setPower(-1.0);
        } else Sweeper.setPower(0.0);

        if(gamepad2.dpad_up){
            tapeTilt.setPosition(tapeTilt.getPosition() + 0.005);
        } else if(gamepad2.dpad_down){
            tapeTilt.setPosition(tapeTilt.getPosition() - 0.005);
        }

        if(gamepad2.dpad_right){
            tapeMotor.setPower(1.0);
        } else if(gamepad2.dpad_left) {
            tapeMotor.setPower(-1.0);
        } else tapeMotor.setPower(0.0);

        telemetry.addData("servo value:", ud.getPosition());
    }


}

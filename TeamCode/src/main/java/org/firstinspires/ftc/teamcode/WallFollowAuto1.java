package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Owner_2 on 1/31/2017.
 */
@Autonomous(name = "Auto With Range wall follow", group = "Test")

public class WallFollowAuto1 extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    ModernRoboticsI2cRangeSensor rangeSensor1;
    ModernRoboticsI2cRangeSensor rangeSensor2;
    Drivetrain driveTrain;
    public DcMotor Left1 = null;
    public DcMotor Left2 = null;
    public DcMotor Right1 = null;
    public DcMotor Right2 = null;

    OpticalDistanceSensor left_ods;
    OpticalDistanceSensor right_ods;
    OpticalDistanceSensor right_back_ods;

    //Raw value is between 0 and 1
    double odsReadingRaw1;
    double odsReadingRaw2;

    // odsReadingRaw to the power of (-0.5)
    static double odsReadingLinear1;
    static double odsReadingLinear2;

    int zAccumulated;  //Total rotation left/right
    int target = 0;  //Desired angle to turn to
    GyroSensor sensorGyro;  //General Gyro Sensor allows us to point to the sensor in the configuration file.
    ModernRoboticsI2cGyro mrGyro;  //ModernRoboticsI2cGyro allows us to .getIntegratedZValue()

    @Override
    public void runOpMode() throws InterruptedException {
        Left1 = hardwareMap.dcMotor.get("Left 1");
        Left2 = hardwareMap.dcMotor.get("Left 2");
        Right1 = hardwareMap.dcMotor.get("Right 1");
        Right2 = hardwareMap.dcMotor.get("Right 2");

        Left1.setDirection(DcMotor.Direction.REVERSE);
        Left2.setDirection(DcMotor.Direction.REVERSE);
        Right1.setDirection(DcMotor.Direction.FORWARD);
        Right2.setDirection(DcMotor.Direction.FORWARD);
        driveTrain = new Drivetrain.Builder()
                .addLeftMotor(Left1)
                .addLeftMotorWithEncoder(Left2)
                .addRightMotor(Right1)
                .addRightMotorWithEncoder(Right2)
                .build();
        rangeSensor1 = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "Range 1");
        rangeSensor2 = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "Range 2");
        left_ods = hardwareMap.opticalDistanceSensor.get("Left ods");
        right_ods = hardwareMap.opticalDistanceSensor.get("Right ods");
        right_back_ods = hardwareMap.opticalDistanceSensor.get("Right Back ods");


        sensorGyro = hardwareMap.gyroSensor.get("gyroSensor");  //Point to the gyroSensor in the configuration file
        mrGyro = (ModernRoboticsI2cGyro) sensorGyro;      //ModernRoboticsI2cGyro allows us to .getIntegratedZValue()
        mrGyro.calibrate();
        mrGyro.resetZAxisIntegrator();

        waitForStart();
        while (mrGyro.isCalibrating()) {

        }

/*
            mrGyro.resetZAxisIntegrator();
        driveStraightBackwards(-2800, -0.5);
        driveTrain.haltDrive();
        mrGyro.resetZAxisIntegrator();
        driveTrain.resetMotorEncoders();
        sleep(100);
            driveToLineRightBack(0.05, -0.3);
        driveTrain.haltDrive();
        mrGyro.resetZAxisIntegrator();
        driveTrain.resetMotorEncoders();
            sleep(100);
        driveStraightBackwards(-600, -0.3);
        mrGyro.resetZAxisIntegrator();
        driveTrain.resetMotorEncoders();
        sleep(100);
        encoderDrive(0.5,5,-5,3);
*/
        driveTrain.resetMotorEncoders();
        wallfollow(15,-3000);
        driveTrain.haltDrive();



    }
    public void wallfollow(double RangeCm, int encodercounts){
        double leftSpeed;
        double rightSpeed;

        double startPositionLeft = Left2.getCurrentPosition();//Starting position
        double startPositionRight = Right2.getCurrentPosition();

        while(Left2.getCurrentPosition() > encodercounts + startPositionLeft && Right2.getCurrentPosition()> encodercounts + startPositionRight)  {
            if (rangeSensor2.cmUltrasonic()>RangeCm){
                driveTrain.setPowers(-0.35,-0.3);
            }else if(rangeSensor2.cmUltrasonic()<RangeCm){
                driveTrain.setPowers(-0.3,-0.35);
            }else if(rangeSensor2.cmUltrasonic() == RangeCm){
                driveTrain.setPowers(-0.3, -0.3);
            }
        }

    }
    public void driveToRange2(double RangeCm, double power) {
        double leftSpeed; //Power to feed the motors
        double rightSpeed;


        double target = mrGyro.getIntegratedZValue();  //Starting direction
        double startPositionLeft = Left2.getCurrentPosition();//Starting position
        double startPositionRight = Right2.getCurrentPosition();

        while (rangeSensor2.cmUltrasonic()> RangeCm /*&& Left2.getCurrentPosition() < startPositionLeft && Right2.getCurrentPosition()< startPositionRight*/){   //While we have not passed out intended distance
            zAccumulated = mrGyro.getIntegratedZValue(); //Current direction

            leftSpeed = power + (zAccumulated - target) / 20;  //Calculate speed for each side
            rightSpeed = power - (zAccumulated - target) / 20;  //See Gyro Straight video for detailed explanation

            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);

            Left1.setPower(leftSpeed);
            Left2.setPower(leftSpeed);
            Right1.setPower(rightSpeed);
            Right2.setPower(rightSpeed);



        }
        Left1.setPower(0);
        Left2.setPower(0);//Stop the motors
        Right1.setPower(0);
        Right2.setPower(0);
    }
    public void driveToLineRightBack(double colorValue, double power) {
        double leftSpeed; //Power to feed the motors
        double rightSpeed;


        double target = mrGyro.getIntegratedZValue();  //Starting direction
        double startPositionLeft = Left2.getCurrentPosition();//Starting position
        double startPositionRight = Right2.getCurrentPosition();

        while (right_back_ods.getLightDetected()<colorValue /*&& Left2.getCurrentPosition() < startPositionLeft && Right2.getCurrentPosition()< startPositionRight*/){   //While we have not passed out intended distance
            zAccumulated = mrGyro.getIntegratedZValue(); //Current direction

            leftSpeed = power + (zAccumulated - target) / 20;  //Calculate speed for each side
            rightSpeed = power - (zAccumulated - target) / 20;  //See Gyro Straight video for detailed explanation

            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);

            Left1.setPower(leftSpeed);
            Left2.setPower(leftSpeed);
            Right1.setPower(rightSpeed);
            Right2.setPower(rightSpeed);



        }
        Left1.setPower(0);
        Left2.setPower(0);//Stop the motors
        Right1.setPower(0);
        Right2.setPower(0);
    }

    public void driveStraightForwards(int encodercounts, double power) {
        double leftSpeed; //Power to feed the motors
        double rightSpeed;

        double target = mrGyro.getIntegratedZValue();  //Starting direction
        double startPositionLeft = Left2.getCurrentPosition();//Starting position
        double startPositionRight = Right2.getCurrentPosition();

        while (Left2.getCurrentPosition() < encodercounts + startPositionLeft && Right2.getCurrentPosition()< encodercounts + startPositionRight) {  //While we have not passed out intended distance
            zAccumulated = mrGyro.getIntegratedZValue();  //Current direction

            leftSpeed = power + (zAccumulated - target) / 20;  //Calculate speed for each side
            rightSpeed = power - (zAccumulated - target) / 20;  //See Gyro Straight video for detailed explanation

            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);

            Left1.setPower(leftSpeed);
            Left2.setPower(leftSpeed);
            Right1.setPower(rightSpeed);
            Right2.setPower(rightSpeed);



        }
        Left1.setPower(0);
        Left2.setPower(0);//Stop the motors
        Right1.setPower(0);
        Right2.setPower(0);
    }
    public void driveStraightBackwards(int encodercounts, double power) {
        double leftSpeed; //Power to feed the motors
        double rightSpeed;

        double target = mrGyro.getIntegratedZValue();  //Starting direction
        double startPositionLeft = Left2.getCurrentPosition();//Starting position
        double startPositionRight = Right2.getCurrentPosition();

        while (Left2.getCurrentPosition() > encodercounts + startPositionLeft && Right2.getCurrentPosition()> encodercounts + startPositionRight) {  //While we have not passed out intended distance
            zAccumulated = mrGyro.getIntegratedZValue();  //Current direction

            leftSpeed = power + (zAccumulated - target) / 20;  //Calculate speed for each side
            rightSpeed = power - (zAccumulated - target) / 20;  //See Gyro Straight video for detailed explanation

            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);

            Left1.setPower(leftSpeed);
            Left2.setPower(leftSpeed);
            Right1.setPower(rightSpeed);
            Right2.setPower(rightSpeed);



        }
        Left1.setPower(0);
        Left2.setPower(0);//Stop the motors
        Right1.setPower(0);
        Right2.setPower(0);
    }



    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;
    static final double     DRIVE_GEAR_REDUCTION    = 0.8 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.5;
    static final double     TURN_SPEED              = 0.5;

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = Left2.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = Right2.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            Left1.setTargetPosition(newLeftTarget);
            Left2.setTargetPosition(newLeftTarget);
            Right1.setTargetPosition(newRightTarget);
            Right2.setTargetPosition(newRightTarget);


            // Turn On RUN_TO_POSITION
            Left1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Left2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Right1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Right2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            Left1.setPower(Math.abs(speed));
            Left2.setPower(Math.abs(speed));
            Right1.setPower(Math.abs(speed));
            Right2.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (Left1.isBusy() && Left2.isBusy() && Right1.isBusy() && Right2.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        Left1.getCurrentPosition(),
                        Left2.getCurrentPosition(),
                        Right1.getCurrentPosition(),
                        Right2.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            Left1.setPower(0);
            Left2.setPower(0);
            Right1.setPower(0);
            Right2.setPower(0);

            // Turn off RUN_TO_POSITION
            Left1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Left2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Right1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Right2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }

    }


}




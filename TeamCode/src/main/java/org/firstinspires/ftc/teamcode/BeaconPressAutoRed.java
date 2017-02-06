package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Owner_2 on 12/31/2016.
 */
@Autonomous(name = "Press Beacon and Score balls Red", group = "RedAutonomus")
public class BeaconPressAutoRed extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    Drivetrain driveTrain;
    ParticleAcclerator accelerator1;
    ParticleAcclerator accelerator2;
    Pickup pickup;
    TroughGate troughGate;
    Column column;
    TuskGate tuskGate;
    CapballHolder capballHolder;
    BaconActivator baconActivator;
    ColorSensor colorSensor;
    OpticalDistanceSensor left_ods;
    OpticalDistanceSensor right_ods;
    ModernRoboticsI2cRangeSensor rangeSensor;

    AutoDriver autoDriver;


    //GyroAutoDriver gyroAutoDriver;


    int zAccumulated;  //Total rotation left/right
    int target = 0;  //Desired angle to turn to
    GyroSensor sensorGyro;  //General Gyro Sensor allows us to point to the sensor in the configuration file.
    ModernRoboticsI2cGyro mrGyro;  //ModernRoboticsI2cGyro allows us to .getIntegratedZValue()

    public DcMotor Left1 = null;
    public DcMotor Left2 = null;
    public DcMotor Right1 = null;
    public DcMotor Right2 = null;

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
        //autoDriver = new GyroscopicAutoDriver(this, driveTrain, "gyroSensor", hardwareMap);
        pickup = new Pickup("Pickup", hardwareMap);
        troughGate = new TroughGate("Trough Gate", hardwareMap);
        accelerator1 = new ParticleAcclerator("Accelerator 1", hardwareMap);
        accelerator2 = new ParticleAcclerator("Accelerator 2", hardwareMap);
        column = new Column("Column", hardwareMap);
        tuskGate = new TuskGate("Tusk Gate", hardwareMap);
        capballHolder = new CapballHolder("Capball Holder", hardwareMap);
        baconActivator = new BaconActivator("Bacon Activator", hardwareMap);
        colorSensor = hardwareMap.colorSensor.get("Beacon Color");
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "Range");
        left_ods = hardwareMap.opticalDistanceSensor.get("Left ods");
        right_ods = hardwareMap.opticalDistanceSensor.get("Right ods");
        //gyroAutoDriver = new GyroAutoDriver("GyroAuto", hardwareMap);
        accelerator1.accleratorPower = 0;
        accelerator2.accleratorPower = 0;
        baconActivator.armDown();
        telemetry.update();
        sensorGyro = hardwareMap.gyroSensor.get("gyroSensor");  //Point to the gyro in the configuration file
        mrGyro = (ModernRoboticsI2cGyro) sensorGyro;      //ModernRoboticsI2cGyro allows us to .getIntegratedZValue()
        mrGyro.calibrate();
        mrGyro.resetZAxisIntegrator();
        colorSensor.enableLed(false);
        troughGate.closeGate();


        waitForStart();
        runtime.reset();
            while (mrGyro.isCalibrating()) {

            }
            driveStraightForwards(2800, 0.5);
            sleep(200);
        driveTrain.resetMotorEncoders();
            while (right_ods.getLightDetected() < 0.06) {
                driveTrain.setPowers(0.2, 0.2);
            }
            driveTrain.haltDrive();
            sleep(200);
            driveTrain.resetMotorEncoders();
            driveStraightForwards(200, 0.5);
            driveTrain.haltDrive();
            sleep(200);
            driveTrain.resetMotorEncoders();
            encoderDrive(0.3, -6.5, 6.5, 10);
            driveTrain.haltDrive();
        driveTrain.resetMotorEncoders();
            baconActivator.sensorScanning();
            sleep(200);
            while (rangeSensor.rawUltrasonic() > 25) {
                driveTrain.setPowers(0.3, 0.3);
            }
            driveTrain.haltDrive();
        driveTrain.resetMotorEncoders();
            sleep(200);
            if (colorSensor.blue() >= 2) {
                baconActivator.armUp();
                sleep(500);
                driveTrain.setPowers(0.2, 0.2);
                sleep(600);
                driveTrain.haltDrive();
            } else {
                baconActivator.armPressing();
                sleep(500);
                driveTrain.setPowers(0.2, 0.2);
                sleep(500);
                driveTrain.haltDrive();
            }
        driveTrain.resetMotorEncoders();
        sensorGyro.resetZAxisIntegrator();
        sleep(100);

        //driveStraightBackwards(-2100, -0.5);
        encoderDrive(0.5, -30, -30, 10);
        driveTrain.haltDrive();

            while (accelerator1.accleratorPower < 1 && accelerator2.accleratorPower < 1) {
                accelerator1.rampup();
                accelerator2.rampup();
            }
            accelerator1.run();
            accelerator2.run();

            troughGate.openGate();
            sleep(2000);
            troughGate.closeGate();






            baconActivator.armUp();
    }
   /* public void driveToLine(double colorValue, double power) {
        double leftSpeed; //Power to feed the motors
        double rightSpeed;


        double target = mrGyro.getIntegratedZValue();  //Starting direction
        double startPositionLeft = Left2.getCurrentPosition();//Starting position
        double startPositionRight = Right2.getCurrentPosition();

        while (right_ods.getRawLightDetected()<colorValue && Left2.getCurrentPosition() < startPositionLeft && Right2.getCurrentPosition()< startPositionRight){   //While we have not passed out intended distance
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
    }*/

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



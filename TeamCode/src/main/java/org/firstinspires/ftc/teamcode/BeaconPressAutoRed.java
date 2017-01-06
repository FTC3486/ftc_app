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
@Autonomous(name = "Press Beacons RedAuto", group = "Autonomus2016")
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
    public void runOpMode(){
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
        autoDriver = new GyroscopicAutoDriver(this, driveTrain, "gyroSensor", hardwareMap);
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


        waitForStart();
        runtime.reset();

        while (mrGyro.isCalibrating()) { //Ensure calibration is complete (usually 2 seconds)
        }


        //autoDriver.drive_forward(2000);

        //driveStraight(5000, 0.75);

        turnAbsolute(90);





    }
    public void driveStraight(int encodercounts, double power) {
        double leftSpeed; //Power to feed the motors
        double rightSpeed;

        double target = mrGyro.getIntegratedZValue();  //Starting direction
        double startPositionLeft = Left2.getCurrentPosition();//Starting position
        double startPositionRight = Right2.getCurrentPosition();

        while (driveTrain.getLeftEncoderCount() < encodercounts + startPositionLeft && driveTrain.getRightEncoderCount()< encodercounts + startPositionRight) {  //While we have not passed out intended distance
            zAccumulated = mrGyro.getIntegratedZValue();  //Current direction

            leftSpeed = power + (zAccumulated - target) / 8;  //Calculate speed for each side
            rightSpeed = power - (zAccumulated - target) / 8;  //See Gyro Straight video for detailed explanation

            leftSpeed = Range.clip(leftSpeed, -0.75, 0.75);
            rightSpeed = Range.clip(rightSpeed, -0.75, 0.75);

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

    //This function turns a number of degrees compared to where the robot is. Positive numbers trn left.
    public void turn(int target) throws InterruptedException {
        turnAbsolute(target + mrGyro.getIntegratedZValue());
    }

    //This function turns a number of degrees compared to where the robot was when the program started. Positive numbers trn left.
    public void turnAbsolute(int target) {
        zAccumulated = mrGyro.getIntegratedZValue();  //Set variables to gyro readings
        double turnSpeed = 0.3;

        while (Math.abs(zAccumulated - target) > 3) {  //Continue while the robot direction is further than three degrees from the target
            if (zAccumulated > target) {  //if gyro is positive, we will turn right
                Left1.setPower(turnSpeed);
                Left2.setPower(turnSpeed);
                Right1.setPower(-turnSpeed);
                Right2.setPower(-turnSpeed);

            }

            if (zAccumulated < target) {  //if gyro is positive, we will turn left
                Left1.setPower(-turnSpeed);
                Left2.setPower(-turnSpeed);
                Right1.setPower(turnSpeed);
                Right2.setPower(turnSpeed);
            }

            zAccumulated = mrGyro.getIntegratedZValue();  //Set variables to gyro readings

        }

        Left1.setPower(0);
        Left2.setPower(0);
        Right1.setPower(0);
        Right2.setPower(0);

    }
}



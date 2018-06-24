package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

        /*
        * Filename: HardwareConfiguration.java
        *
        * Description:
        *     This class contains the methods that use the range sensors for predefined autonomous movements.
        *     Is utlized by the Robot.java class in order to program the Velocity Vortex robot
        *
        * Methods:
        *     HardwareConfiguration
        *           - Robot Components
        *           - Sensors
        *           - Auto Drivers
        *           - Initialize Robot Components
        *
        *
        * Setup Instructions:
        *     -To add a servo type public Servo servoName; under the "//Servos" section below,
        *     then go to the "//Define Servos" section and type servoName = opMode.hardwareMap.servo.get("servoName");
        *     For an example you can find an example servo below
        *
        *     -To add a motor type public DcMotor motorName; under the "//Motors" section below,
        *     then go to the "//Define Motors" section and type motorName = opMode.hardwareMap.dcMotor.get("motorName");
        *     For an example you can find an example motor below
        *
        *     -To add a sensor type public SensorType sensorName; in the "//Sensors" section below,
        *     then go to the "//Define Sensors" section and type sensorName = opMode.hardwareMap.sensorType.get("sensorName");
        *     It should be noted that the syntax for the last step varies slightly depending on the type of sensor being defined
        *
        *     -To add a submodule type public Submodule submodule; in the "//Robot Components" section below,
        *     then go to the "//Define robot components" section and type submodule = new Submodule("submoduleName", opmode.hardwareMap);
        *
        *     -To add an auto driver type public AutoDriver autoDriver; in the "//Auto Drivers" section below,
        *     then go to the "//Define Auto Drivers" section and type autoDriver = new autoDriver(this);
        *
        * Changelog:
        *     -Created by Team 3486 on 1/8/2017.
        *     -Edited file description and documentation 7/23/17
        *     -Added Setup Instructions in the documentation 7/25/17
        */


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.teamcode.Subsystems.BaconActivator;
import org.firstinspires.ftc.teamcode.Subsystems.CapballHolder;
import org.firstinspires.ftc.teamcode.Subsystems.Column;
import org.firstinspires.ftc.teamcode.Subsystems.ParticleAccelerator;
import org.firstinspires.ftc.teamcode.Subsystems.Pickup;
import org.firstinspires.ftc.teamcode.Subsystems.TroughGate;
import org.firstinspires.ftc.teamcode.Subsystems.TuskGate;

public class HardwareConfiguration
{
    LinearOpMode opMode;

    //Robot Components
    public Drivetrain drivetrain;
    public ParticleAccelerator accelerator;
    public Pickup pickup;
    public TroughGate troughGate;
    public Column column;
    public TuskGate tuskGate;
    public CapballHolder capballHolder;
    public BaconActivator baconActivator;

    //Sensors
    public ColorSensor colorSensor;
    public OpticalDistanceSensor leftOpticalDistanceSensor;
    public OpticalDistanceSensor rightOpticalDistanceSensor;
    public ModernRoboticsI2cGyro gyroSensor;
    public RangeSensor leftRangeSensor;
    public RangeSensor rightRangeSensor;

    I2cDevice adafruitIMUDevice;
    public BNO055IMU adafruitIMU;

    //Auto Drivers
    public GyroAutoDriver gyroAutoDriver;
    public EncoderAutoDriver encoderAutoDriver;
    public RangeAutoDriver rangeAutoDriver;
    public OpticalDistanceAutoDriver opticalDistanceAutoDriver;

    HardwareConfiguration(LinearOpMode opMode){
        this.opMode = opMode;
        //Define robot components
        DcMotor Left1 = opMode.hardwareMap.dcMotor.get("Left 1");
        DcMotor Left2 = opMode.hardwareMap.dcMotor.get("Left 2");
        DcMotor Right1 = opMode.hardwareMap.dcMotor.get("Right 1");
        DcMotor Right2 = opMode.hardwareMap.dcMotor.get("Right 2");

        Left1.setDirection(DcMotor.Direction.REVERSE);
        Left2.setDirection(DcMotor.Direction.REVERSE);
        Right1.setDirection(DcMotor.Direction.FORWARD);
        Right2.setDirection(DcMotor.Direction.FORWARD);

        drivetrain = new Drivetrain.Builder()
                .addLeftMotorWithEncoder(Left1)
                .addLeftMotorWithEncoder(Left2)
                .addRightMotorWithEncoder(Right1)
                .addRightMotorWithEncoder(Right2)
                .build();

        pickup = new Pickup("Pickup", opMode.hardwareMap);
        troughGate = new TroughGate("Trough Gate", opMode.hardwareMap);
        accelerator = new ParticleAccelerator("Accelerator 1", opMode.hardwareMap);
        column = new Column("Column 1", "Column 2", opMode.hardwareMap);
        tuskGate = new TuskGate("Tusk Gate", opMode.hardwareMap);
        capballHolder = new CapballHolder("Capball Holder", opMode.hardwareMap);
        baconActivator = new BaconActivator("Bacon Activator", opMode.hardwareMap);

        //Define sensors
        colorSensor = opMode.hardwareMap.colorSensor.get("Beacon Color");
        leftOpticalDistanceSensor = opMode.hardwareMap.opticalDistanceSensor.get("Left ods");
        rightOpticalDistanceSensor = opMode.hardwareMap.opticalDistanceSensor.get("Right ods");
        gyroSensor = (ModernRoboticsI2cGyro) opMode.hardwareMap.gyroSensor.get("gyroSensor");
        rightRangeSensor = new RangeSensor("Range 1", 0x28, opMode.hardwareMap);
        leftRangeSensor = new RangeSensor("Range 2", 0x2a, opMode.hardwareMap);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        adafruitIMU = opMode.hardwareMap.get(BNO055IMU.class, "imu");
        adafruitIMU.initialize(parameters);

        //Define auto drivers
        gyroAutoDriver = new GyroAutoDriver(this);
        encoderAutoDriver = new EncoderAutoDriver(this);
        rangeAutoDriver = new RangeAutoDriver(this);
        opticalDistanceAutoDriver = new OpticalDistanceAutoDriver(this);
    }

    void init() {
        //Initialize Robot Components
        accelerator.acceleratorPower = 0;
        baconActivator.armDown();
        colorSensor.enableLed(false);
        troughGate.closeGate();
        drivetrain.resetMotorEncoders();
        gyroSensor.calibrate();
        while(gyroSensor.isCalibrating()) {
            //Wait for calibration
        }
    }
}


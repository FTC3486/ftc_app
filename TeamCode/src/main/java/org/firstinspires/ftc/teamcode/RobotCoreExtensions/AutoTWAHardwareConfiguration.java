package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Subsystems.GlyphGrabber;
import org.firstinspires.ftc.teamcode.Subsystems.GlyphLift;
import org.firstinspires.ftc.teamcode.Subsystems.GlyphSpinner;
import org.firstinspires.ftc.teamcode.Subsystems.JewelArm;
import org.firstinspires.ftc.teamcode.Subsystems.RelicArm;
import org.firstinspires.ftc.teamcode.Subsystems.RelicClaw;
import org.firstinspires.ftc.teamcode.Subsystems.RelicLift;

public class AutoTWAHardwareConfiguration
{

    LinearOpMode opMode;
    //Robot Components
    public Drivetrain drivetrain;
    public GlyphSpinner glyphSpinner;
    public GlyphLift glyphLift;
    public GlyphGrabber glyphGrabber;
    public RelicLift relicLift;
    public RelicArm relicArm;
    public RelicClaw relicClaw;
    public JewelArm jewelArm;
    //Sensors
    public BNO055IMU adafruitIMU;


    //Auto Drivers
    public GyroAutoDriver2017 gyroAutoDriver;
    public EncoderAutoDriver2017 encoderAutoDriver;
    public RangeAutoDriver rangeAutoDriver;
    public OpticalDistanceAutoDriver opticalDistanceAutoDriver;

    AutoTWAHardwareConfiguration(LinearOpMode opMode)
    {
        this.opMode = opMode;

        //Define robot components
        DcMotor left1 = opMode.hardwareMap.dcMotor.get("left1");
        DcMotor left2 = opMode.hardwareMap.dcMotor.get("left2");
        DcMotor right1 = opMode.hardwareMap.dcMotor.get("right1");
        DcMotor right2 = opMode.hardwareMap.dcMotor.get("right2");
        left1.setDirection(DcMotor.Direction.REVERSE);
        left2.setDirection(DcMotor.Direction.REVERSE);
        right1.setDirection(DcMotor.Direction.FORWARD);
        right2.setDirection(DcMotor.Direction.FORWARD);


        drivetrain = new Drivetrain.Builder()
                .addLeftMotor(left1)
                .addLeftMotorWithEncoder(left2)
                .addRightMotor(right1)
                .addRightMotorWithEncoder(right2)
                .build();

        glyphGrabber = new GlyphGrabber("leftservo1","leftservo2","rightservo1","rightservo2", opMode.hardwareMap);
        glyphSpinner = new GlyphSpinner("spinner", "SpinnerTouch1", "SpinnerTouch2", opMode.hardwareMap);
        glyphLift = new GlyphLift("GlyphLift", "LiftTouch", opMode.hardwareMap);
        relicLift = new RelicLift("RelicLift", opMode.hardwareMap);
        relicArm = new RelicArm("RelicArm", opMode.hardwareMap);
        relicClaw = new RelicClaw("ClawServo1","ClawServo2", "Pivot", opMode.hardwareMap);
        jewelArm = new JewelArm("JewelArm", "JewelColor", opMode.hardwareMap);

        //Define sensors
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        adafruitIMU = opMode.hardwareMap.get(BNO055IMU.class, "imu 1");
        adafruitIMU.initialize(parameters);

        //Define auto drivers

        gyroAutoDriver = new GyroAutoDriver2017(this);
        encoderAutoDriver = new EncoderAutoDriver2017(this);
    }

    void init()
    {

        // :) oh happy day, there is nothing here. :)
    }
}


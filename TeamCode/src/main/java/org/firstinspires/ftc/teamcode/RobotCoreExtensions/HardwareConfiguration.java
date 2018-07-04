package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Subsystems.JewelArm;
import org.firstinspires.ftc.teamcode.Subsystems.RelicArm;
import org.firstinspires.ftc.teamcode.Subsystems.RelicClaw;
import org.firstinspires.ftc.teamcode.Subsystems.RelicLift;

public class HardwareConfiguration
{
    OpMode opMode;
    LinearOpMode linearOpMode;
    //Rover Components
    public Drivetrain drivetrain;
    public RelicLift relicLift;
    public RelicArm relicArm;
    public RelicClaw relicClaw;
    public JewelArm jewelArm;

    //Sensors
    public BNO055IMU adafruitIMU;
    RangeSensor leftRangeSensor;
    RangeSensor rightRangeSensor;

    HardwareConfiguration(OpMode opMode)
    {
        this.opMode = opMode;

        //Define rover components
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
        adafruitIMU = opMode.hardwareMap.get(BNO055IMU.class, "imu");
        adafruitIMU.initialize(parameters);

        leftRangeSensor = new RangeSensor("LeftRangeSensor",  0x28, opMode.hardwareMap);
        rightRangeSensor = new RangeSensor("RightRangeSensor",  0x2a, opMode.hardwareMap);
    }

    void init()
    {
        // :) oh happy day, there is nothing here. :)
    }
}


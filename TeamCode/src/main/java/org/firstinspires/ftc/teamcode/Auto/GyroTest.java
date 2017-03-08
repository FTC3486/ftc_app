package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Extension.EncoderAutoDriver;
import org.firstinspires.ftc.teamcode.Extension.GyroAutoDriver;
import org.firstinspires.ftc.teamcode.Extension.RangeAutoDriver;
import org.firstinspires.ftc.teamcode.Subsystems.BaconActivator;
import org.firstinspires.ftc.teamcode.Subsystems.CapballHolder;
import org.firstinspires.ftc.teamcode.Subsystems.Column;
import org.firstinspires.ftc.teamcode.Extension.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.ParticleAcclerator;
import org.firstinspires.ftc.teamcode.Subsystems.Pickup;
import org.firstinspires.ftc.teamcode.Subsystems.TroughGate;
import org.firstinspires.ftc.teamcode.Subsystems.TuskGate;

/**
 * Created by Owner_2 on 1/12/2017.
 */
@Autonomous(name = "GyroTest", group = "BlueAutonomus")

public class GyroTest extends LinearOpMode {

    //Robot Components
    Drivetrain driveTrain;
    ParticleAcclerator accelerator1;
    Pickup pickup;
    TroughGate troughGate;
    Column column;
    TuskGate tuskGate;
    CapballHolder capballHolder;
    BaconActivator baconActivator;

    //Sensors
    ColorSensor colorSensor;
    OpticalDistanceSensor left_ods;
    OpticalDistanceSensor right_ods;
    ModernRoboticsI2cRangeSensor rangeSensor;

    //Auto Drivers
    GyroAutoDriver gyroAutoDriver;
    EncoderAutoDriver encoderAutoDriver;
    RangeAutoDriver rangeAutoDriver;

    public DcMotor Left1 = null;
    public DcMotor Left2 = null;
    public DcMotor Right1 = null;
    public DcMotor Right2 = null;

    @Override
    public void runOpMode() throws InterruptedException {
        //Define robot components
        Left1 = hardwareMap.dcMotor.get("Left 1");
        Left2 = hardwareMap.dcMotor.get("Left 2");
        Right1 = hardwareMap.dcMotor.get("Right 1");
        Right2 = hardwareMap.dcMotor.get("Right 2");

        Left1.setDirection(DcMotor.Direction.REVERSE);
        Left2.setDirection(DcMotor.Direction.REVERSE);
        Right1.setDirection(DcMotor.Direction.FORWARD);
        Right2.setDirection(DcMotor.Direction.FORWARD);
        driveTrain = new Drivetrain.Builder()
                .addLeftMotorWithEncoder(Left1)
                .addLeftMotorWithEncoder(Left2)
                .addRightMotorWithEncoder(Right1)
                .addRightMotorWithEncoder(Right2)
                .build();
        pickup = new Pickup("Pickup", hardwareMap);
        troughGate = new TroughGate("Trough Gate", hardwareMap);
        accelerator1 = new ParticleAcclerator("Accelerator 1", hardwareMap);
        column = new Column("Column 1","Column 2", hardwareMap);
        tuskGate = new TuskGate("Tusk Gate", hardwareMap);
        capballHolder = new CapballHolder("Capball Holder", hardwareMap);
        baconActivator = new BaconActivator("Bacon Activator", hardwareMap);
        colorSensor = hardwareMap.colorSensor.get("Beacon Color");
        //rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "Range 1");
        left_ods = hardwareMap.opticalDistanceSensor.get("Left ods");
        right_ods = hardwareMap.opticalDistanceSensor.get("Right ods");

        //The gyro sensor is constructed inside of the autoDriver.
        gyroAutoDriver = new GyroAutoDriver(this, "gyroSensor", driveTrain);
        encoderAutoDriver = new EncoderAutoDriver(this, driveTrain);
        rangeAutoDriver = new RangeAutoDriver(this, driveTrain, "Range 1", "Range 2");

        //Initialize Robot Components
        accelerator1.accleratorPower = 0;
        baconActivator.armDown();
        telemetry.update();
        colorSensor.enableLed(false);
        troughGate.closeGate();

        gyroAutoDriver.calibrate();
        driveTrain.resetMotorEncoders();
        waitForStart();

        gyroAutoDriver.driveStraightForwards(5600, 1);
        sleep(200);
        driveTrain.resetMotorEncoders();

        while (left_ods.getLightDetected() < 0.06 && opModeIsActive()) {
            driveTrain.setPowers(0.2, 0.2);
        }
        driveTrain.haltDrive();
        sleep(200);
        driveTrain.resetMotorEncoders();

        gyroAutoDriver.driveStraightForwards(200, 0.5);
        driveTrain.haltDrive();
        sleep(200)  ;
        driveTrain.resetMotorEncoders();

        gyroAutoDriver.turn(-70);

        baconActivator.sensorScanning();
        sleep(200);

        while (rangeSensor.rawUltrasonic() > 25 && opModeIsActive()) {
            driveTrain.setPowers(0.3, 0.3);
        }
        driveTrain.haltDrive();
        driveTrain.resetMotorEncoders();
        sleep(200);

        gyroAutoDriver.turn(-90);

        while(opModeIsActive())
        {
            telemetry.addData("Side Ultrasonic", rangeAutoDriver.getSideUltrasonicRange());
            telemetry.addData("Front Ultrasonic", rangeAutoDriver.getFrontUltrasonicRange());
        }
    }
}

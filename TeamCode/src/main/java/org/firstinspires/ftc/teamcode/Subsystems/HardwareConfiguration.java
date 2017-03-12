package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.teamcode.FTCRC_Extensions.OpticalDistanceAutoDriver;
import org.firstinspires.ftc.teamcode.FTCRC_Extensions.RangeAutoDriver;
import org.firstinspires.ftc.teamcode.FTCRC_Extensions.RangeSensor;
import org.firstinspires.ftc.teamcode.FTCRC_Extensions.DriveTrain;
import org.firstinspires.ftc.teamcode.FTCRC_Extensions.GyroAutoDriver;
import org.firstinspires.ftc.teamcode.FTCRC_Extensions.TargetPositionAutoDriver;

public class HardwareConfiguration
{
    public LinearOpMode opMode;

    //Robot Components
    public DriveTrain driveTrain;
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
    public RangeSensor sideRangeSensor;
    public RangeSensor frontRangeSensor;

    //Auto Drivers
    public GyroAutoDriver gyroAutoDriver;
    public TargetPositionAutoDriver targetPositionAutoDriver;
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

        driveTrain = new DriveTrain.Builder()
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
        frontRangeSensor = new RangeSensor("Range 1", 0x28, opMode.hardwareMap);
        sideRangeSensor = new RangeSensor("Range 2", 0x2a, opMode.hardwareMap);

        //Define auto drivers
        gyroAutoDriver = new GyroAutoDriver(this);
        targetPositionAutoDriver = new TargetPositionAutoDriver(opMode, this.driveTrain);
        rangeAutoDriver = new RangeAutoDriver(this);
        opticalDistanceAutoDriver = new OpticalDistanceAutoDriver(this);
    }

    void init() {
        //Initialize Robot Components
        accelerator.acceleratorPower = 0;
        baconActivator.armDown();
        colorSensor.enableLed(false);
        troughGate.closeGate();
        driveTrain.resetMotorEncoders();
        gyroSensor.calibrate();
        while(gyroSensor.isCalibrating()) {
            //Wait for calibration
        }
    }
}


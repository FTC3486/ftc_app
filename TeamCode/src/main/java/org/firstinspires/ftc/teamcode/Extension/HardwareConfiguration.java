package org.firstinspires.ftc.teamcode.Extension;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.teamcode.Subsystems.BaconActivator;
import org.firstinspires.ftc.teamcode.Subsystems.CapballHolder;
import org.firstinspires.ftc.teamcode.Subsystems.Column;
import org.firstinspires.ftc.teamcode.Subsystems.ParticleAcclerator;
import org.firstinspires.ftc.teamcode.Subsystems.Pickup;
import org.firstinspires.ftc.teamcode.Subsystems.TroughGate;
import org.firstinspires.ftc.teamcode.Subsystems.TuskGate;

public class HardwareConfiguration
{
    //Robot Components
    public Drivetrain drivetrain;
    public ParticleAcclerator accelerator1;
    public Pickup pickup;
    public TroughGate troughGate;
    public Column column;
    public TuskGate tuskGate;
    public CapballHolder capballHolder;
    public BaconActivator baconActivator;

    //Sensors
    public ColorSensor colorSensor;
    public OpticalDistanceSensor left_ods;
    public OpticalDistanceSensor right_ods;

    //Auto Drivers
    public GyroAutoDriver gyroAutoDriver;
    public EncoderAutoDriver encoderAutoDriver;
    public RangeAutoDriver rangeAutoDriver;

    public HardwareConfiguration(LinearOpMode opMode){
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
        accelerator1 = new ParticleAcclerator("Accelerator 1", opMode.hardwareMap);
        column = new Column("Column 1", "Column 2", opMode.hardwareMap);
        tuskGate = new TuskGate("Tusk Gate", opMode.hardwareMap);
        capballHolder = new CapballHolder("Capball Holder", opMode.hardwareMap);
        baconActivator = new BaconActivator("Bacon Activator", opMode.hardwareMap);

        //Define sensors
        colorSensor = opMode.hardwareMap.colorSensor.get("Beacon Color");
        left_ods = opMode.hardwareMap.opticalDistanceSensor.get("Left ods");
        right_ods = opMode.hardwareMap.opticalDistanceSensor.get("Right ods");

        //Define auto drivers
        gyroAutoDriver = new GyroAutoDriver(opMode, "gyroSensor", this.drivetrain);
        encoderAutoDriver = new EncoderAutoDriver(opMode, this.drivetrain);
        rangeAutoDriver = new RangeAutoDriver(opMode, this.drivetrain, "Range 1", "Range 2");
    }

    public void init() {
        //Initialize Robot Components
        accelerator1.accleratorPower = 0;
        baconActivator.armDown();
        colorSensor.enableLed(false);
        troughGate.closeGate();
        drivetrain.resetMotorEncoders();
        gyroAutoDriver.calibrate();
    }
}


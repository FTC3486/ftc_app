package org.firstinspires.ftc.teamcode.RobotConfiguration.RoverRuckus;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Drivable;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Drivetrain;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Initializable;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.SpeedServo;
import org.firstinspires.ftc.teamcode.Subsystems.Latch;
import org.firstinspires.ftc.teamcode.Subsystems.OpenCloseServo;
import org.firstinspires.ftc.teamcode.Subsystems.ReversableMotor;


public class RoverRuckusRobot implements Drivable, Initializable {
    // Components
    private final Drivetrain drivetrain;
    public Latch latch;
    //Arm motor for extending/retracting
    public final ReversableMotor arm;

    //Flapper motor for collecting elements
    public final ReversableMotor flapperMotor;
    //Flapper servo for tilting
    public final SpeedServo flapperServo;
    //Team marker servo for dumping
    public final OpenCloseServo markerServo;

    // Sensors
    //private RangeSensor leftRangeSensor;
    //private RangeSensor rightRangeSensor;
    public final ColorSensor colorSensor;

    public RoverRuckusRobot(HardwareMap hardwareMap) {
        // Drivetrain
        final DcMotor left1 = hardwareMap.dcMotor.get("left1");
        final DcMotor left2 = hardwareMap.dcMotor.get("left2");
        final DcMotor right1 = hardwareMap.dcMotor.get("right1");
        final DcMotor right2 = hardwareMap.dcMotor.get("right2");
        left1.setDirection(DcMotor.Direction.FORWARD);
        left2.setDirection(DcMotor.Direction.FORWARD);
        right1.setDirection(DcMotor.Direction.REVERSE);
        right2.setDirection(DcMotor.Direction.REVERSE);
        this.drivetrain = new Drivetrain.Builder()
                .addLeftMotor(left1)
                .addLeftMotorWithEncoder(left2)
                .addRightMotor(right1)
                .addRightMotorWithEncoder(right2)
                .build();

        // Latch

        final DcMotor latchMotor = hardwareMap.dcMotor.get("latch");
        final DcMotor latchMotor2 = hardwareMap.dcMotor.get("latch2");
        final DigitalChannel latchTop = hardwareMap.digitalChannel.get("latchTop");
        final DigitalChannel latchBottom = hardwareMap.digitalChannel.get("latchBottom");
        this.latch = new Latch(latchMotor,latchMotor2, latchTop, latchBottom,-1.00, 1.00);


        //Arm
        final DcMotor armMotor = hardwareMap.dcMotor.get("arm");

        this.arm = new ReversableMotor(armMotor, 1);

        //Flapper motor
        final DcMotor flapperMotor = hardwareMap.dcMotor.get("flapper");
        this.flapperMotor = new ReversableMotor(flapperMotor, 1);
        //Flapper servo
        final Servo flapperServo = hardwareMap.servo.get("flapperServo");
        this.flapperServo = new SpeedServo(flapperServo, 1.0, 0.1);
        //Team marker servo
        final Servo markerServo = hardwareMap.servo.get("markerServo");
        this.markerServo = new OpenCloseServo(markerServo, 1, 0, .5 );

        //Color sensor
        this.colorSensor = hardwareMap.colorSensor.get("colorSensor");
    }

    @Override
    public void initialize() {
        //latch.initialize();
        flapperServo.initialize();
        //markerServo.initialize();
    }

    @Override
    public Drivetrain getDrivetrain() {
        return this.drivetrain;
    }
}


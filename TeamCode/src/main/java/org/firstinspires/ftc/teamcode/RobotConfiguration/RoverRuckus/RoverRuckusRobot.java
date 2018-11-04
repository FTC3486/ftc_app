package org.firstinspires.ftc.teamcode.RobotConfiguration.RoverRuckus;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Drivable;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Drivetrain;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Initializable;
import org.firstinspires.ftc.teamcode.Subsystems.Latch;
import org.firstinspires.ftc.teamcode.Subsystems.OpenCloseServo;
import org.firstinspires.ftc.teamcode.Subsystems.ReversableMotor;

public class RoverRuckusRobot implements Drivable, Initializable {
    // Components
    private final Drivetrain drivetrain;
    public final Latch latch;
    //Arm motor for extending/retracting
    public final ReversableMotor reversableMotor;
    double armPower = 0;

    //Flapper motor for collecting elements
    public final ReversableMotor flapperMotor;
    //Flapper servo for tilting
    public final OpenCloseServo flapperServo;

    // Sensors
    //private RangeSensor leftRangeSensor;
    //private RangeSensor rightRangeSensor;
    //public ColorSensor jewelColorSensor;
    private final DigitalChannel latchTop;
    private final DigitalChannel latchBottom;

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
        latchTop = hardwareMap.digitalChannel.get("latchTop");
        latchBottom = hardwareMap.digitalChannel.get("latchBottom");
        this.latch = new Latch(latchMotor, latchTop, latchBottom, 1.00, -1.00);

        //Arm
        final DcMotor armMotor = hardwareMap.dcMotor.get("arm");
        this.reversableMotor = new ReversableMotor(armMotor, 1);

        //Flapper motor
        final DcMotor flapperMotor = hardwareMap.dcMotor.get("flapper");
        this.flapperMotor = new ReversableMotor(flapperMotor, 1);
        //Flapper servo
        final Servo flapperServo = hardwareMap.servo.get("flapperServo");
        this.flapperServo = new OpenCloseServo(flapperServo, 0, .5, .99);
    }

    @Override
    public void initialize() {
        latch.initialize();
    }

    @Override
    public Drivetrain getDrivetrain() {
        return drivetrain;
    }
}

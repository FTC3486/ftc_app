package org.firstinspires.ftc.teamcode.RobotConfiguration.RoverRuckus;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Drivable;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Latch;

        public class RoverRuckusRobot implements  Drivable {// Initializable,,RangeAutoDriver.Drivable {
            // Components
            private final Drivetrain drivetrain;
            //public Arm jewelArm;
            // public final OpenCloseServo test180;
            //public final OpenCloseServo openClose;
            //public final ReversableMotor will;
            public Latch latch;

    // Sensors
    //private RangeSensor leftRangeSensor;
    //private RangeSensor rightRangeSensor;
    //public ColorSensor jewelColorSensor;
    //public SensorDigitalTouch latchTop;
    //public SensorDigitalTouch latchBottom;

    public RoverRuckusRobot(HardwareMap hardwareMap) {
        DcMotor left1 = hardwareMap.dcMotor.get("left1");
        DcMotor left2 = hardwareMap.dcMotor.get("left2");
        DcMotor right1 = hardwareMap.dcMotor.get("right1");
        DcMotor right2 = hardwareMap.dcMotor.get("right2");
        left1.setDirection(DcMotor.Direction.FORWARD);
        left2.setDirection(DcMotor.Direction.FORWARD);
        right1.setDirection(DcMotor.Direction.REVERSE);
        right2.setDirection(DcMotor.Direction.REVERSE);
        DcMotor latch = hardwareMap.dcMotor.get("latch");
        //DcMotor will = hardwareMap.dcMotor.get("will");

        drivetrain = new Drivetrain.Builder()
                .addLeftMotor(left1)
                .addLeftMotorWithEncoder(left2)
                .addRightMotor(right1)
                .addRightMotorWithEncoder(right2)
                .build();

        //jewelArm = new Arm(hardwareMap.servo.get("JewelArm"), 0.24, 0.43, 0.84);
        // test180 = new OpenCloseServo(hardwareMap.servo.get("test180"),
         //        0.5, 0.6, 0.7);
        //openClose = new OpenCloseServo(hardwareMap.servo.get("openClose"), 0.1, 0.3, 0.8);
        //will = new ReversableMotor(hardwareMap.dcMotor.get("will"),0.0);


    }

    @Override
    public Drivetrain getDrivetrain() {
        return drivetrain;
    }

    //@Override
    //public SensorDigitalTouch getTouch1() { return touch1; }


    //@Override
    //public RangeSensor getLeftRangeSensor() {
     //   return leftRangeSensor;
    //}

    //@Override
   // public RangeSensor getRightRangeSensor() {
    //    return rightRangeSensor;
   // }

    //@Override
    //public void initialize() {
    //    jewelArm.initialize();
   // }
}


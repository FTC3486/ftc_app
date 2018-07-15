package org.firstinspires.ftc.teamcode.RobotConfiguration.RelicRecovery;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Drivetrain;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Drivable;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Initializable;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.RangeAutoDriver;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.RangeSensor;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;

public class RelicRecoveryRobot implements Initializable, Drivable, RangeAutoDriver.Drivable {
    // Components
    private final Drivetrain drivetrain;
    public Arm jewelArm;

    // Sensors
    private RangeSensor leftRangeSensor;
    private RangeSensor rightRangeSensor;
    public ColorSensor jewelColorSensor;

    public RelicRecoveryRobot(HardwareMap hardwareMap) {
        DcMotor left1 = hardwareMap.dcMotor.get("left1");
        DcMotor left2 = hardwareMap.dcMotor.get("left2");
        DcMotor right1 = hardwareMap.dcMotor.get("right1");
        DcMotor right2 = hardwareMap.dcMotor.get("right2");
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

        jewelArm = new Arm(hardwareMap.servo.get("JewelArm"), 0.24, 0.43, 0.84);
    }

    @Override
    public Drivetrain getDrivetrain() {
        return drivetrain;
    }

    @Override
    public RangeSensor getLeftRangeSensor() {
        return leftRangeSensor;
    }

    @Override
    public RangeSensor getRightRangeSensor() {
        return rightRangeSensor;
    }

    @Override
    public void initialize() {
        jewelArm.initialize();
    }
}


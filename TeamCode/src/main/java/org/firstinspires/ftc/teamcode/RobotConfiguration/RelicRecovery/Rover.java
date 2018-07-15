package org.firstinspires.ftc.teamcode.RobotConfiguration.RelicRecovery;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Drivetrain;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.HardwareConfiguration;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.RangeSensor;
import org.firstinspires.ftc.teamcode.Subsystems.JewelArm;

public class Rover extends HardwareConfiguration {
    OpMode opMode;

    //Rover Components
    public JewelArm jewelArm;

    //Sensors
    RangeSensor leftRangeSensor;
    RangeSensor rightRangeSensor;

    public Rover(OpMode opMode) {
        super(opMode);
        //Define rover components
        DcMotor left1 = opMode.hardwareMap.dcMotor.get("left1");
        DcMotor left2 = opMode.hardwareMap.dcMotor.get("left2");
        DcMotor right1 = opMode.hardwareMap.dcMotor.get("right1");
        DcMotor right2 = opMode.hardwareMap.dcMotor.get("right2");
        left1.setDirection(DcMotor.Direction.REVERSE);
        left2.setDirection(DcMotor.Direction.REVERSE);
        right1.setDirection(DcMotor.Direction.FORWARD);
        right2.setDirection(DcMotor.Direction.FORWARD);

        this.drivetrain = new Drivetrain.Builder()
                .addLeftMotor(left1)
                .addLeftMotorWithEncoder(left2)
                .addRightMotor(right1)
                .addRightMotorWithEncoder(right2)
                .build();

        jewelArm = new JewelArm("JewelArm", "JewelColor", opMode.hardwareMap);
    }

    @Override
    public void initialize(){
        jewelArm.autoInit();
    }
}


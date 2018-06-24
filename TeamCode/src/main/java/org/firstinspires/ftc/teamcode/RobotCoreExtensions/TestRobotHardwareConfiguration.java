package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class TestRobotHardwareConfiguration
{
    OpMode opMode;
    //Robot Components
    public Drivetrain drivetrain;
    public DcMotor spinner;

    //Sensors

    //Auto Drivers

    TestRobotHardwareConfiguration(OpMode opMode)
    {
        this.opMode = opMode;
        //Define robot components
        DcMotor Left = opMode.hardwareMap.dcMotor.get("left");
        DcMotor Right = opMode.hardwareMap.dcMotor.get("right");
        Left.setDirection(DcMotor.Direction.REVERSE);
        Right.setDirection(DcMotor.Direction.FORWARD);


        drivetrain = new Drivetrain.Builder()
                .addLeftMotorWithEncoder(Left)
                .addRightMotorWithEncoder(Right)
                .build();

        spinner = opMode.hardwareMap.dcMotor.get("spinner");
        //Define sensors

        //Define auto drivers

    }

    void init()
    {
        spinner.setPower(0);

    }
}


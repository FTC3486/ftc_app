package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class TWARobotHardwareConfiguration
{
    OpMode opMode;
    //Robot Components
    public Drivetrain drivetrain;

    //Sensors

    //Auto Drivers

    TWARobotHardwareConfiguration(OpMode opMode)
    {
        this.opMode = opMode;
        //Define robot components
        DcMotor left1 = opMode.hardwareMap.dcMotor.get("left1");
        DcMotor left2 = opMode.hardwareMap.dcMotor.get("left2");
        DcMotor right1 = opMode.hardwareMap.dcMotor.get("right1");
        DcMotor right2 = opMode.hardwareMap.dcMotor.get("right2");
        left1.setDirection(DcMotor.Direction.REVERSE);
        left2.setDirection(DcMotor.Direction.REVERSE);
        right1.setDirection(DcMotor.Direction.FORWARD);
        right2.setDirection(DcMotor.Direction.FORWARD);


        drivetrain = new Drivetrain.Builder()
                .addLeftMotorWithEncoder(left1)
                .addLeftMotorWithEncoder(left2)
                .addRightMotorWithEncoder(right1)
                .addRightMotorWithEncoder(right2)
                .build();

        //Define sensors

        //Define auto drivers

    }

    void init()
    {
        // :) oh happy day, there is nothing here. :)
    }
}


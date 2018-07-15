package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.EncoderAutoDriver;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.RangeAutoDriver;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Rover;

/*
    Filename: RoverTestAuto.java

    Description:
        Test Autonomous program using encoders to drive Rover.

    Use:
        Test program to test encoderAutoDriver, JewelArm and RangeAutoDriver.  Test Stall monitor.

    Requirements:
 *     - AutoDrive configured for stall monitor
 *     - Drive motors with encoders
 *     - Two Range sensors
 *     - one color sensor
 *     -Jewel arm
 * *
 * Changelog:
 *     -Created by 3486 on 7/5/18.
 *     -

 */

@Autonomous (name = "RoverTestAuto", group = "BlueAuto")
public class RoverTestAuto extends LinearOpMode {
    Rover rover = new Rover(this);
    EncoderAutoDriver encoderAutoDriver = new EncoderAutoDriver(rover, this);
    //RangeAutoDriver rangeAutoDriver = new RangeAutoDriver(rover, this);

    @Override
    public void runOpMode() throws InterruptedException {
        rover.init();
        rover.hw.jewelArm.autoInit();

        waitForStart();
        rover.hw.drivetrain.resetMotorEncoders();
        encoderAutoDriver.driveToDistanceForwards(1000);
        encoderAutoDriver.driveLeftSideToDistance(-9);
        encoderAutoDriver.driveRightSideToDistance(9);
        encoderAutoDriver.spinLeft(9,9);
        //rangeAutoDriver.

    }
}
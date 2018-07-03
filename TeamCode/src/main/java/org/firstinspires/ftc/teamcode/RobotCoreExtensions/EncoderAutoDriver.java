package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Filename: EncoderAutoDriver.java
 *
 * Description:
 *     This class contains the methods that use the encoders for predefined autonomous movements.
 *
 * Methods:
 *      driveLeftSideToDistance - Drives the left side a specified distance using motor encoders
 *      driveRightSideToDistance - Drives the right side a specified distance using motor encoders
 *      driveToDistance - Drives both sides a specified distance using motor encoders
 *
 * Example: robot.hardwareConfiguration.encoderAutoDriver.driveLeftSideToDistance(double distance)
 * Distances in inches.
 *
 * Requirements:
 *     - Drive motors with encoders
 *     - An encoder auto driver is created in a hardware configuration and accessed
 *       in an autonomous program for use.
 *
 * Changelog:
 *     -Edited and tested by Team 3486 on 7/8/2017.
 *     -Edited file description and documentation 7/22/17
 */

public class EncoderAutoDriver extends AutoDriver
{
    public EncoderAutoDriver(Robot robot, LinearOpMode opMode)
    {
        super(robot, opMode);
    }

    public void driveLeftSideToDistance(double distance)
    {
        setupMotion("Driving to set distance.");
        robot.hw.drivetrain.setPowers(0.3, 0.0);

        // Drives the left side converting our inches input to counts while the OpMode is active

        while(robot.hw.drivetrain.getLeftEncoderCount() < robot.hw.drivetrain.convertInchesToEncoderCounts(distance)
                && opMode.opModeIsActive())
        {}
        robot.hw.drivetrain.haltDrive();
        endMotion();
    }

    public void driveRightSideToDistance(double distance)
    {
        setupMotion("Driving to set distance.");
        robot.hw.drivetrain.setPowers(0.0, 0.3);

        // Drives the Right side converting our inches input to counts while the OpMode is active

        while(robot.hw.drivetrain.getRightEncoderCount() < robot.hw.drivetrain.convertInchesToEncoderCounts(distance)
                && opMode.opModeIsActive()) {}
        robot.hw.drivetrain.haltDrive();
        endMotion();
    }

    public void driveToDistanceForwards(double distance)
    {
        setupMotion("Driving to set distance.");

        // Drives the both sides converting our inches input to counts while the OpMode is active

        while(robot.hw.drivetrain.getLeftEncoderCount() < robot.hw.drivetrain.convertInchesToEncoderCounts(distance)
                && robot.hw.drivetrain.getRightEncoderCount() < robot.hw.drivetrain.convertInchesToEncoderCounts(distance)
                && opMode.opModeIsActive()
                ){
            robot.hw.drivetrain.setPowers(0.3, 0.3);
            opMode.telemetry.addData("Right Encoder", robot.hw.drivetrain.getRightEncoderCount());
            opMode.telemetry.addData("Left Encoder", robot.hw.drivetrain.getLeftEncoderCount());
            opMode.telemetry.update();
        }
        endMotion();
    }

    public void driveToDistanceBackwards(double distance)
    {
        setupMotion("Driving to set distance.");
        robot.hw.drivetrain.setPowers(-0.3, -0.3);

        // Drives the both sides converting our inches input to counts while the OpMode is active

        while(robot.hw.drivetrain.getLeftEncoderCount() > robot.hw.drivetrain.convertInchesToEncoderCounts(distance)
                && robot.hw.drivetrain.getRightEncoderCount() > robot.hw.drivetrain.convertInchesToEncoderCounts(distance)
                && opMode.opModeIsActive()) {}
        endMotion();
    }

    public void spinRight(double leftInches, double rightInches){
     setupMotion("Spinning set amount");
        robot.hw.drivetrain.setPowers(0.3, -0.3);

        while (robot.hw.drivetrain.getLeftEncoderCount() < robot.hw.drivetrain.convertInchesToEncoderCounts(leftInches)
                && robot.hw.drivetrain.getRightEncoderCount() > robot.hw.drivetrain.convertInchesToEncoderCounts(rightInches) );{}
        endMotion();
    }


    public void spinLeft(double leftInches, double rightInches){
        setupMotion("Spinning set amount");
        robot.hw.drivetrain.setPowers(-0.3, 0.3);

        while (robot.hw.drivetrain.getLeftEncoderCount() > robot.hw.drivetrain.convertInchesToEncoderCounts(leftInches)
                && robot.hw.drivetrain.getRightEncoderCount()< robot.hw.drivetrain.convertInchesToEncoderCounts(rightInches) );{}
        endMotion();
    }
}



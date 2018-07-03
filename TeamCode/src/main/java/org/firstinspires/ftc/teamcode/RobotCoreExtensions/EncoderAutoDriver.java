package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

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

public class EncoderAutoDriver2017 extends TWAAutoDriver
{
    public EncoderAutoDriver2017(AutoTWAHardwareConfiguration hw)
    {
        super(hw);
    }

    public void driveLeftSideToDistance(double distance)
    {
        setupMotion("Driving to set distance.");
        
        hw.drivetrain.setPowers(0.3, 0.0);

        // Drives the left side converting our inches input to counts while the OpMode is active

        while(hw.drivetrain.getLeftEncoderCount() < hw.drivetrain.convertInchesToEncoderCounts(distance)
                /*&& opMode.opModeIsActive()*/)
        {}
        hw.drivetrain.haltDrive();

        endMotion();
    }

    public void driveRightSideToDistance(double distance)
    {
        setupMotion("Driving to set distance.");

        hw.drivetrain.setPowers(0.0, 0.3);

        // Drives the Right side converting our inches input to counts while the OpMode is active

        while(hw.drivetrain.getRightEncoderCount() < hw.drivetrain.convertInchesToEncoderCounts(distance)
                /*&& hw.opMode.opModeIsActive()*/)
        {}
        hw.drivetrain.haltDrive();

        endMotion();
    }

    public void driveToDistanceForwards(double distance)
    {
        setupMotion("Driving to set distance.");



        // Drives the both sides converting our inches input to counts while the OpMode is active

        while(hw.drivetrain.getLeftEncoderCount() < hw.drivetrain.convertInchesToEncoderCounts(distance)
                && hw.drivetrain.getRightEncoderCount() < hw.drivetrain.convertInchesToEncoderCounts(distance)
                && hw.opMode.opModeIsActive()
                ){
            hw.drivetrain.setPowers(0.3, 0.3);
            hw.opMode.telemetry.addData("Right Encoder", hw.drivetrain.getRightEncoderCount());
            hw.opMode.telemetry.addData("Left Encoder", hw.drivetrain.getLeftEncoderCount());
            hw.opMode.telemetry.update();

        }

        endMotion();



    }

    public void driveToDistanceBackwards(double distance)
    {
        setupMotion("Driving to set distance.");

        hw.drivetrain.setPowers(-0.3, -0.3);

        // Drives the both sides converting our inches input to counts while the OpMode is active

        while(hw.drivetrain.getLeftEncoderCount() > hw.drivetrain.convertInchesToEncoderCounts(distance)
                && hw.drivetrain.getRightEncoderCount() > hw.drivetrain.convertInchesToEncoderCounts(distance)
                /*&& hw.opMode.opModeIsActive()*/) {}

        endMotion();
    }

    public void spinRight(double leftInches, double rightInches){
     setupMotion("Spinning set amount");
         hw.drivetrain.setPowers(0.3, -0.3);

        while (hw.drivetrain.getLeftEncoderCount() < hw.drivetrain.convertInchesToEncoderCounts(leftInches) && hw.drivetrain.getRightEncoderCount() > hw.drivetrain.convertInchesToEncoderCounts(rightInches) );{}

        endMotion();
    }


    public void spinLeft(double leftInches, double rightInches){
        setupMotion("Spinning set amount");
        hw.drivetrain.setPowers(-0.3, 0.3);

        while (hw.drivetrain.getLeftEncoderCount() > hw.drivetrain.convertInchesToEncoderCounts(leftInches) && hw.drivetrain.getRightEncoderCount()< hw.drivetrain.convertInchesToEncoderCounts(rightInches) );{}

        endMotion();
    }
}



package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Filename: EncoderAutoDriver.java
 * <p>
 * Description:
 * This class contains the methods that use the encoders for predefined autonomous movements.
 * <p>
 * Methods:
 * driveLeftSideToDistance - Drives the left side a specified distance using motor encoders
 * driveRightSideToDistance - Drives the right side a specified distance using motor encoders
 * driveToDistance - Drives both sides a specified distance using motor encoders
 * <p>
 * Example: hw.hardwareConfiguration.encoderAutoDriver.driveLeftSideToDistance(double distance)
 * Distances in inches.
 * <p>
 * Requirements:
 * - Drive motors with encoders
 * - An encoder auto driver is created in a hardware configuration and accessed
 * in an autonomous program for use.
 * <p>
 * Changelog:
 * -Edited and tested by Team 3486 on 7/8/2017.
 * -Edited file description and documentation 7/22/17
 */

public class EncoderAutoDriver extends AutoDriver {
    private final Drivetrain drivetrain;

    public EncoderAutoDriver(Drivable hw, LinearOpMode opMode) {
        super(hw, opMode);
        drivetrain = hw.getDrivetrain();
    }

    public void driveLeftSideToDistance(double distance) {
        setupMotion("Driving to set distance.");
        hw.getDrivetrain().setPowers(power, 0.0);

        // Drives the left side converting our inches input to counts while the OpMode is active

        while (drivetrain.getLeftEncoderCount() < drivetrain.convertInchesToEncoderCounts(distance)
                && opMode.opModeIsActive()) {
        }
        drivetrain.haltDrive();
        endMotion();
    }

    public void driveRightSideToDistance(double distance) {
        setupMotion("Driving to set distance.");
        drivetrain.setPowers(0.0, power);

        // Drives the Right side converting our inches input to counts while the OpMode is active

        while (drivetrain.getRightEncoderCount() < drivetrain.convertInchesToEncoderCounts(distance)
                && opMode.opModeIsActive()) {
        }
        endMotion();
    }

    public void driveToDistanceForwards(double distance) {
        setupMotion("Driving to set distance.");
        drivetrain.setPowers(power, power);

        // Drives the both sides converting our inches input to counts while the OpMode is active

        while (drivetrain.getLeftEncoderCount() < drivetrain.convertInchesToEncoderCounts(distance)
                && drivetrain.getRightEncoderCount() < drivetrain.convertInchesToEncoderCounts(distance)
                && opMode.opModeIsActive()
                ) {
        }
        endMotion();
    }

    public void driveToDistanceBackwards(double distance) {
        setupMotion("Driving to set distance.");
        drivetrain.setPowers(-power, -power);

        // Drives the both sides converting our inches input to counts while the OpMode is active

        while (drivetrain.getLeftEncoderCount() > drivetrain.convertInchesToEncoderCounts(distance)
                && drivetrain.getRightEncoderCount() > drivetrain.convertInchesToEncoderCounts(distance)
                && opMode.opModeIsActive()) {
        }
        endMotion();
    }

    public void spinRight(double leftInches, double rightInches) {
        setupMotion("Spinning set amount");
        drivetrain.setPowers(power, -power);

        while (drivetrain.getLeftEncoderCount() < drivetrain.convertInchesToEncoderCounts(leftInches)
                && drivetrain.getRightEncoderCount() > drivetrain.convertInchesToEncoderCounts(rightInches)) {
        }
        endMotion();
    }


    public void spinLeft(double leftInches, double rightInches) {
        setupMotion("Spinning set amount");
        drivetrain.setPowers(-power, power);

        while (drivetrain.getLeftEncoderCount() > drivetrain.convertInchesToEncoderCounts(leftInches)
                && drivetrain.getRightEncoderCount() < drivetrain.convertInchesToEncoderCounts(rightInches)) {
        }
        endMotion();
    }
}



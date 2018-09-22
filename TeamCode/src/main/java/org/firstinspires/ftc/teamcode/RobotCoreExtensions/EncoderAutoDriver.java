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
 * driveToDistance - Drives both sides a specified distance forward or backwords using motor encoders
 * spinLeft - Drives the left and right side in opposite directions using motor encoders
 * spinRight - Drives the left and right side in opposite directions using motor encoders
 *
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
 * -Combined driveToDistanceForward with driveToDistanceBackwards into common program that moves both directions 7/26/18
 * -Impoved driveRightSideToDistance and Left to move forward or backwards 7/26/18
 *  - added && opMode.opModeIsActive() to each method 7/26/18
 * Updated documentation and tested each methods. 7/26/18
 */

public class EncoderAutoDriver extends AutoDriver {
    private final Drivetrain drivetrain;

    public EncoderAutoDriver(Drivable hw, LinearOpMode opMode) {
        super(hw, opMode);
        drivetrain = hw.getDrivetrain();
    }

    // Function - driveLeftSideToDistance
    // Drives the left side forwards of backwards converting our inches input to counts while the OpMode is active
    // using the power variable for speed control.
    // Distance in inches to one decimal place.
    // Input – number, positive to move forward or negative to go backwards.   Example (4) 4 inches forward,
    // (-8) eight inches backwards.

    public void driveLeftSideToDistance(double distance) {
        setupMotion("Driving to set distance.");

       if (distance > 0) {
           hw.getDrivetrain().setPowers(power, 0.0);

           while (drivetrain.getLeftEncoderCount() < drivetrain.convertInchesToEncoderCounts(distance)
                   && opMode.opModeIsActive()) {
               opMode.telemetry.addData("LeftEncoderCount: ", drivetrain.getLeftEncoderCount());
               opMode.telemetry.addData("RightEncoderCount: ", drivetrain.getRightEncoderCount());
               opMode.telemetry.update();
           }
       }else {
           hw.getDrivetrain().setPowers(-power, 0.0);

           while (drivetrain.getLeftEncoderCount() > drivetrain.convertInchesToEncoderCounts(distance)
                       && opMode.opModeIsActive()) {
               opMode.telemetry.addData("LeftEncoderCount: ", drivetrain.getLeftEncoderCount());
               opMode.telemetry.addData("RightEncoderCount: ", drivetrain.getRightEncoderCount());
               opMode.telemetry.update();
               }
           }
            endMotion();
    }

    // Function - driveRightSideToDistance
    // Drives the right side forwards of backwards converting our inches input to counts while the OpMode is active
    // using the power variable for speed control.
    // Distance in inches to one decimal place.
    // Input – number, positive to move forward or negative to go backwards.   Example (4) 4 inches forward,
    // (-8) eight inches backwards.

    public void driveRightSideToDistance(double distance) {
        setupMotion("Driving to set distance.");

        if (distance > 0) {

            drivetrain.setPowers(0.0, power);

            while (drivetrain.getRightEncoderCount() < drivetrain.convertInchesToEncoderCounts(distance)
                    && opMode.opModeIsActive()) {
                opMode.telemetry.addData("LeftEncoderCount: ", drivetrain.getLeftEncoderCount());
                opMode.telemetry.addData("RightEncoderCount: ", drivetrain.getRightEncoderCount());
                opMode.telemetry.update();
            }

        }else {

            drivetrain.setPowers(0.0, -power);

            while (drivetrain.getRightEncoderCount() > drivetrain.convertInchesToEncoderCounts(distance)
                    && opMode.opModeIsActive()) {
                opMode.telemetry.addData("LeftEncoderCount: ", drivetrain.getLeftEncoderCount());
                opMode.telemetry.addData("RightEncoderCount: ", drivetrain.getRightEncoderCount());
                opMode.telemetry.update();
            }
        }
        endMotion();
    }


    // Function - driveToDistance
    // Drives both sides straight forwards or backwards converting our inches input to counts while the OpMode is active
    // using the power variable for speed control.
    // Distance in inches to one decimal place.
    // Input – number, positive to move forward or negative to go backwards.   Example (4) 4 inches forward,
    // (-8) eight inches backwards.


    public void driveToDistance(double distance) {
        setupMotion("Driving to set distance.");

        if (distance > 0) {
            drivetrain.setPowers(power, power);
            while (drivetrain.getLeftEncoderCount() < drivetrain.convertInchesToEncoderCounts(distance)
                    //&& drivetrain.getRightEncoderCount() < drivetrain.convertInchesToEncoderCounts(distance)
                    && opMode.opModeIsActive()) {
                opMode.telemetry.addData("LeftEncoderCount: ", drivetrain.getLeftEncoderCount());
                opMode.telemetry.addData("RightEncoderCount: ", drivetrain.getRightEncoderCount());
                opMode.telemetry.update();
            }
        } else {
            drivetrain.setPowers(-power, -power);
            while (drivetrain.getLeftEncoderCount() > drivetrain.convertInchesToEncoderCounts(distance)
                    //&& drivetrain.getRightEncoderCount() > drivetrain.convertInchesToEncoderCounts(distance)
                    && opMode.opModeIsActive()) {
                opMode.telemetry.addData("LeftEncoderCount: ", drivetrain.getLeftEncoderCount());
                opMode.telemetry.addData("RightEncoderCount: ", drivetrain.getRightEncoderCount());
                opMode.telemetry.update();
            }
        }
        endMotion();
    }


        // Function - spinRight
    // Spins the robot right using both left and right motors converting our inches input to counts
    // while the OpMode is active using the power variable for speed control.
    // Distance in inches to one decimal place.
    // Input (?,?) – The first number is the left motor distance and the second is the right motor distance.
    // First number is positive to move the left side forward. the second number is negative to drive the right side backwards.
    // Standard turns 90 degrees (10,-10),  180 turn (22,-22)
    // Distances will need adjusted based on power and weight of the robot.

    public void spinRight(double leftInches, double rightInches) {
        setupMotion("Spinning set amount");
        drivetrain.setPowers(power, -power);

        while (drivetrain.getLeftEncoderCount() < drivetrain.convertInchesToEncoderCounts(leftInches)
                && drivetrain.getRightEncoderCount() > drivetrain.convertInchesToEncoderCounts(rightInches)
                && opMode.opModeIsActive()){
            opMode.telemetry.addData("LeftEncoderCount: ", drivetrain.getLeftEncoderCount());
            opMode.telemetry.addData("RightEncoderCount: ", drivetrain.getRightEncoderCount());
            opMode.telemetry.update();
        }
        endMotion();
    }

    // Function - spinLeft
    // Spins the robot left using both left and right motors converting our inches input to counts
    // while the OpMode is active using the power variable for speed control.
    // Distance in inches to one decimal place.
    // Input (?,?) – The first number is the left motor distance and the second is the right motor distance.
    // First number is negative to move the left side backwards. the second number is positive to drive the left side forward.
    // Standard turns 90 degrees (10,-10),  180 turn (22,-22)
    // Distances will need adjusted based on power and weight of the robot.

    public void spinLeft(double leftInches, double rightInches) {
        setupMotion("Spinning set amount");
        drivetrain.setPowers(-power, power);

        while (drivetrain.getLeftEncoderCount() > drivetrain.convertInchesToEncoderCounts(leftInches)
                && drivetrain.getRightEncoderCount() < drivetrain.convertInchesToEncoderCounts(rightInches)
                && opMode.opModeIsActive()) {
            opMode.telemetry.addData("LeftEncoderCount: ", drivetrain.getLeftEncoderCount());
            opMode.telemetry.addData("RightEncoderCount: ", drivetrain.getRightEncoderCount());
            opMode.telemetry.update();
        }
        endMotion();
    }
}



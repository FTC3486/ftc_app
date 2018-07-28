package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Filename: RangeAutoDriver.java
 * <p>
 * Description:
 * This class contains the methods that use the range sensors for predefined autonomous movements.
 * <p>
 * Methods:
 * squareUpToWall - From current location the robot will square up to the wall using the closest
 * range sensor
 * *
 * Example: robot.hardwareConfiguration.rangeAutoDriver.squareUpToWall()
 * <p>
 * Requirements:
 * - 2 range sensors, created and stored in the hardware configuration
 * - A wall
 * - A range auto driver object is created in a hardware configuration and accessed
 * in an autonomous program for use
 * - RangeSensor Extension created to manage more than one range sensors
 * <p>
 * Changelog:
 * -Edited and tested by Team 3486 on 7/8/2017.
 * -Edited file description and documentation 7/22/17
 */

public class RangeAutoDriver extends AutoDriver {
    private final Drivable hw;
    EncoderAutoDriver encoderAutoDriver;

    /**
     * Your Robot Configuration must implement this interface to be drivable by the RangeAutoDriver
     */
    public interface Drivable extends org.firstinspires.ftc.teamcode.RobotCoreExtensions.Drivable {
        RangeSensor getLeftRangeSensor();

        RangeSensor getRightRangeSensor();
    }

    public RangeAutoDriver(Drivable hw, LinearOpMode linearOpMode) {
        super(hw, linearOpMode);
        encoderAutoDriver = new EncoderAutoDriver(hw, linearOpMode);
        this.hw = hw;
    }

    public void squareUpToWall(double distance, double power) {
        setupMotion("Squaring up to a wall.");

        int initialLeftReading = hw.getLeftRangeSensor().getUltrasonicRange();
        int initialRightReading = hw.getRightRangeSensor().getUltrasonicRange();
        int distanceToDrive = 0;
        double correctionFactor = 20;

        // Measure difference between the 2 sides and corrects the further side
        // by driving that side farther to make the distances from the wall the same.
        // This also aligns the robot perpendicular to the wall.
        if (initialLeftReading > initialRightReading) {
            // Divides by 2.54 to convert range sensor centimeter readings to inches.
            encoderAutoDriver.driveLeftSideToDistance((initialLeftReading - initialRightReading) / 2.54);

            // distanceToDrive is how far the robot is from the wall.
            distanceToDrive = hw.getRightRangeSensor().getUltrasonicRange();
        } else if (initialRightReading > initialLeftReading) {
            // Divides by 2.54 to convert range sensor centimeter readings to inches.
            encoderAutoDriver.driveRightSideToDistance(1.0 * (initialRightReading - initialLeftReading) / 2.54);

            // distanceToDrive is how far the robot is from the wall.
            distanceToDrive = hw.getLeftRangeSensor().getUltrasonicRange();
        }
        opMode.sleep(5000);

        // correctionFactor is subtracted from the distanceToDrive so the robot doesn't run into the wall.
        // Divides by 2.54 to convert range sensor centimeter readings to inches.
        encoderAutoDriver.driveToDistance((distanceToDrive - correctionFactor) / 2.54);
        endMotion();
    }
}

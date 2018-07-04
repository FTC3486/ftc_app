package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Filename: RangeAutoDriver.java
 *
 * Description:
 *     This class contains the methods that use the range sensors for predefined autonomous movements.
 *
 * Methods:
 *     squareUpToWall - From current location the rover will square up to the wall using the closest
 *                      range sensor
 *  *
 * Example: rover.hardwareConfiguration.rangeAutoDriver.squareUpToWall()
 *
 * Requirements:
 *     - 2 range sensors, created and stored in the hardware configuration
 *     - A wall
 *     - A range auto driver object is created in a hardware configuration and accessed
 *       in an autonomous program for use
 *     - RangeSensor Extension created to manage more than one range sensors
 *
 * Changelog:
 *     -Edited and tested by Team 3486 on 7/8/2017.
 *     -Edited file description and documentation 7/22/17
 */

public class RangeAutoDriver extends AutoDriver
{
    EncoderAutoDriver encoderAutoDriver;
    public RangeAutoDriver(Rover rover, LinearOpMode linearOpMode)
    {
        super(rover,linearOpMode);
        encoderAutoDriver = new EncoderAutoDriver(rover, linearOpMode);
    }

    public void squareUpToWall(double distance, double power)
    {
        setupMotion("Squaring up to a wall.");

        int initialLeftReading = rover.hw.leftRangeSensor.getUltrasonicRange();
        int initialRightReading = rover.hw.rightRangeSensor.getUltrasonicRange();
        int distanceToDrive = 0;
        double correctionFactor = 20;

        // Measure difference between the 2 sides and corrects the further side
        // by driving that side farther to make the distances from the wall the same.
        // This also aligns the rover perpendicular to the wall.
        if(initialLeftReading > initialRightReading)
        {
            // Divides by 2.54 to convert range sensor centimeter readings to inches.
            encoderAutoDriver.driveLeftSideToDistance((initialLeftReading - initialRightReading)/2.54);

            // distanceToDrive is how far the rover is from the wall.
            distanceToDrive = rover.hw.rightRangeSensor.getUltrasonicRange();
        }
        else if(initialRightReading > initialLeftReading)
        {
            // Divides by 2.54 to convert range sensor centimeter readings to inches.
            encoderAutoDriver.driveRightSideToDistance( 1.0 * (initialRightReading - initialLeftReading)/2.54);

            // distanceToDrive is how far the rover is from the wall.
            distanceToDrive = rover.hw.leftRangeSensor.getUltrasonicRange();
        }
        opMode.sleep(5000);

        // correctionFactor is subtracted from the distanceToDrive so the rover doesn't run into the wall.
        // Divides by 2.54 to convert range sensor centimeter readings to inches.
        encoderAutoDriver.driveToDistanceForwards((distanceToDrive - correctionFactor)/2.54);
        endMotion();
    }
}

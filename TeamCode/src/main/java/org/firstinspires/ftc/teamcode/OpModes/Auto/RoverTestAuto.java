package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotConfiguration.RelicRecovery.RelicRecoveryRobot;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Drivetrain;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.EncoderAutoDriver;

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

@Autonomous (group = "Blue")
public class RoverTestAuto extends LinearOpMode {

    @Override
    public void runOpMode() {
        RelicRecoveryRobot relicRecoveryRobot = new RelicRecoveryRobot(this.hardwareMap);
        EncoderAutoDriver encoderAutoDriver = new EncoderAutoDriver(relicRecoveryRobot, this);
        //RangeAutoDriver rangeAutoDriver = new RangeAutoDriver(rover, this);
        relicRecoveryRobot.initialize();

        waitForStart();
        encoderAutoDriver.setPower(.7);
       encoderAutoDriver.driveToDistance(86);
        //encoderAutoDriver.spinRight(11,-11);
        //encoderAutoDriver.driveToDistance(132);
        //encoderAutoDriver.spinRight(10,-10);
       // encoderAutoDriver.driveToDistance(64);
        //encoderAutoDriver.spinRight(10,-10);
        //encoderAutoDriver.driveToDistance(132);
        //encoderAutoDriver.setPower(.7);
        //encoderAutoDriver.driveToDistanceBackwards(-20); // Works needs negitive number - why!!!
        //encoderAutoDriver.setPower(.5);
      //  encoderAutoDriver.spinRight(10,-10); //  10  90 degree turn
        //encoderAutoDriver.spinRight(22,-22);// 22 180 degree turn
      //  encoderAutoDriver.spinRight(40,-40);
        //encoderAutoDriver.spinLeft(-12,12); // works why
        //encoderAutoDriver.driveLeftSideToDistance(15);
        //encoderAutoDriver.driveLeftSideToDistance(-15);
       //encoderAutoDriver.driveRightSideToDistance(10);
       // encoderAutoDriver.driveRightSideToDistance(-10);
        //encoderAutoDriver.spinLeft(9,9);
        //rangeAutoDriver.
    }
}
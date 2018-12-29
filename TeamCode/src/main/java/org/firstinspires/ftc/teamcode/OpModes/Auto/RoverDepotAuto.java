package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotConfiguration.RelicRecovery.RelicRecoveryRobot;
import org.firstinspires.ftc.teamcode.RobotConfiguration.RoverRuckus.RoverRuckusRobot;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.EncoderAutoDriver;

/*
    Filename: RoverDepotAuto.java

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

@Autonomous (group = "Blue" )
public class RoverDepotAuto extends LinearOpMode {

    @Override
    public void runOpMode() {
        RoverRuckusRobot roverRuckusRobot = new RoverRuckusRobot(this.hardwareMap);
        EncoderAutoDriver encoderAutoDriver = new EncoderAutoDriver(roverRuckusRobot, this);
        //RangeAutoDriver rangeAutoDriver = new RangeAutoDriver(rover, this);
        roverRuckusRobot.initialize();

        waitForStart();

        encoderAutoDriver.setPower(1.0);

        while(!roverRuckusRobot.latch.isFullyExtended())
        {
            roverRuckusRobot.latch.extend();
            telemetry.addData("Latch state", roverRuckusRobot.latch.toString());
            telemetry.update();
        }

        encoderAutoDriver.driveToDistance(-2);

        encoderAutoDriver.spinLeft(-10.0,10.0);
        sleep(200);

        encoderAutoDriver.driveToDistance(-30);
        /*
        while(!roverRuckusRobot.latch.isFullyRetracted())
        {
            roverRuckusRobot.latch.retract();
        }
        */

        //encoderAutoDriver.driveToDistance(48);
        //sleep(200);
        /*
        encoderAutoDriver.spinLeft(-5, 5);
        sleep(200);
        encoderAutoDriver.driveToDistance(-96);
        */

        // roverRuckusRobot.jewelArm.extend();
        //sleep(5000);
        //relicRecoveryRobot.jewelArm.fullyRetract();
        //sleep(5000);
        //relicRecoveryRobot.jewelArm.fullyExtend();
        //sleep(5000);
        //encoderAutoDriver.spinRight(11,-11);
        //encoderAutoDriver.driveToDistance(132);
        //encoderAutoDriver.spinRight(10,-10);
       // encoderAutoDriver.driveToDistance(64);
        //encoderAutoDriver.spinRight(72,-72);
        //encoderAutoDriver.driveToDistance(132);
        //encoderAutoDriver.setPower(.7);
        //encoderAutoDriver.driveToDistanceBackwards(-20); // Works needs negitive number - why!!!
        //encoderAutoDriver.setPower(.5);
      //  encoderAutoDriver.spinRight(10,-10); //  10  90 degree turn
        //encoderAutoDriver.spinRight(22,-22);// 22 180 degree turn
      //  encoderAutoDriver.spinRight(40,-40);
        //encoderAutoDriver.spinLeft(-12,12); // works why
        //encoderAutoDriver.driveLeftSideToDistance(72);
        //encoderAutoDriver.driveLeftSideToDistance(-15);
       //encoderAutoDriver.driveRightSideToDistance(72);
       // encoderAutoDriver.driveRightSideToDistance(-10);
        //encoderAutoDriver.spinLeft(9,9);
        //rangeAutoDriver.
    }
}
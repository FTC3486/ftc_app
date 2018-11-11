package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotConfiguration.RelicRecovery.RelicRecoveryRobot;
import org.firstinspires.ftc.teamcode.RobotConfiguration.RoverRuckus.RoverRuckusRobot;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.EncoderAutoDriver;

/*
    Filename: RoverCraterAuto.java

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
public class RoverCraterAuto extends LinearOpMode {

    @Override
    public void runOpMode() {
        RoverRuckusRobot roverRuckusRobot = new RoverRuckusRobot(this.hardwareMap);
        EncoderAutoDriver encoderAutoDriver = new EncoderAutoDriver(roverRuckusRobot, this);
        //RangeAutoDriver rangeAutoDriver = new RangeAutoDriver(rover, this);
        roverRuckusRobot.initialize();

        waitForStart();

        encoderAutoDriver.setPower(.7);

        while(!roverRuckusRobot.latch.isFullyExtended())
        {
            roverRuckusRobot.latch.extend();
            telemetry.addData("Latch state", roverRuckusRobot.latch.toString());
            telemetry.update();
        }

        encoderAutoDriver.spinLeft(-10.0,10.0);
        sleep(200);
        encoderAutoDriver.driveToDistance(18);
        sleep(200);
        encoderAutoDriver.spinLeft(-10, 10);
        sleep(200);
        encoderAutoDriver.driveToDistance(36);
        sleep(200);
        encoderAutoDriver.spinLeft(-6,6);
        sleep(200);
        encoderAutoDriver.driveToDistance(60);
        sleep(200);
        encoderAutoDriver.driveToDistance(-96);


    }
}
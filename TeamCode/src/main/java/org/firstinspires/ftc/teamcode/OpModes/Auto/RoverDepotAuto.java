package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.hardware.ams.AMSColorSensor;
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

        //This is copy and pasted from RoverDepotAuto.java, the original code is commented out
        roverRuckusRobot.latch.manualStop();
        encoderAutoDriver.setPower(0.75);
        //base number 25
        encoderAutoDriver.driveLeftSideToDistance(-25);
        encoderAutoDriver.driveToDistance(-7);
        //larger numbers turn to blocks - right turn
        encoderAutoDriver.spinRight(10,-10);
        encoderAutoDriver.driveToDistance(-8);
        encoderAutoDriver.spinRight(0.4, -0.4);

        //Go to the end of the sampling items
        roverRuckusRobot.getDrivetrain().resetMotorEncoders();
        roverRuckusRobot.getDrivetrain().setPowers(0.3, 0.3);
        while ((roverRuckusRobot.getDrivetrain().getLeftEncoderCount() <= 2700) && !roverRuckusRobot.foundYellowObject() && opModeIsActive())
        {
            //telemetry.addData("Green Value", roverRuckusRobot.colorSensor.green());
            //telemetry.addData("Blue Value", roverRuckusRobot.colorSensor.blue());
            //telemetry.addData("Red Value", roverRuckusRobot.colorSensor.red());
            //telemetry.addData("LeftEncoder", roverRuckusRobot.getDrivetrain().getLeftEncoderCount());
            //telemetry.update();
        }
        double counts = roverRuckusRobot.getDrivetrain().getLeftEncoderCount();
        roverRuckusRobot.getDrivetrain().haltDrive();
        encoderAutoDriver.spinLeft(-10,10);
        encoderAutoDriver.driveToDistance(-6);
        encoderAutoDriver.driveToDistance(6);
        encoderAutoDriver.spinRight(10, -10);
        roverRuckusRobot.getDrivetrain().setPowers(0.5, 0.5);
        while(roverRuckusRobot.getDrivetrain().getLeftEncoderCount() <= 4825 - counts && opModeIsActive()) {telemetry.addData("Encoder", roverRuckusRobot.getDrivetrain().getLeftEncoderCount());}
        encoderAutoDriver.spinLeft(-7.7, 7.7);
        encoderAutoDriver.driveToDistance(-50);
        roverRuckusRobot.markerServo.open();
        encoderAutoDriver.driveToDistance(1.0);
    }
}
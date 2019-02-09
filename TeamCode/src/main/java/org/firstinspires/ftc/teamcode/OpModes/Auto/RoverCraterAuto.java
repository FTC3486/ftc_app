package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.function.Supplier;
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
 *     -Created by Saatvik on 10/5/18.
 *     -

 */

@Autonomous (group = "Blue" )
public class RoverCraterAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
        final RoverRuckusRobot roverRuckusRobot = new RoverRuckusRobot(this.hardwareMap);
        final EncoderAutoDriver encoderAutoDriver = new EncoderAutoDriver(roverRuckusRobot, this);
        //RangeAutoDriver rangeAutoDriver = new RangeAutoDriver(rover, this);
        roverRuckusRobot.initialize();

        waitForStart();

        encoderAutoDriver.setPower(1);
        while(!roverRuckusRobot.latch.isFullyExtended())
        {
            roverRuckusRobot.latch.extend();
            telemetry.addData("Latch state", roverRuckusRobot.latch.toString());
            telemetry.update();
        }

        //This is copy and pasted from RoverDepotAuto.java, the original code is commented out
        //encoderAutoDriver.driveToDistance(-1);
        roverRuckusRobot.latch.manualStop();
        encoderAutoDriver.setPower(0.5);
        encoderAutoDriver.driveLeftSideToDistance(-25.0);
        encoderAutoDriver.driveToDistance(-8);
        encoderAutoDriver.spinRight(10.0,-10.0);
        encoderAutoDriver.driveToDistance(-8);
        encoderAutoDriver.spinRight(0.2, -0.2);

        //Go to the end of the sampling items
        roverRuckusRobot.getDrivetrain().resetMotorEncoders();
        roverRuckusRobot.getDrivetrain().setPowers(0.3, 0.3);
        while ((roverRuckusRobot.getDrivetrain().getLeftEncoderCount() <= 3500) && !roverRuckusRobot.foundYellowObject() && opModeIsActive())
        {
            telemetry.addData("Green Value", roverRuckusRobot.colorSensor.green());
            telemetry.addData("Blue Value", roverRuckusRobot.colorSensor.blue());
            telemetry.addData("Red Value", roverRuckusRobot.colorSensor.red());
            telemetry.addData("LeftEncoder", roverRuckusRobot.getDrivetrain().getLeftEncoderCount());
            telemetry.update();
        }
        double counts = roverRuckusRobot.getDrivetrain().getLeftEncoderCount();
        roverRuckusRobot.getDrivetrain().haltDrive();
        encoderAutoDriver.spinLeft(-10,10);
        encoderAutoDriver.driveToDistance(-6);
        encoderAutoDriver.driveToDistance(6);
        encoderAutoDriver.spinRight(10, -10);
        roverRuckusRobot.getDrivetrain().setPowers(0.5, 0.5);
        while(roverRuckusRobot.getDrivetrain().getLeftEncoderCount() <= 5000 - counts && opModeIsActive()) {telemetry.addData("Encoder", roverRuckusRobot.getDrivetrain().getLeftEncoderCount());}

/*        encoderAutoDriver.spinLeft(-7.5, 7.5);
        encoderAutoDriver.driveToDistance(50);
        encoderAutoDriver.spinLeft(-22, 22);
        roverRuckusRobot.markerServo.open();
        encoderAutoDriver.driveToDistance(10);
*/

    }
}
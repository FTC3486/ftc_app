package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.EncoderAutoDriver;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.RangeAutoDriver;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Rover;

/**
 * Created by Matthew on 7/1/2017.
 */

@Autonomous(name = "Blue Left Auto", group = "BlueAuto")
public class BlueAutoLeft extends LinearOpMode {
    Rover rover = new Rover(this);
    EncoderAutoDriver encoderAutoDriver = new EncoderAutoDriver(rover, this);
    RangeAutoDriver rangeAutoDriver = new RangeAutoDriver(rover, this);

    @Override
    public void runOpMode() throws InterruptedException {
        rover.init();
        rover.hw.jewelArm.autoInit();

        waitForStart();
        rover.hw.drivetrain.resetMotorEncoders();
        encoderAutoDriver.driveToDistanceForwards(1000);
        encoderAutoDriver.driveLeftSideToDistance(-9);
        encoderAutoDriver.driveRightSideToDistance(9);
        encoderAutoDriver.spinLeft(9,9);
        //rangeAutoDriver.

    }
}
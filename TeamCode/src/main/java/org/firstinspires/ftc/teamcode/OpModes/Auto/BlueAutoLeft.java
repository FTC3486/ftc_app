package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.EncoderAutoDriver;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Robot;

/**
 * Created by Matthew on 7/1/2017.
 */

@Autonomous(name = "Blue Left Auto", group = "BlueAuto")
public class BlueAutoLeft extends LinearOpMode {
    Robot robot = new Robot(this);
    EncoderAutoDriver encoderAutoDriver = new EncoderAutoDriver(robot, this);

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init();
        robot.hw.jewelArm.autoInit();

        waitForStart();
        robot.hw.drivetrain.resetMotorEncoders();
        encoderAutoDriver.driveToDistanceForwards(1000);
        encoderAutoDriver.driveLeftSideToDistance(-9);
        encoderAutoDriver.driveRightSideToDistance(9);
    }
}
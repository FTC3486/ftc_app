package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotConfiguration.RelicRecovery.RelicRecoveryRobot;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.GamepadWrapper;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.TeleopDriver;

/**
 * Created by 3486 on 7/15/2017.
 */

@TeleOp(name = "Rover Teleop", group = "Teleop2017")
public class RoverTeleop extends OpMode {
    //Declare parts of the robot that will be used by this Teleop
    private RelicRecoveryRobot robotRelicRecoveryRobot;
    private GamepadWrapper joy1 = new GamepadWrapper();
    private TeleopDriver teleopDriver;

    @Override
    public void init() {
        robotRelicRecoveryRobot=new RelicRecoveryRobot(this.hardwareMap);
        teleopDriver=new TeleopDriver(robotRelicRecoveryRobot);
        robotRelicRecoveryRobot.initialize();

        robotRelicRecoveryRobot.jewelArm.fullyExtend();
    }

    @Override
    public void loop() {
        robotRelicRecoveryRobot.jewelArm.fullyExtend();
        joy1.update(gamepad1);

        //Toggle Half Speed on the drivetrain
        if (joy1.toggle.right_stick_button) {
            //Swap front and back of the robot, and control the drive train at half speed
            teleopDriver.setMaxSpeed(0.5f);
            if (joy1.toggle.left_stick_button) {
                teleopDriver.tankDrive(gamepad1, TeleopDriver.Direction.BACKWARD);
            } else {
                teleopDriver.tankDrive(gamepad1, TeleopDriver.Direction.FORWARD);
            }
        } else {
            //Swap front and back of the robot, and control the drive train
            teleopDriver.setMaxSpeed(1f);
            if (joy1.toggle.left_stick_button) {
                teleopDriver.tankDrive(gamepad1, TeleopDriver.Direction.BACKWARD);
            } else {
                teleopDriver.tankDrive(gamepad1, TeleopDriver.Direction.FORWARD);
            }
        }
    }
}

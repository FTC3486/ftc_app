package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RobotConfiguration.RoverRuckus.RoverRuckusRobot;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.GamepadWrapper;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.TeleopDriver;

/**
 * Created by 3486 on 7/15/2017.
 */

@TeleOp(name = "Rover Teleop", group = "Teleop2017")
public class RoverTeleop extends OpMode {
    //Declare parts of the robot that will be used by this Teleop
    private RoverRuckusRobot roverRuckusRobot;
    private GamepadWrapper joy1 = new GamepadWrapper();
    private GamepadWrapper joy2 = new GamepadWrapper();
    private TeleopDriver teleopDriver;

    @Override
    public void init() {
        roverRuckusRobot = new RoverRuckusRobot(this.hardwareMap);
        roverRuckusRobot.initialize();
        teleopDriver = new TeleopDriver(roverRuckusRobot);
        //roverRuckusRobot.initialize();
        //roverRuckusRobot.jewelArm.fullyExtend();
    }

    @Override
    public void loop() {
        joy1.update(gamepad1);
        joy2.update(gamepad2);

        //Toggle Half Speed on the drivetrain
        if (joy1.toggle.right_stick_button) {
            //Swap front and back of the robot, and control the drive train at half speed
            teleopDriver.setMaxSpeed(0.3);
            if (joy1.toggle.left_stick_button) {
                teleopDriver.tankDrive(gamepad1, TeleopDriver.Direction.BACKWARD);
            } else {
                teleopDriver.tankDrive(gamepad1, TeleopDriver.Direction.FORWARD);
            }
        } else {
            //Swap front and back of the robot, and control the drive train
            teleopDriver.setMaxSpeed(0.4);
            if (joy1.toggle.left_stick_button) {
                teleopDriver.tankDrive(gamepad1, TeleopDriver.Direction.BACKWARD);
            } else {
                teleopDriver.tankDrive(gamepad1, TeleopDriver.Direction.FORWARD);
            }
        }

        //Buttons for the latch/arm lift
        if (gamepad1.right_bumper) {
            roverRuckusRobot.latch.manualExtend();
        } else if (gamepad1.left_bumper) {
            roverRuckusRobot.latch.manualRetract();
        } else {
            roverRuckusRobot.latch.manualStop();
        }

        //Buttons for the arm extension/retraction
        if (gamepad2.left_stick_y > joy2.getLeftStickThreshold()) {
            roverRuckusRobot.arm.run(gamepad2.left_stick_y);
        } else if (gamepad2.left_stick_y < -joy2.getLeftStickThreshold()) {
            roverRuckusRobot.arm.reverse(gamepad2.left_stick_y);
        } else {
            roverRuckusRobot.arm.stop();
        }

        //Buttons for the flapper motor
        if (gamepad2.a) {
            roverRuckusRobot.flapperMotor.run();
        } else if (gamepad2.b) {
            roverRuckusRobot.flapperMotor.reverse();
        } else {
            roverRuckusRobot.flapperMotor.stop();
        }

        //Buttons for the flapper servo
        if (Math.abs(gamepad2.left_stick_y) > joy2.getLeftStickThreshold()) {
            roverRuckusRobot.flapperServo.run(gamepad2.left_stick_y);
        }

        // TODO: jewelColor should be private. Telemetry should be exposed through toString methods

        //telemetry.addData("Is Not Pressed", roverRuckusRobot.touch1();

        //telemetry.addData("Green Value", roverRuckusRobot.jewelColorSensor.green());
        //telemetry.addData("Blue Value", roverRuckusRobot.jewelColorSensor.blue());
        //telemetry.addData("Red Value", roverRuckusRobot.jewelColorSensor.red());
        //telemetry.addData("Alpha Value", roverRuckusRobot.jewelColorSensor.alpha());
        //telemetry.addData("ARGB Value", roverRuckusRobot.jewelColorSensor.argb());
        //telemetry.addData("JewelServo", roverRuckusRobot.jewelArm);
        //telemetry.update();

    }
}
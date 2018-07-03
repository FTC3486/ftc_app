package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.GamepadWrapper;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Robot;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.TeleopDriver;

/**
 * Created by 3486 on 7/15/2017.
 */

@TeleOp(name = "Robot Teleop", group = "Teleop2017")

public class TWATeleop extends OpMode {
    //Declare parts of the robot that will be used by this Teleop
    private Robot robotRobot = new Robot(this);
    private GamepadWrapper joy1;
    private TeleopDriver teleopDriver;
    private int spinnerPos;

    @Override
    public void init() {
        robotRobot.init();
        teleopDriver = new TeleopDriver(this, robotRobot.hw.drivetrain);
        teleopDriver.setMaxSpeed(1f);
        joy1 = new GamepadWrapper();

        //Initial subsystem positions

        spinnerPos = 0;

        robotRobot.hw.jewelArm.up();
        robotRobot.hw.relicClaw.openClaw();
    }


    @Override
    public void start() {
    }

    @Override
    public void init_loop() { }

    @Override
    public void loop() {
        robotRobot.hw.jewelArm.up();
        joy1.update(gamepad1);

        //Toggle Half Speed on the drivetrain
        if (joy1.toggle.right_stick_button) {
            //Swap front and back of the robot, and control the drive train at half speed
            if (joy1.toggle.left_stick_button) {
                teleopDriver.tank_drive(gamepad1, TeleopDriver.Direction.BACKWARD);
            } else {
                teleopDriver.tank_drive(gamepad1, TeleopDriver.Direction.FORWARD);
            }
        } else {
            //Swap front and back of the robot, and control the drive train
            if (joy1.toggle.left_stick_button) {
                teleopDriver.half_speed_tank_drive(gamepad1, TeleopDriver.Direction.BACKWARD);
            } else {
                teleopDriver.half_speed_tank_drive(gamepad1, TeleopDriver.Direction.FORWARD);
            }
        }

        //Runs Relic Lift down while button is head
        if (gamepad2.dpad_down) {
            robotRobot.hw.relicLift.retract();
        }//Runs Relic Lift up while button is held
        else if (gamepad2.dpad_up) {
            robotRobot.hw.relicLift.lift();
        }//Stop all Relic Lift motion while nothing is pressed
        else {
            robotRobot.hw.relicLift.stop();
        }
        //Extends Relic Arm while button is held
        if (gamepad2.dpad_right) {
            robotRobot.hw.relicArm.extend();
        }//Retracts Relic Arm while button is held
        else if (gamepad2.dpad_left) {
            robotRobot.hw.relicArm.retract();
        }//Stop all Relic Arm motion while nothing is pressed
        else {
            robotRobot.hw.relicArm.stop();
        }
        //Open Relic Claw
        if (gamepad2.a) {
            robotRobot.hw.relicClaw.releaseRelic();
        }//Close Relic Claw
        else if (gamepad2.b) {
            robotRobot.hw.relicClaw.grabRelic();
            //robotRobot.hw.relicClaw.closeClaw();
        }
        //Set Relic CLaw Pivot to Position 1
        if (gamepad2.x) {
            robotRobot.hw.relicClaw.pivotPosition1();
        }//Set Relic CLaw Pivot to Position 2
        else if (gamepad2.y) {
            robotRobot.hw.relicClaw.pivotPosition2();
        }
    }

    @Override
    public void stop() {
    }
}

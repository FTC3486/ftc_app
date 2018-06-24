package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.GamepadWrapper;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.TWA;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.TeleopDriver;

/**
 * Created by 3486 on 7/15/2017.
 */

@TeleOp(name = "TWA Teleop", group = "Teleop2017")

public class TWATeleop extends OpMode {
    //Declare parts of the robot that will be used by this Teleop
    private TWA twaRobot = new TWA(this);
    private GamepadWrapper joy1;
    private TeleopDriver teleopDriver;
    private int spinnerPos;

    @Override
    public void init() {
        twaRobot.init();
        teleopDriver = new TeleopDriver(this, twaRobot.hw.drivetrain);
        teleopDriver.setMaxSpeed(1f);
        joy1 = new GamepadWrapper();

        //Initial subsystem positions

        spinnerPos = 0;

        twaRobot.hw.jewelArm.up();
        twaRobot.hw.relicClaw.openClaw();
    }


    @Override
    public void start() {
    }

    @Override
    public void init_loop() {
        telemetry.addData("GlyphGrabber",twaRobot.hw.glyphGrabber);
        telemetry.addData("GlyphSpinner", twaRobot.hw.glyphSpinner);
        telemetry.addData("GlyphLift", twaRobot.hw.glyphLift);
    }

    @Override
    public void loop() {
        twaRobot.hw.jewelArm.up();
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

        //Spin Glyph grabber and Swap Glyph Grabber buttons so button orientation stays the same
        if (joy1.onPress.y || twaRobot.hw.glyphSpinner.isFlipping()) {
            twaRobot.hw.glyphSpinner.flip();
        }

        //Lift Glyph Grabber while button is held
        if (gamepad1.left_trigger > 0.2 || twaRobot.hw.glyphSpinner.isFlipping()) {
            twaRobot.hw.glyphLift.lift();
        }//Lower Glyph Grabber while button is held unless touch sensor detects that the lift is bottomed out
        else if (gamepad1.right_trigger > 0.2) {
            twaRobot.hw.glyphLift.retract();
        } else {
            //Stop all Glyph Lift motion while nothing is pressed
            twaRobot.hw.glyphLift.stop();
        }

        twaRobot.hw.glyphGrabber.gripTop(joy1.toggle.left_bumper, twaRobot.hw.glyphSpinner.isFlipped());
        twaRobot.hw.glyphGrabber.gripBottom(joy1.toggle.right_bumper, twaRobot.hw.glyphSpinner.isFlipped());

        //Runs Relic Lift down while button is head
        if (gamepad2.dpad_down) {
            twaRobot.hw.relicLift.retract();
        }//Runs Relic Lift up while button is held
        else if (gamepad2.dpad_up) {
            twaRobot.hw.relicLift.lift();
        }//Stop all Relic Lift motion while nothing is pressed
        else {
            twaRobot.hw.relicLift.stop();
        }
        //Extends Relic Arm while button is held
        if (gamepad2.dpad_right) {
            twaRobot.hw.relicArm.extend();
        }//Retracts Relic Arm while button is held
        else if (gamepad2.dpad_left) {
            twaRobot.hw.relicArm.retract();
        }//Stop all Relic Arm motion while nothing is pressed
        else {
            twaRobot.hw.relicArm.stop();
        }
        //Open Relic Claw
        if (gamepad2.a) {
            twaRobot.hw.relicClaw.releaseRelic();
        }//Close Relic Claw
        else if (gamepad2.b) {
            twaRobot.hw.relicClaw.grabRelic();
            //twaRobot.hw.relicClaw.closeClaw();
        }
        //Set Relic CLaw Pivot to Position 1
        if (gamepad2.x) {
            twaRobot.hw.relicClaw.pivotPosition1();
        }//Set Relic CLaw Pivot to Position 2
        else if (gamepad2.y) {
            twaRobot.hw.relicClaw.pivotPosition2();
        }

        telemetry.addData("GlyphGrabber",twaRobot.hw.glyphGrabber);
        telemetry.addData("GlyphSpinner", twaRobot.hw.glyphSpinner);
        telemetry.addData("GlyphLift", twaRobot.hw.glyphLift);
    }

    @Override
    public void stop() {
    }
}

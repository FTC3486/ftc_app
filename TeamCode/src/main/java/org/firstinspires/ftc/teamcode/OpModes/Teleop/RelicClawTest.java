package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.GamepadWrapper;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.TWA;
import org.firstinspires.ftc.teamcode.Subsystems.RelicClaw;

/**
 * Created by 3486 on 7/15/2017.
 */

@TeleOp(name="RelicGrabberTest", group="Teleop2016")
@Disabled
public class RelicClawTest extends OpMode
{
    GamepadWrapper joy1;
    TWA twaRobot = new TWA(this);

    @Override
    public void init() {
        joy1 = new GamepadWrapper();
        twaRobot.init();


    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start()
    {
    }

    @Override
    public void loop()
    {
        joy1.update(gamepad1);
        if (gamepad1.left_bumper){
            twaRobot.hw.relicClaw.closeClaw();
        } else if(gamepad1.right_bumper){
            twaRobot.hw.relicClaw.openClaw();
        } else if(gamepad1.a){
            twaRobot.hw.relicClaw.pivotCounterclockwise();
        } else if(gamepad1.b){
            twaRobot.hw.relicClaw.pivotClockwise();
        }else if(gamepad1.x){
            twaRobot.hw.relicClaw.grabRelic();
        }else if (gamepad1.y){
            twaRobot.hw.relicClaw.releaseRelic();
        }else if(gamepad1.dpad_right){
            twaRobot.hw.relicClaw.pivotPosition1();
        }else if(gamepad1.dpad_left){
            twaRobot.hw.relicClaw.pivotPosition2();
        }


        telemetry.addData("Grabber 1", twaRobot.hw.relicClaw.ClawServo1.getPosition());
        telemetry.addData("Grabber 2", twaRobot.hw.relicClaw.ClawServo2.getPosition());
        telemetry.addData("Pivot", twaRobot.hw.relicClaw.Pivot.getPosition());
        telemetry.update();
    }
    @Override
    public void stop(){
    }
}
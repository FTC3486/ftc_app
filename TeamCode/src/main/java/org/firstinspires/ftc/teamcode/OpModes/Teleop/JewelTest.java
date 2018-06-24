package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.GamepadWrapper;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.TWA;

/**
 * Created by 3486 on 7/15/2017.
 */

@TeleOp(name="Jewel Test", group="Teleop2016")
//@Disabled
public class JewelTest extends OpMode
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
            twaRobot.hw.jewelArm.down();
        } else if(gamepad1.right_bumper){
            twaRobot.hw.jewelArm.up();
        } else if(gamepad1.a && gamepad1.b){

        }
        telemetry.addData("Green Value",twaRobot.hw.jewelArm.jewelColor.green());
        telemetry.addData("Blue Value", twaRobot.hw.jewelArm.jewelColor.blue());
        telemetry.addData("Red Value",twaRobot.hw.jewelArm.jewelColor.red());
        telemetry.addData("Alpha Value", twaRobot.hw.jewelArm.jewelColor.alpha());
        telemetry.addData("ARGB Value", twaRobot.hw.jewelArm.jewelColor.argb());
        telemetry.addData("JewelServo", twaRobot.hw.jewelArm.armServo.getPosition());
        telemetry.update();

    }
    @Override
    public void stop(){
    }
}
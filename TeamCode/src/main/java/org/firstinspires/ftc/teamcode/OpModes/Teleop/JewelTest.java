package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.GamepadWrapper;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Robot;

/**
 * Created by 3486 on 7/15/2017.
 */

@TeleOp(name="Jewel Test", group="Teleop2016")
//@Disabled
public class JewelTest extends OpMode
{
    GamepadWrapper joy1;
    Robot robotRobot = new Robot(this);

    @Override
    public void init() {
        joy1 = new GamepadWrapper();
        robotRobot.init();

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
            robotRobot.hw.jewelArm.down();
        } else if(gamepad1.right_bumper){
            robotRobot.hw.jewelArm.up();
        } else if(gamepad1.a && gamepad1.b){

        }
        telemetry.addData("Green Value", robotRobot.hw.jewelArm.jewelColor.green());
        telemetry.addData("Blue Value", robotRobot.hw.jewelArm.jewelColor.blue());
        telemetry.addData("Red Value", robotRobot.hw.jewelArm.jewelColor.red());
        telemetry.addData("Alpha Value", robotRobot.hw.jewelArm.jewelColor.alpha());
        telemetry.addData("ARGB Value", robotRobot.hw.jewelArm.jewelColor.argb());
        telemetry.addData("JewelServo", robotRobot.hw.jewelArm.armServo.getPosition());
        telemetry.update();

    }
    @Override
    public void stop(){
    }
}
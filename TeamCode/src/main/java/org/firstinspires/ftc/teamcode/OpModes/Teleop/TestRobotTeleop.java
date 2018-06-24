package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.GamepadWrapper;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.TeleopDriver;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.TestRobot;

/**
 * Created by 3486 on 7/15/2017.
 */

@TeleOp(name="Demo Bot Teleop", group="Teleop2016")
@Disabled
public class TestRobotTeleop extends OpMode
{
    TestRobot testRobot = new TestRobot(this);
    GamepadWrapper joy1;
    TeleopDriver teleopDriver;

    @Override
    public void init() {
        testRobot.init();
        teleopDriver = new TeleopDriver(this, testRobot.hw.drivetrain);
        teleopDriver.setMaxSpeed(0.5f);
        joy1 = new GamepadWrapper();
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

        if(joy1.toggle.x)
        {
            teleopDriver.tank_drive(gamepad1, TeleopDriver.Direction.BACKWARD);
        }
        else
        {
            teleopDriver.tank_drive(gamepad1, TeleopDriver.Direction.FORWARD);
        }

        if(gamepad1.a)
        {
            testRobot.hw.spinner.setPower(1);
        }
        else if(gamepad1.y)
        {
            testRobot.hw.spinner.setPower(-1);
        }

        else
        {
            testRobot.hw.spinner.setPower(0);
        }
    }
    @Override
    public void stop(){
    }
}

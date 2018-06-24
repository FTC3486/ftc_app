package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.GamepadWrapper;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.TWARobot;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.TeleopDriver;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.TestRobot;

/**
 * Created by 3486 on 7/15/2017.
 */

@TeleOp(name="TWARobot Teleop", group="Teleop2016")
@Disabled
public class TWARobotTeleop extends OpMode
{
    TWARobot twaRobot = new TWARobot(this);
    GamepadWrapper joy1;
    TeleopDriver teleopDriver;

    @Override
    public void init() {
        twaRobot.init();
        teleopDriver = new TeleopDriver(this, twaRobot.hw.drivetrain);
        teleopDriver.setMaxSpeed(1f);
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
    }
    @Override
    public void stop(){
    }
}

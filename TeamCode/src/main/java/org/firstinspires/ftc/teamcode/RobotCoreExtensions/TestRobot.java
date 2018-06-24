package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by Matthew on 3/10/2017.
 */

public class TestRobot
{
    public TestRobotHardwareConfiguration hw;
    public OpMode opMode;

    public TestRobot(OpMode opMode) {
        this.opMode = opMode;
    }

    public void init()
    {
        hw = new TestRobotHardwareConfiguration(opMode);
        hw.init();
    }
}

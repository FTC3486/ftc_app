package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by carrotnation on 3/10/2017.
 *
 * TWA robot is the drivetrain product.
 */

public class TWARobot {
    public TWARobotHardwareConfiguration hw;
    private OpMode opMode;

    public TWARobot(OpMode opMode) {
        this.opMode = opMode;
    }

    public void init() {
        hw = new TWARobotHardwareConfiguration(opMode);
        hw.init();
    }
}

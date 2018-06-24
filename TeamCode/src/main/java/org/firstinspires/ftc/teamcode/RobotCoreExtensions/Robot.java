package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Matthew on 3/10/2017.
 */

public class Robot {
    public HardwareConfiguration hw;
    private LinearOpMode opMode;

    public Robot(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public void init() {
        hw = new HardwareConfiguration(opMode);
        hw.init();
    }
}

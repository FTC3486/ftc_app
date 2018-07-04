package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by carrotnation on 3/10/2017.
 *
 *
 */

public class Rover {
    public HardwareConfiguration hw;
    private OpMode opMode;

    public Rover(OpMode opMode) {
        this.opMode = opMode;
    }

    public void init() {
        hw = new HardwareConfiguration(opMode);
        hw.init();
    }
}

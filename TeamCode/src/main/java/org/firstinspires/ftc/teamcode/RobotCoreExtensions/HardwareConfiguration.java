package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class HardwareConfiguration{
    private OpMode opMode;
    protected Drivetrain drivetrain;

    public HardwareConfiguration(OpMode opMode) {
        this.opMode = opMode;
    }

    /** Place necessary robot initialization code here.**/
    abstract public void initialize();
}


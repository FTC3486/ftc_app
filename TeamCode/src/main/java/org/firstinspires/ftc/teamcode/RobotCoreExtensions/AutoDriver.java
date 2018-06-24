package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

/**

 * Filename: AutoDriver.java
 *
 * Description:
 *     This extension is the parent for all other Autodrivers called by all autonomous movements.
 *     This class defines the beginning and end of each movement.
 *
 * Methods:
 *     setWaitTimeBetweenMovements(int milliseconds)
 *     setupMotion(String motion_description)
 *     endMotion()
 *     eStop()
 *
 *
 * Requirements:
 *     - Hardware configuration
 *
 * Changelog:
 *     -Created by Jacob on 2/24/16
 *     -Edited file description and documentation 7/24/17
 *
 */
public abstract class AutoDriver {
    HardwareConfiguration hw;
    private StallMonitor stallMonitor = new StallMonitor(this);
    protected double power = 1.0D;
    private int wait_time_ms = 500;
    protected boolean eStop = false;


    public AutoDriver(HardwareConfiguration hw)
    {
        this.hw = hw;
    }

    public void setWaitTimeBetweenMovements(int milliseconds)
    {
        if (milliseconds < 0) throw new IllegalArgumentException("the wait time should always be positive");
        this.wait_time_ms = milliseconds;
    }
    // Creates setupMotion method - Resets encoders and then starts monitoring the robot for stalling.

    protected void setupMotion(String motion_description)
    {
        eStop = false;
        hw.opMode.telemetry.addData("AutoDriver", motion_description);
        hw.opMode.telemetry.update();
        hw.drivetrain.resetMotorEncoders();
        stallMonitor.startMonitoring();
    }

    // Creates endMotion method - Stops monitoring the robot for stalling.

    protected void endMotion()
    {
        hw.drivetrain.haltDrive();
        stallMonitor.stopMonitoring();
        hw.opMode.telemetry.addData("AutoDriver", "Halting");
        hw.opMode.telemetry.update();
        hw.opMode.sleep(wait_time_ms);
    }

    // Creates the eStop method to halt program.

    protected void eStop()
    {
        eStop = true;
        endMotion();
    }
}

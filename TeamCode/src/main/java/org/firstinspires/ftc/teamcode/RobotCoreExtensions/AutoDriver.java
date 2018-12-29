package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.function.Supplier;

/**
 * Filename: AutoDriver.java
 * <p>
 * Description:
 * This extension is the parent for all other Autodrivers called by all autonomous movements.
 * This class defines the beginning and end of each movement.
 * <p>
 * Methods:
 * setWaitTimeBetweenMovements(int milliseconds)
 * setupMotion(String motion_description)
 * endMotion()
 * isMovementTerminated()
 * <p>
 * <p>
 * Requirements:
 * - Hardware configuration
 * <p>
 * Changelog:
 * -Created by Jacob on 2/24/16
 * -Edited file description and documentation 7/24/17
 */
public abstract class AutoDriver implements StallMonitor.EmergencyStoppable {
    protected final Drivable hw;
    protected final LinearOpMode opMode;
    private final StallMonitor stallMonitor;
    private int timeBetweenMovements = 500;
    private boolean isStallMonitoringEnabled = true;
    private boolean isMovementTerminated = false;
    protected double power;

    AutoDriver(Drivable hw, LinearOpMode opMode) {
        this.hw = hw;
        this.opMode = opMode;
        this.stallMonitor = new StallMonitor(this, hw.getDrivetrain());
    }

    protected void setWaitTimeBetweenMovements(int milliseconds) {
        if (milliseconds < 0) {
            throw new IllegalArgumentException("Wait time between movements should always be positive");
        }
        this.timeBetweenMovements = milliseconds;
    }

    /**
     * Reset encoders and then start monitoring the robot for stalling.
     */
    protected void setupMotion(String motion_description) {
        isMovementTerminated = false;
        opMode.telemetry.addData("AutoDriver", motion_description);
        opMode.telemetry.update();
        hw.getDrivetrain().resetMotorEncoders();
        if (isStallMonitoringEnabled) {
            stallMonitor.startMonitoring();
       }
    }

    /**
     * Stop monitoring the robot for stalling.
     */
    protected void endMotion() {
        hw.getDrivetrain().haltDrive();
        stallMonitor.stopMonitoring();
        opMode.telemetry.addData("AutoDriver", "Halting");
        opMode.telemetry.update();
        opMode.sleep(timeBetweenMovements);
    }

    /**
     * Stop the robot
     */
    @Override
    public void eStop() {
        isMovementTerminated = true;
    }

    public boolean isMovementTerminated() {
        return isMovementTerminated;
    }

    public boolean isStallMonitoringEnabled() {
        return isStallMonitoringEnabled;
    }

    public void enableStallMonitoring() {
        isStallMonitoringEnabled = true;
    }

    public void disableStallMonitoring() {
        isStallMonitoringEnabled = false;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }


    // Drive methods that are sensor-agnostic

    /**
     * This method will apply the given left and right powers to the drivetrain until the given callback method returns True.
     * This method is blocking.
     */
    public void setPowerUntilTrue(double leftPower, double rightPower, Supplier<Boolean> supplier) {
        hw.getDrivetrain().setPowers(leftPower, rightPower);
        while(opMode.opModeIsActive() && supplier.get()) { Thread.yield(); }
        hw.getDrivetrain().haltDrive();
    }
}

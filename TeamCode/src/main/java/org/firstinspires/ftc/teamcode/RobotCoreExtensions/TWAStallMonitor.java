package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import java.util.Timer;
import java.util.TimerTask;

/*
    Filename: StallMonitor.java

    Description:
        Stall Monitor forms a crucial piece of the autonomous code for robot movement each season.
    Stall Monitor monitors the motor encoders of each drive motor during movements, and it stops the
    motors if the robot is trying to drive but not moving far enough. The robot checks its encoder
    counts over a time increment and stops the robot if the change in encoder counts is too low. This
    could occur when the robot collides with another object, and it protects our drive motors from
    possible damage from autonomous collisions.

    If a stall is detected the program performs a eStop and terminates the autonomous program.
    Some thought was given to just terminate the current instruction but if the last instruction was
    not performed successfully why perform more instructions.

    Use:
        Stall Monitor is used by each AutoDriver when any movement function is called in an autonomous
    program. This ensures that every predefined movement (squaring up to a line, driving with the
    gyro sensor, etc.) is being monitored.

    Requirements:
 *     - AutoDrive configured for stall monitor
 *     - Drive motors with encoders
 *
 *
 * Changelog:
 *     -Created by Jacob on 4/6/16.
 *     -Edited file description and documentation 7/25/17

 */

class TWAStallMonitor {
    TWAAutoDriver autoDriver;
    Timer stallTimer = new Timer();
    StallMonitorTask task;

    // taskFrequency - is how long it waits to check again after the initial check.

    private int taskFrequency = 1000;

    // taskDelay - Is the initial delay that happens when stall monitor first starts monitoring. This is only
    //              executed the first time for each movement

    private int taskDelay = 1000;

    protected TWAStallMonitor(TWAAutoDriver autoDriver) {
        this.autoDriver = autoDriver;
    }

    protected class StallMonitorTask extends TimerTask {
        private int previousLeftCounts;
        private int previousRightCounts;
        int leftThresholdConstant = 125;
        int rightThresholdConstant = 125;

        int getLeftThreshold() {
            return (int) (leftThresholdConstant);
        }

        int getRightThreshold() {
            return (int) (rightThresholdConstant);
        }

        private boolean isStallDetected() {
            boolean isStallDetected = false;

            // Left side previous counts - left current counts  compared to left threshold

            if (Math.abs(previousLeftCounts - autoDriver.hw.drivetrain.getLeftEncoderCount()) <= getLeftThreshold()) {
                isStallDetected = true;
            }

            // Right side previous counts - right current counts  compared to right threshold

            if (Math.abs(previousRightCounts - autoDriver.hw.drivetrain.getRightEncoderCount()) <= getRightThreshold()) {
                isStallDetected = true;
            }

            return isStallDetected;
        }

        @Override
        public void run() {

            // If stall detected perform eStop else update previous counts with current counts

            if (isStallDetected()) {
                autoDriver.eStop();
            } else {
                previousLeftCounts = (int) autoDriver.hw.drivetrain.getLeftEncoderCount();
                previousRightCounts = (int) autoDriver.hw.drivetrain.getRightEncoderCount();
            }
        }
    }

    protected void setTaskFrequency(int taskFrequency) {
        if (taskFrequency < 0) throw new IllegalArgumentException("The frequency must be >0");
        this.taskFrequency = taskFrequency;
    }

    protected void setTaskDelay(int taskDelay) {
        if (taskDelay < 0) throw new IllegalArgumentException("The delay must be >0");
        this.taskDelay = taskDelay;
    }

    protected void startMonitoring() {
        task = new StallMonitorTask();
        stallTimer.scheduleAtFixedRate(task, taskDelay, taskFrequency);
    }

    protected void stopMonitoring() {
        task.cancel();
        stallTimer.purge();
    }
}

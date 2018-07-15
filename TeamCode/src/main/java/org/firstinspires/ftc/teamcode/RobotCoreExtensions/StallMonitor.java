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

    If a stall is detected the program performs an eStop and terminates the autonomous program.
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
 *     -Cleaned up by Jacob on 7/14/18

 */

class StallMonitor {
    private final EmergencyStoppable eStoppable;
    private final Drivetrain drivetrain;
    private final Timer stallTimer = new Timer();
    private StallMonitorTask task;

    private int taskFrequency = 1000;
    private int taskDelay = 1000;

    protected StallMonitor(EmergencyStoppable eStoppable, Drivetrain drivetrain) {
        this.eStoppable = eStoppable;
        this.drivetrain = drivetrain;
    }

    protected interface EmergencyStoppable {
        void eStop();
    }

    protected void startMonitoring() {
        task = new StallMonitorTask();
        stallTimer.scheduleAtFixedRate(task, taskDelay, taskFrequency);
    }

    protected void stopMonitoring() {
        try {
            task.cancel();
            stallTimer.purge();
        } catch (RuntimeException ignored) {
            // Ignore all errors on this, especially NPE from uninstantiated tasks.
        }
    }

    protected class StallMonitorTask extends TimerTask {
        private int previousLeftCounts;
        private int previousRightCounts;
        private int leftThreshold = 125;
        private int rightThreshold = 125;

        private boolean isStallDetected() {
            return (Math.abs(previousLeftCounts - drivetrain.getLeftEncoderCount()) <= leftThreshold) ||
                    (Math.abs(previousRightCounts - drivetrain.getRightEncoderCount()) <= rightThreshold);
        }

        /**
         * If stall detected perform isMovementTerminated else update previous counts with current counts
         */
        @Override
        public void run() {
            if (isStallDetected()) {
                eStoppable.eStop();
            } else {
                previousLeftCounts = (int) drivetrain.getLeftEncoderCount();
                previousRightCounts = (int) drivetrain.getRightEncoderCount();
            }
        }
    }

    /**
     * Task Frequency is how long Stall Monitor waits to check again after the initial check.
     *
     * @param taskFrequency
     */
    protected void setTaskFrequency(int taskFrequency) {
        if (taskFrequency < 0) {
            throw new IllegalArgumentException("The frequency must be greater than 0");
        }
        this.taskFrequency = taskFrequency;
    }

    /**
     * Task Delay is the initial delay that happens when Stall Monitor first starts monitoring.
     * This is only executed the first time for each movement.
     *
     * @param taskDelay
     */
    protected void setTaskDelay(int taskDelay) {
        if (taskDelay < 0) {
            throw new IllegalArgumentException("The delay must be greater than 0");
        }
        this.taskDelay = taskDelay;
    }
}

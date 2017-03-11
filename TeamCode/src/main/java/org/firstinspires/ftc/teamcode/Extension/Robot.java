package org.firstinspires.ftc.teamcode.Extension;

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

    public void driveForward(int targetPosition, double power) {
        hw.gyroAutoDriver.driveStraightForwards(targetPosition, power);
    }

    public void driveBackward(int targetPosition, double power) {
        hw.gyroAutoDriver.driveStraightBackwards(targetPosition, power);
    }

    public void driveUntilLineUsingLeftODS(double lightValue, double power) {
        hw.opticalDistanceAutoDriver.driveUntilLine(hw.leftOpticalDistanceSensor, lightValue, power);
    }

    public void driveUntilLineUsingRightODS(double lightValue, double power) {
        hw.opticalDistanceAutoDriver.driveUntilLine(hw.rightOpticalDistanceSensor, lightValue, power);
    }

    public void driveForwardsUntilDistance(double distance, double power) {
        hw.rangeAutoDriver.driveForwardsUntilDistance(distance, power);
    }

    public void driveBackwardsUntilDistance(double distance, double power) {
        hw.rangeAutoDriver.driveBackwardsUntilDistance(distance, power);
    }

    public void wallFollowForward(double power, int encodercounts) {
        hw.rangeAutoDriver.wallFollowForwards(power, hw.sideRangeSensor.getUltrasonicRange(), encodercounts);
    }

    public void wallFollowBackward(double power, int encodercounts) {
        hw.rangeAutoDriver.wallFollowBackwards(power, hw.sideRangeSensor.getUltrasonicRange(), encodercounts);
    }

    public void turn(int target) {
        hw.gyroAutoDriver.turn(target);
    }

    public void pressRedSideBeacon(double power, int presstime) {
        if (hw.colorSensor.blue() >= 2) {
            hw.baconActivator.armUp();
            opMode.sleep(200);
        } else {
            hw.baconActivator.armPressing();
            opMode.sleep(200);
        }

        // TODO: Change this to odometric-based movement
        hw.drivetrain.setPowers(power, power);
        opMode.sleep(presstime);

        hw.drivetrain.haltDrive();
        hw.gyroSensor.resetZAxisIntegrator();
        hw.opMode.sleep(200);
        hw.drivetrain.resetMotorEncoders();
    }
    public void pressBlueSideBeacon(double power, int presstime) {
        if (hw.colorSensor.blue() >= 2) {
            hw.baconActivator.armPressing();
            opMode.sleep(200);
        } else {
            hw.baconActivator.armUp();
            opMode.sleep(200);
        }

        // TODO: Change this to odometric-based movement
        hw.drivetrain.setPowers(power, power);
        opMode.sleep(presstime);

        hw.drivetrain.haltDrive();
        hw.gyroSensor.resetZAxisIntegrator();
        hw.opMode.sleep(200);
        hw.drivetrain.resetMotorEncoders();
    }
}

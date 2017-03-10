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

    public void wallFollowForward(double power, int encodercounts) {
        hw.rangeAutoDriver.wallFollowForwards(power, hw.sideRangeSensor.getUltrasonicRange(), encodercounts);
    }

    public void wallFollowBackward(double power, double rangeCm, int encodercounts) {
        hw.rangeAutoDriver.wallFollowBackwards(power, rangeCm, encodercounts);
    }

    public void turn(int target) {
        hw.gyroAutoDriver.turn(target);
    }

    public void pressBeacon(double colorValue, double power) {
        if (hw.colorSensor.blue() >= colorValue) {
            hw.baconActivator.armUp();
            opMode.sleep(500);
        } else {
            hw.baconActivator.armPressing();
            opMode.sleep(500);
        }

        // TODO: Change this to odometric-based movement
        hw.drivetrain.setPowers(power, power);
        opMode.sleep(500);
        hw.drivetrain.haltDrive();

        opMode.sleep(200);
        hw.drivetrain.resetMotorEncoders();
    }
}

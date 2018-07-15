package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Jacob on 7/12/2015.
 */
public class TeleopDriver {
    private Drivetrain drivetrain;
    private float maxSpeed = 1.0f;
    private float minSpeed = 0.1f;

    public enum Direction {
        FORWARD,
        BACKWARD
    }

    public TeleopDriver(Drivable hw) {
        this.drivetrain = hw.getDrivetrain();
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(float minSpeed) {
        this.minSpeed = minSpeed;
    }

    public void tankDrive(Gamepad gamepad, Direction direction) {
        double leftPower = getScaledPowerFromGamepadStick(gamepad.left_stick_y);
        double rightPower = getScaledPowerFromGamepadStick(gamepad.right_stick_y);

        if (direction == Direction.FORWARD) {
            drivetrain.setPowers(leftPower, rightPower);
        } else {
            drivetrain.setPowers(-rightPower, -leftPower);
        }
    }

    private double getScaledPowerFromGamepadStick(float stick) {
        return -(Math.signum(stick) * ((Math.pow(stick, 2) * (maxSpeed - minSpeed)) + minSpeed));
    }

    @Override
    public String toString() {
        return String.format("max speed: %.2f\n%s", maxSpeed, drivetrain);
    }
}

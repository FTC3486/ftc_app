package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Jacob on 7/12/2015.
 */
public class TeleopDriver
{
    private OpMode opMode;
    private Drivetrain drivetrain;
    private float maxSpeed = 1.0f;
    private float minSpeed = 0.1f;
    public enum Direction
    {
        FORWARD,
        BACKWARD
    }

    public TeleopDriver(OpMode opMode, Drivetrain drivetrain)
    {
        this.opMode = opMode;
        this.drivetrain = drivetrain;
    }

    public float getMaxSpeed()
    {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed)
    {
        this.maxSpeed = maxSpeed;
    }

    public float getMinSpeed()
    {
        return minSpeed;
    }

    public void setMinSpeed(float minSpeed)
    {
        this.minSpeed = minSpeed;
    }

    public void tank_drive(Gamepad gamepad, Direction direction)
    {
        double left = get_scaled_power_from_gamepad_stick(gamepad.left_stick_y);
        double right = get_scaled_power_from_gamepad_stick(gamepad.right_stick_y);

        if(direction == Direction.FORWARD)
        {
            drivetrain.setPowers(left, right);
        }
        else
        {
            drivetrain.setPowers(-right, -left);
        }
    }
    public void half_speed_tank_drive(Gamepad gamepad, Direction direction)
    {
        double left = get_scaled_power_from_gamepad_stick(gamepad.left_stick_y)/2;
        double right = get_scaled_power_from_gamepad_stick(gamepad.right_stick_y)/2;

        if(direction == Direction.FORWARD)
        {
            drivetrain.setPowers(left, right);
        }
        else
        {
            drivetrain.setPowers(-right, -left);
        }
    }

    private double get_scaled_power_from_gamepad_stick(float stick)
    {
        return -(Math.signum(stick) * ((Math.pow(stick, 2) * (maxSpeed - minSpeed)) + minSpeed));
    }

    @Override
    public String toString() {
        return "max speed: " + String.format("%.2f", maxSpeed) + "\n" + drivetrain;
    }
}

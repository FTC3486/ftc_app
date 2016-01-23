package com.jacobamason.FTCRC_Extensions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Jacob on 7/12/2015.
 */
public class Drive
{
    private OpMode opMode;
    private float joystickDeadzone = 0.2f;
    private float maxSpeed = 1.0f;
    private float minSpeed = 0.1f;

    public Drive(OpMode opMode)
    {
        this.opMode = opMode;
        construct();
    }

    public Drive(OpMode opMode, float joystickDeadzone)
    {
        this.opMode = opMode;
        this.joystickDeadzone = joystickDeadzone;
        construct();
    }

    private void construct()
    {
        opMode.gamepad1.setJoystickDeadzone(joystickDeadzone);
        opMode.gamepad2.setJoystickDeadzone(joystickDeadzone);
    }

    public float getJoystickDeadzone()
    {
        return joystickDeadzone;
    }

    public void setJoystickDeadzone(float joystickDeadzone)
    {
        this.joystickDeadzone = joystickDeadzone;
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

    public void forward_tank_drive(DcMotor leftfront, DcMotor leftback, DcMotor rightfront, DcMotor rightback)
    {
        opMode.telemetry.addData("maxMotorSpeed", "max speed: " + String.format("%.2f", maxSpeed));

        float left = -opMode.gamepad1.left_stick_y;
        float right = -opMode.gamepad1.right_stick_y;

        opMode.telemetry.addData("left org pwr", "original left pwr: "
                + String.format("%.2f", left));

        if (opMode.gamepad1.left_stick_y != 0)
        {
            left = (float) (Math.signum(left) *
                    ((Math.pow(left, 2) * (maxSpeed - minSpeed)) + minSpeed));
        }

        if (opMode.gamepad1.right_stick_y != 0)
        {
            right = (float) (Math.signum(right) *
                    ((Math.pow(right, 2) * (maxSpeed - minSpeed)) + minSpeed));
        }

        opMode.telemetry.addData("left fnl pwr", "final left pwr: " + String.format("%.2f", left));

        rightfront.setPower(right);
        rightback.setPower(right);
        leftfront.setPower(left);
        leftback.setPower(left);
    }

    public void reverse_tank_drive(DcMotor leftfront, DcMotor leftback, DcMotor rightfront, DcMotor rightback)
    {
        opMode.telemetry.addData("maxMotorSpeed", "max speed: " + String.format("%.2f", maxSpeed));

        float left = -opMode.gamepad1.left_stick_y;
        float right = -opMode.gamepad1.right_stick_y;

        opMode.telemetry.addData("left org pwr", "original left pwr: "
                + String.format("%.2f", left));

        if (opMode.gamepad1.left_stick_y != 0)
        {
            left = (float) (Math.signum(left) *
                    ((Math.pow(left, 2) * (maxSpeed - minSpeed)) + minSpeed));
        }

        if (opMode.gamepad1.right_stick_y != 0)
        {
            right = (float) (Math.signum(right) *
                    ((Math.pow(right, 2) * (maxSpeed - minSpeed)) + minSpeed));
        }

        opMode.telemetry.addData("left fnl pwr", "final left pwr: " + String.format("%.2f", left));

        rightfront.setPower(-1 * left);
        rightback.setPower(-1* left);
        leftfront.setPower(-1 * right);
        leftback.setPower(-1 * right);
    }
}

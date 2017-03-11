package org.firstinspires.ftc.teamcode.Extension;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Matthew on 3/8/2017.
 */

public class RangeAutoDriver {
    private HardwareConfiguration hw;

    public RangeAutoDriver(HardwareConfiguration hw) {
        this.hw = hw;
    }

    public void wallFollowForwards(double power, double rangeCm, int encodercounts){
        double startPositionLeft = hw.drivetrain.getLeftEncoderCount();//Starting position
        double startPositionRight = hw.drivetrain.getRightEncoderCount();

        while(hw.drivetrain.getLeftEncoderCount() < encodercounts + startPositionLeft && hw.drivetrain.getRightEncoderCount() < encodercounts + startPositionRight && hw.opMode.opModeIsActive())  {
            double leftSpeed = power;
            double rightSpeed = power;

            double sideUltrasonicRange = hw.sideRangeSensor.getUltrasonicRange();

            if(sideUltrasonicRange > rangeCm)
            {
                rightSpeed = power - 0.005;
            }
            else if(sideUltrasonicRange < rangeCm)
            {
                leftSpeed = power - 0.005;
            }

            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);

            hw.drivetrain.setPowers(leftSpeed, rightSpeed);
        }
        hw.drivetrain.haltDrive();
        hw.gyroSensor.resetZAxisIntegrator();
        hw.opMode.sleep(200);
        hw.drivetrain.resetMotorEncoders();
    }

    public void wallFollowBackwards(double power, double rangeCm, int encodercounts){
        double startPositionLeft = hw.drivetrain.getLeftEncoderCount();//Starting position
        double startPositionRight = hw.drivetrain.getRightEncoderCount();

        while(hw.drivetrain.getLeftEncoderCount() > encodercounts + startPositionLeft && hw.drivetrain.getRightEncoderCount() > encodercounts + startPositionRight && hw.opMode.opModeIsActive())  {
            double leftSpeed = power;
            double rightSpeed = power;

            double sideUltrasonicRange = hw.sideRangeSensor.getUltrasonicRange();
            if(sideUltrasonicRange > rangeCm)
            {
                rightSpeed = power + 0.007;
            }
            else if(sideUltrasonicRange < rangeCm)
            {
                leftSpeed = power + 0.007;
            }

            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);

            hw.drivetrain.setPowers(leftSpeed, rightSpeed);
        }
        hw.drivetrain.haltDrive();
        hw.gyroSensor.resetZAxisIntegrator();
        hw.opMode.sleep(200);
        hw.drivetrain.resetMotorEncoders();
    }

    public void driveForwardsUntilDistance(double distance, double power) {
        while (hw.frontRangeSensor.getUltrasonicRange() > distance && hw.opMode.opModeIsActive()) {
            hw.drivetrain.setPowers(power, power);
        }
        hw.drivetrain.haltDrive();
        hw.gyroSensor.resetZAxisIntegrator();
        hw.opMode.sleep(200);
        hw.drivetrain.resetMotorEncoders();
    }

    public void driveBackwardsUntilDistance(double distance, double power) {
        while (hw.frontRangeSensor.getUltrasonicRange() < distance && hw.opMode.opModeIsActive()) {
            hw.drivetrain.setPowers(power, power);
        }
        hw.drivetrain.haltDrive();
        hw.gyroSensor.resetZAxisIntegrator();
        hw.opMode.sleep(200);
        hw.drivetrain.resetMotorEncoders();
    }
}

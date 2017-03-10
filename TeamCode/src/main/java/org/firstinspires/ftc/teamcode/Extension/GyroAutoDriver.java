package org.firstinspires.ftc.teamcode.Extension;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Owner_2 on 12/31/2016.
 * Edited by Matthew on 3/6/2017.
 */

public class GyroAutoDriver {
    private LinearOpMode opMode;
    private HardwareConfiguration hw;

    public GyroAutoDriver(LinearOpMode opMode, HardwareConfiguration hw) {
        this.opMode = opMode;
        this.hw = hw;
    }

    public void driveStraightForwards(int targetPosition, double power) {
        double leftSpeed; //Power to feed the motors
        double rightSpeed;

        double target = hw.gyroSensor.getIntegratedZValue();  //Starting direction
        double zAccumulated;
        double startPositionLeft = hw.drivetrain.getLeftEncoderCount();
        double startPositionRight = hw.drivetrain.getRightEncoderCount();

        while (hw.drivetrain.getLeftEncoderCount() < targetPosition + startPositionLeft
                && hw.drivetrain.getRightEncoderCount() < targetPosition + startPositionRight && opMode.opModeIsActive()) {
            zAccumulated = hw.gyroSensor.getIntegratedZValue();  //Current direction

            leftSpeed = power + (zAccumulated - target) / 20;  //Calculate speed for each side
            rightSpeed = power - (zAccumulated - target) / 20;  //See Gyro Straight video for detailed explanation

            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);

            hw.drivetrain.setPowers(leftSpeed, rightSpeed);
        }
        hw.drivetrain.haltDrive();
        opMode.sleep(200);
        hw.drivetrain.resetMotorEncoders();
    }

    public void driveStraightBackwards(int targetPosition, double power) {
        double leftSpeed; //Power to feed the motors
        double rightSpeed;

        double target = hw.gyroSensor.getIntegratedZValue();  //Starting direction
        double zAccumulated;
        double startPositionLeft = hw.drivetrain.getLeftEncoderCount();//Starting position
        double startPositionRight = hw.drivetrain.getRightEncoderCount();

        while (hw.drivetrain.getLeftEncoderCount() > targetPosition + startPositionLeft &&
                hw.drivetrain.getRightEncoderCount() > targetPosition + startPositionRight && opMode.opModeIsActive())
        {  //While we have not passed out intended distance
            zAccumulated = hw.gyroSensor.getIntegratedZValue();  //Current direction

            leftSpeed = power + (zAccumulated - target) / 20;  //Calculate speed for each side
            rightSpeed = power - (zAccumulated - target) / 20;  //See Gyro Straight video for detailed explanation

            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);

            hw.drivetrain.setPowers(leftSpeed, rightSpeed);
        }
        hw.drivetrain.haltDrive();
        opMode.sleep(200);
        hw.drivetrain.resetMotorEncoders();
    }

    public void turn(int target) {
        hw.gyroSensor.resetZAxisIntegrator();
        double gyroHeading = this.getAdjustedHeading();

        while(gyroHeading  < target - 1 || gyroHeading > target + 1 && opMode.opModeIsActive())
        {
            double power = ( (target - gyroHeading) / Math.abs(target)) * (1.0/4.0);

            power = Math.signum(power) * Range.clip(Math.abs(power), 0.05, 1.0);

            hw.drivetrain.setPowers(power, -power);
            gyroHeading = this.getAdjustedHeading();
        }
        hw.drivetrain.haltDrive();
    }

    private double getAdjustedHeading() {
        double sensorValue = hw.gyroSensor.getHeading();

        if(sensorValue > 180)
        {
            sensorValue -= 360;
        }
        return sensorValue;
    }
}

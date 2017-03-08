package org.firstinspires.ftc.teamcode.Extension;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Owner_2 on 12/31/2016.
 * Edited by Matthew on 3/6/2017.
 */

public class GyroAutoDriver {
    private LinearOpMode opMode;
    private ModernRoboticsI2cGyro gyroSensor;  //ModernRoboticsI2cGyro allows us to .getIntegratedZValue()
    private Drivetrain drivetrain;

    public GyroAutoDriver(LinearOpMode inputOpMode, String gyroSensorName, Drivetrain inputDrivetrain)
    {
        opMode = inputOpMode;
        gyroSensor = (ModernRoboticsI2cGyro) opMode.hardwareMap.gyroSensor.get(gyroSensorName);
        gyroSensor.calibrate();
        gyroSensor.resetZAxisIntegrator();
        drivetrain = inputDrivetrain;
    }

    public void calibrate()
    {
        while(gyroSensor.isCalibrating())
        {
            //Wait for calibration
        }
    }

    public void driveStraightForwards(int targetPosition, double power) {
        double leftSpeed; //Power to feed the motors
        double rightSpeed;

        double target = gyroSensor.getIntegratedZValue();  //Starting direction
        double zAccumulated;
        double startPositionLeft = drivetrain.getLeftEncoderCount();
        double startPositionRight = drivetrain.getRightEncoderCount();

        while (drivetrain.getLeftEncoderCount() < targetPosition + startPositionLeft
                && drivetrain.getRightEncoderCount() < targetPosition + startPositionRight && opMode.opModeIsActive()) {  //While we have not passed out intended distance
            zAccumulated = gyroSensor.getIntegratedZValue();  //Current direction

            leftSpeed = power + (zAccumulated - target) / 20;  //Calculate speed for each side
            rightSpeed = power - (zAccumulated - target) / 20;  //See Gyro Straight video for detailed explanation

            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);

            drivetrain.setPowers(leftSpeed, rightSpeed);
        }
        drivetrain.setPowers(0.0, 0.0);
    }

    public void driveStraightBackwards(int targetPosition, double power) {
        double leftSpeed; //Power to feed the motors
        double rightSpeed;

        double target = gyroSensor.getIntegratedZValue();  //Starting direction
        double zAccumulated;
        double startPositionLeft = drivetrain.getLeftEncoderCount();//Starting position
        double startPositionRight = drivetrain.getRightEncoderCount();

        while (drivetrain.getLeftEncoderCount() > targetPosition + startPositionLeft &&
               drivetrain.getRightEncoderCount() > targetPosition + startPositionRight && opMode.opModeIsActive())
        {  //While we have not passed out intended distance
            zAccumulated = gyroSensor.getIntegratedZValue();  //Current direction

            leftSpeed = power + (zAccumulated - target) / 20;  //Calculate speed for each side
            rightSpeed = power - (zAccumulated - target) / 20;  //See Gyro Straight video for detailed explanation

            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);

            drivetrain.setPowers(leftSpeed, rightSpeed);
        }
        drivetrain.setPowers(0.0, 0.0);
    }

    public void turn(int target) {
        gyroSensor.resetZAxisIntegrator();

        while(this.getAdjustedHeading()  != target)
        {
            double power = ( (target - this.getAdjustedHeading()) / Math.abs(target)) * (2.0/3.0);
            drivetrain.setPowers(power, -power);
        }
    }

    private double getAdjustedHeading() {
        double sensorValue = gyroSensor.getHeading();

        if(sensorValue > 180)
        {
            sensorValue -= 360;
        }
        return sensorValue;
    }
}

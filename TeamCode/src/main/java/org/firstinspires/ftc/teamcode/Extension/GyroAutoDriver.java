package org.firstinspires.ftc.teamcode.Extension;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Owner_2 on 12/31/2016.
 * Edited by Matthew on 3/6/2017.
 */

public class GyroAutoDriver {
    public DcMotor Left1 = null;
    public DcMotor Left2 = null;
    public DcMotor Right1 = null;
    public DcMotor Right2 = null;

    private ModernRoboticsI2cGyro gyroSensor;  //ModernRoboticsI2cGyro allows us to .getIntegratedZValue()

    public GyroAutoDriver(LinearOpMode opMode, String gyroSensorName)
    {
        gyroSensor = (ModernRoboticsI2cGyro) opMode.hardwareMap.gyroSensor.get(gyroSensorName);
        gyroSensor.calibrate();
        gyroSensor.resetZAxisIntegrator();
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
        double startPosition = Left2.getCurrentPosition();  //Starting position

        while (Left2.getCurrentPosition() < targetPosition + startPosition) {  //While we have not passed out intended distance
            zAccumulated = gyroSensor.getIntegratedZValue();  //Current direction

            leftSpeed = power + (zAccumulated - target) / 20;  //Calculate speed for each side
            rightSpeed = power - (zAccumulated - target) / 20;  //See Gyro Straight video for detailed explanation

            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);

            Left1.setPower(leftSpeed);
            Left2.setPower(leftSpeed);
            Right1.setPower(rightSpeed);
            Right2.setPower(rightSpeed);

            Left1.setPower(0);
            Left2.setPower(0);//Stop the motors
            Right1.setPower(0);
            Right2.setPower(0);
        }
    }

    public void driveStraightBackwards(int targetPosition, double power) {
        double leftSpeed; //Power to feed the motors
        double rightSpeed;

        double target = gyroSensor.getIntegratedZValue();  //Starting direction
        double zAccumulated;
        double startPositionLeft = Left2.getCurrentPosition();//Starting position
        double startPositionRight = Right2.getCurrentPosition();

        while (Left2.getCurrentPosition() > targetPosition + startPositionLeft &&
                Right2.getCurrentPosition()> targetPosition + startPositionRight)
        {  //While we have not passed out intended distance
            zAccumulated = gyroSensor.getIntegratedZValue();  //Current direction

            leftSpeed = power + (zAccumulated - target) / 20;  //Calculate speed for each side
            rightSpeed = power - (zAccumulated - target) / 20;  //See Gyro Straight video for detailed explanation

            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);

            Left1.setPower(leftSpeed);
            Left2.setPower(leftSpeed);
            Right1.setPower(rightSpeed);
            Right2.setPower(rightSpeed);
        }

        Right1.setPower(0);
        Right2.setPower(0);
        Left1.setPower(0);
        Left2.setPower(0);//Stop the motors
    }

    //This function turns a number of degrees compared to where the robot is. Positive numbers trn left.
    public void turn(int target) throws InterruptedException {
        turnAbsolute(target + gyroSensor.getIntegratedZValue());
    }

    //This function turns a number of degrees compared to where the robot was when the program started. Positive numbers trn left.
    public void turnAbsolute(int target) {
        double zAccumulated;
        zAccumulated = gyroSensor.getIntegratedZValue();  //Set variables to gyroSensor readings
        double turnSpeed = 0.15;

        while (Math.abs(zAccumulated - target) > 3) {  //Continue while the robot direction is further than three degrees from the target
            if (zAccumulated > target) {  //if gyroSensor is positive, we will turn right
                Left1.setPower(turnSpeed);
                Left2.setPower(turnSpeed);
                Right1.setPower(-turnSpeed);
                Right2.setPower(-turnSpeed);
            }

            if (zAccumulated < target) {  //if gyroSensor is positive, we will turn left
                Left1.setPower(-turnSpeed);
                Left2.setPower(-turnSpeed);
                Right1.setPower(turnSpeed);
                Right2.setPower(turnSpeed);
            }

            zAccumulated = gyroSensor.getIntegratedZValue();  //Set variables to gyroSensor readings

        }

        Left1.setPower(0);
        Left2.setPower(0);
        Right1.setPower(0);
        Right2.setPower(0);
    }
}

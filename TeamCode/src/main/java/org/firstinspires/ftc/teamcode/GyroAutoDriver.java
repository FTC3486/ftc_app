package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Owner_2 on 12/31/2016.
 */

public class GyroAutoDriver {
    public DcMotor Left1 = null;
    public DcMotor Left2 = null;
    public DcMotor Right1 = null;
    public DcMotor Right2 = null;

    private ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime timer = new ElapsedTime();

    int zAccumulated;  //Total rotation left/right
    int target = 0;  //Desired angle to turn to
    GyroSensor sensorGyro;  //General Gyro Sensor allows us to point to the sensor in the configuration file.
    ModernRoboticsI2cGyro mrGyro;  //ModernRoboticsI2cGyro allows us to .getIntegratedZValue()




    public void driveStraight(int duration, double power) {
        double leftSpeed; //Power to feed the motors
        double rightSpeed;

        double target = mrGyro.getIntegratedZValue();  //Starting direction
        double startPosition = Left2.getCurrentPosition();  //Starting position

        while (Left2.getCurrentPosition() < duration + startPosition) {  //While we have not passed out intended distance
            zAccumulated = mrGyro.getIntegratedZValue();  //Current direction

            leftSpeed = power + (zAccumulated - target) / 100;  //Calculate speed for each side
            rightSpeed = power - (zAccumulated - target) / 100;  //See Gyro Straight video for detailed explanation

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

    //This function turns a number of degrees compared to where the robot is. Positive numbers trn left.
    public void turn(int target) throws InterruptedException {
        turnAbsolute(target + mrGyro.getIntegratedZValue());
    }

    //This function turns a number of degrees compared to where the robot was when the program started. Positive numbers trn left.
    public void turnAbsolute(int target) {
        zAccumulated = mrGyro.getIntegratedZValue();  //Set variables to gyroSensor readings
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

            zAccumulated = mrGyro.getIntegratedZValue();  //Set variables to gyroSensor readings

        }

        Left1.setPower(0);
        Left2.setPower(0);
        Right1.setPower(0);
        Right2.setPower(0);

    }
}

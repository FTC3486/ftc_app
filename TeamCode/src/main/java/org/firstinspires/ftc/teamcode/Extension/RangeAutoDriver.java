package org.firstinspires.ftc.teamcode.Extension;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Matthew on 3/8/2017.
 */

public class RangeAutoDriver {
    private LinearOpMode opMode;
    private Drivetrain drivetrain;
    private I2cDevice sideSensor;
    private I2cDeviceSynch sideSensorReader;
    private I2cDevice frontSensor;
    private I2cDeviceSynch frontSensorReader;


    public RangeAutoDriver(LinearOpMode opMode, Drivetrain drivetrain, String frontSensor, String sideSensor)
    {
        this.opMode = opMode;
        this.drivetrain = drivetrain;

        this.frontSensor = opMode.hardwareMap.i2cDevice.get(frontSensor);
        this.frontSensorReader = new I2cDeviceSynchImpl(this.frontSensor, I2cAddr.create8bit(0x28), false);
        this.frontSensorReader.engage();

        this.sideSensor = opMode.hardwareMap.i2cDevice.get(sideSensor);
        this.sideSensorReader = new I2cDeviceSynchImpl(this.sideSensor, I2cAddr.create8bit(0x2a), false);
        this.sideSensorReader.engage();
    }

    public double getSideUltrasonicRange()
    {
        //Read 2 bytes at a time, starting at 0x04
        byte[] sideSensorCache = sideSensorReader.read(0x04, 2);
        //sideSensorCache[0] holds Ultrasonic Values; & 0xFF converts the values to be in the range 0-255;
        return sideSensorCache[0] & 0xFF;
    }

    public double getFrontUltrasonicRange()
    {
        //Read 2 bytes at a time, starting at 0x04
        byte[] frontSensorCache = frontSensorReader.read(0x04, 2);
        //frontSensorCache[0] holds Ultrasonic Values; & 0xFF converts the values to be in the range 0-255;
        return frontSensorCache[0] & 0xFF;
    }

    public void wallFollowForwards(double power, double RangeCm, int encodercounts){
        double startPositionLeft = drivetrain.getLeftEncoderCount();//Starting position
        double startPositionRight = drivetrain.getRightEncoderCount();

        while(drivetrain.getLeftEncoderCount() < encodercounts + startPositionLeft && drivetrain.getRightEncoderCount()< encodercounts + startPositionRight && opMode.opModeIsActive())  {
            double leftSpeed = power;
            double rightSpeed = power;

            double sideUltrasonicRange = this.getSideUltrasonicRange();

            if(sideUltrasonicRange > RangeCm)
            {
                rightSpeed = power - 0.005;
            }
            else if(sideUltrasonicRange < RangeCm)
            {
                leftSpeed = power - 0.005;
            }

            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);

            drivetrain.setPowers(leftSpeed, rightSpeed);
        }
    }

    public void wallFollowBackwards(double power, double RangeCm, int encodercounts){
        double startPositionLeft = drivetrain.getLeftEncoderCount();//Starting position
        double startPositionRight = drivetrain.getRightEncoderCount();

        while(drivetrain.getLeftEncoderCount() > encodercounts + startPositionLeft && drivetrain.getRightEncoderCount() > encodercounts + startPositionRight && opMode.opModeIsActive())  {
            double leftSpeed = power;
            double rightSpeed = power;

            double sideUltrasonicRange = this.getSideUltrasonicRange();
            if(sideUltrasonicRange > RangeCm)
            {
                leftSpeed = power - 0.005;
            }
            else if(sideUltrasonicRange < RangeCm)
            {
                rightSpeed = power - 0.005;
            }

            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);

            drivetrain.setPowers(leftSpeed, rightSpeed);
        }
    }
}

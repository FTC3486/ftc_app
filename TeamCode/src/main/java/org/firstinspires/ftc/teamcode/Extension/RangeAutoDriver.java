package org.firstinspires.ftc.teamcode.Extension;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Matthew on 3/8/2017.
 */

public class RangeAutoDriver {
    private LinearOpMode opMode;
    private HardwareConfiguration hw;

    public RangeAutoDriver(LinearOpMode opMode, HardwareConfiguration hw)
    {
        this.opMode = opMode;
        this.hw = hw;
    }

    public void wallFollowForwards(double power, double RangeCm, int encodercounts){
        double startPositionLeft = hw.drivetrain.getLeftEncoderCount();//Starting position
        double startPositionRight = hw.drivetrain.getRightEncoderCount();

        while(hw.drivetrain.getLeftEncoderCount() < encodercounts + startPositionLeft && hw.drivetrain.getRightEncoderCount()< encodercounts + startPositionRight && opMode.opModeIsActive())  {
            double leftSpeed = power;
            double rightSpeed = power;

            double sideUltrasonicRange = hw.sideRangeSensor.getUltrasonicRange();

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

            hw.drivetrain.setPowers(leftSpeed, rightSpeed);
        }
    }

    public void wallFollowBackwards(double power, double RangeCm, int encodercounts){
        double startPositionLeft = hw.drivetrain.getLeftEncoderCount();//Starting position
        double startPositionRight = hw.drivetrain.getRightEncoderCount();

        while(hw.drivetrain.getLeftEncoderCount() > encodercounts + startPositionLeft && hw.drivetrain.getRightEncoderCount() > encodercounts + startPositionRight && opMode.opModeIsActive())  {
            double leftSpeed = power;
            double rightSpeed = power;

            double sideUltrasonicRange = hw.sideRangeSensor.getUltrasonicRange();
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

            hw.drivetrain.setPowers(leftSpeed, rightSpeed);
        }
    }
}

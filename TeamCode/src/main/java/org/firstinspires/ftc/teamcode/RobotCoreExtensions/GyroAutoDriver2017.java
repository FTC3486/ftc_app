package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class GyroAutoDriver2017 extends TWAAutoDriver
{
    public GyroAutoDriver2017(AutoTWAHardwareConfiguration hw) {
        super(hw);
        angles = hw.adafruitIMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.YZX, AngleUnit.DEGREES);
    }
    public Orientation angles;


    private double getAdjustedHeading() {

        //double sensorValue = hw.adafruitIMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        if(angles.secondAngle == 180)
        {
            angles.secondAngle -= 360;
        }
        return angles.secondAngle;
    }

    public void driveStraightForwards(float distance, double power)
    {
        setupMotion("Driving straight forwards using the gyro sensor.");
        double target = angles.firstAngle;
        hw.drivetrain.setPowers(power, power);
        int checkCount = 0;
        while (hw.drivetrain.getLeftEncoderCount() < hw.drivetrain.convertInchesToEncoderCounts(distance)
                && hw.drivetrain.getRightEncoderCount() < hw.drivetrain.convertInchesToEncoderCounts(distance) && hw.opMode.opModeIsActive()
                )
        {
            checkCount++;
            float currentHeading = angles.firstAngle;
            hw.drivetrain.setPowers(power + (currentHeading - target) / 50,
                                    power - (currentHeading - target) / 50);
            hw.opMode.telemetry.addData("Times checked:", checkCount);
            hw.opMode.telemetry.addData("Gyro Target:", target);
            hw.opMode.telemetry.addData("Gyro Heading:", currentHeading);
            hw.opMode.telemetry.addData("Right Encoder", hw.drivetrain.getRightEncoderCount());
            hw.opMode.telemetry.addData("Left Encoder", hw.drivetrain.getLeftEncoderCount());
            hw.opMode.telemetry.update();
        }
        hw.drivetrain.haltDrive();

        endMotion();
    }

    public void driveStraightBackwards(float distance, double power)
    {
        setupMotion("Driving straight backwards using the gyro sensor.");
        double target = angles.firstAngle;  //Starting direction

        while (hw.drivetrain.getLeftEncoderCount() > -hw.drivetrain.convertInchesToEncoderCounts(distance) &&
                hw.drivetrain.getRightEncoderCount() > -hw.drivetrain.convertInchesToEncoderCounts(distance) /*&&
               hw.opMode.opModeIsActive()*/
                )
        {
            float currentHeading = angles.firstAngle;  //Current direction
            hw.drivetrain.setPowers(-power + (currentHeading - target) / 50,
                                    -power - (currentHeading - target) / 50);
        }
        endMotion();
    }

    public void turn(int target)
    {

       // target = target *-1;
        setupMotion("Turning using gyro.");
        double initialValue = angles.firstAngle;  //Starting direction
        double gyroTarget = initialValue + 90;

        if(gyroTarget > 180)
        {
            gyroTarget -= 360;
        }
        else if(gyroTarget < -180)
        {
            gyroTarget += 360;
        }
        boolean shouldBeRunning = true;

        while(shouldBeRunning)
        {
            float currentHeading = angles.firstAngle;  //Current direction
            hw.drivetrain.setPowers(-power + (currentHeading - gyroTarget) / 50,
                    -power - (currentHeading - gyroTarget) / 50);
        }
        endMotion();
    }
}

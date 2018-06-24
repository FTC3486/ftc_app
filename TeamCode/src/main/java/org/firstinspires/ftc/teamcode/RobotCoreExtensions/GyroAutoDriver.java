package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.util.Range;

public class GyroAutoDriver extends AutoDriver
{
    public GyroAutoDriver(HardwareConfiguration hw) {
        super(hw);
    }

    private double getAdjustedHeading() {
        double sensorValue = hw.gyroSensor.getHeading();

        if(sensorValue > 180)
        {
            sensorValue -= 360;
        }
        return sensorValue;
    }

    public void driveStraightForwards(float distance, double power)
    {
        setupMotion("Driving straight forwards using the gyro sensor.");
        double target = hw.gyroSensor.getIntegratedZValue();
        hw.drivetrain.setPowers(power, power);
        int checkCount = 0;
        while (hw.drivetrain.getLeftEncoderCount() < hw.drivetrain.convertInchesToEncoderCounts(distance)
                && hw.drivetrain.getRightEncoderCount() < hw.drivetrain.convertInchesToEncoderCounts(distance)
                && hw.opMode.opModeIsActive())
        {
            checkCount++;
            int currentHeading = hw.gyroSensor.getIntegratedZValue();
            hw.drivetrain.setPowers(power + (currentHeading - target) / 50,
                                    power - (currentHeading - target) / 50);
            hw.opMode.telemetry.addData("Times checked:", checkCount);
            hw.opMode.telemetry.addData("Gyro Target:", target);
            hw.opMode.telemetry.addData("Gyro Heading:", currentHeading);
            hw.opMode.telemetry.update();
        }
        endMotion();
    }

    public void driveStraightBackwards(float distance, double power)
    {
        setupMotion("Driving straight backwards using the gyro sensor.");
        double target = hw.gyroSensor.getIntegratedZValue();  //Starting direction

        while (hw.drivetrain.getLeftEncoderCount() > -hw.drivetrain.convertInchesToEncoderCounts(distance) &&
                hw.drivetrain.getRightEncoderCount() > -hw.drivetrain.convertInchesToEncoderCounts(distance) &&
                hw.opMode.opModeIsActive())
        {
            int currentHeading = hw.gyroSensor.getIntegratedZValue();  //Current direction
            hw.drivetrain.setPowers(-power + (currentHeading - target) / 50,
                                    -power - (currentHeading - target) / 50);
        }
        endMotion();
    }

    public void turn(int target)
    {
        setupMotion("Turning using gyro.");
        double gyroHeading = this.getAdjustedHeading();

        while(gyroHeading  < target - 1 || gyroHeading > target + 1 && hw.opMode.opModeIsActive())
        {
            double power = ( (target - gyroHeading) / Math.abs(target)) * (1.0/4.0);

            power = Math.signum(power) * Range.clip(Math.abs(power), 0.05, 1.0);

            hw.drivetrain.setPowers(power, -power);
            gyroHeading = this.getAdjustedHeading();
        }
        endMotion();
    }
}

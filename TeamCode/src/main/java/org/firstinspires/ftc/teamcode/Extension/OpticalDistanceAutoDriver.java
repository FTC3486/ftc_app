package org.firstinspires.ftc.teamcode.Extension;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.teamcode.Extension.HardwareConfiguration;

/**
 * Created by Owner_2 on 12/31/2016.
 * Edited by Matthew on 3/6/2017.
 */

public class OpticalDistanceAutoDriver {
    private HardwareConfiguration hw;

    public OpticalDistanceAutoDriver(HardwareConfiguration hw)
    {
        this.hw = hw;
    }

    public void driveUntilLine(OpticalDistanceSensor opticalDistanceSensor, double lightValue, double power) {
        while (opticalDistanceSensor.getLightDetected() < lightValue && hw.opMode.opModeIsActive()) {
            hw.drivetrain.setPowers(power, power);
        }
        hw.drivetrain.haltDrive();
        hw.gyroSensor.resetZAxisIntegrator();
        hw.opMode.sleep(200);
        hw.drivetrain.resetMotorEncoders();
    }
}
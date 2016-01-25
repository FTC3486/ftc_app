package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.GyroSensor;


/**
 * Created by Matthew on 8/11/2015.
 */
public class GyroTest
        extends OpMode{
    GyroSensor gyro;

    @Override
    public void init() {
        gyro = hardwareMap.gyroSensor.get("gyro");
        gyro.calibrate();
    }

    @Override
    public void loop() {
        telemetry.addData("Rotation", gyro.getHeading());
        telemetry.addData("Raw X", gyro.rawX());
        telemetry.addData("Raw Y", gyro.rawY());
    }
}

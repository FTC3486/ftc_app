package com.qualcomm.ftcrobotcontroller.opmodes;
import com.FTC3486.FTCRC_Extensions.Drive;
import com.FTC3486.FTCRC_Extensions.GamepadWrapper;
import com.FTC3486.FTCRC_Extensions.ExtendedServo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;


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

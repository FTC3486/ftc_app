package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by Owner_2 on 12/31/2016.
 */
@Autonomous(name="SensorTest", group="Autonomus")
public class SensorTest extends LinearOpMode {
    ColorSensor colorSensor;
    OpticalDistanceSensor left_ods;
    OpticalDistanceSensor right_ods;
    GyroSensor gyro;
    ModernRoboticsI2cRangeSensor range;
    BaconActivator baconActivator;

    @Override
    public void runOpMode() {
        colorSensor = hardwareMap.colorSensor.get("Beacon Color");
        range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "Range");
        gyro = hardwareMap.gyroSensor.get("gyroSensor");
        left_ods = hardwareMap.opticalDistanceSensor.get("Left ods");
        right_ods = hardwareMap.opticalDistanceSensor.get("Right ods");
        baconActivator = new BaconActivator("Bacon Activator", hardwareMap);

        telemetry.update();

        colorSensor.enableLed(false);
        baconActivator.sensorScanning();
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.addData("Raw Left", left_ods.getRawLightDetected());
            telemetry.addData("Left ODS reading", left_ods.getLightDetected());
            telemetry.addData("Raw Right", right_ods.getRawLightDetected());
            telemetry.addData("Right ODS reading", right_ods.getLightDetected());
            telemetry.addData("raw ultrasonic", range.rawUltrasonic());
            telemetry.addData("raw optical", range.rawOptical());
            telemetry.addData("cm optical", "%.2f cm", range.cmOptical());
            telemetry.addData("cm", "%.2f cm", range.getDistance(DistanceUnit.CM));
            telemetry.update();

        }
    }
}

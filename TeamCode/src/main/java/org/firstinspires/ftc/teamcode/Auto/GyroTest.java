package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Extension.HardwareConfiguration;

@Autonomous(name = "GyroTest", group = "BlueAutonomus")

public class GyroTest extends LinearOpMode {
    HardwareConfiguration mammut = new HardwareConfiguration(this);

    @Override
    public void runOpMode() throws InterruptedException {
        mammut.init();
        waitForStart();

        mammut.gyroAutoDriver.driveStraightForwards(5600, 1);
        sleep(200);
        mammut.drivetrain.resetMotorEncoders();

        while (mammut.left_ods.getLightDetected() < 0.06 && opModeIsActive()) {
            mammut.drivetrain.setPowers(0.2, 0.2);
        }
        mammut.drivetrain.haltDrive();
        sleep(200);
        mammut.drivetrain.resetMotorEncoders();

        mammut.gyroAutoDriver.driveStraightForwards(300, 0.5);
        mammut.drivetrain.haltDrive();
        sleep(200);
        mammut.drivetrain.resetMotorEncoders();

        mammut.gyroAutoDriver.turn(-75);

        mammut.baconActivator.sensorScanning();
        sleep(200);

        while (mammut.rangeAutoDriver.getFrontUltrasonicRange() > 25 && opModeIsActive()) {
            mammut.drivetrain.setPowers(0.3, 0.3);
        }
        mammut.drivetrain.haltDrive();
        mammut.drivetrain.resetMotorEncoders();
        sleep(200);

        if (mammut.colorSensor.blue() >= 2) {
            mammut.baconActivator.armUp();
            sleep(500);
            mammut.drivetrain.setPowers(0.2, 0.2);
            sleep(600);
            mammut.drivetrain.haltDrive();
        } else {
            mammut.baconActivator.armPressing();
            sleep(500);
            mammut.drivetrain.setPowers(0.2, 0.2);
            sleep(500);
            mammut.drivetrain.haltDrive();
        }
        mammut.drivetrain.resetMotorEncoders();

        mammut.gyroAutoDriver.driveStraightBackwards(-200, -0.5);
        mammut.drivetrain.resetMotorEncoders();
        sleep(100);

        mammut.gyroAutoDriver.turn(-85);
        mammut.drivetrain.resetMotorEncoders();
        sleep(100);

        mammut.rangeAutoDriver.wallFollowForwards(0.25, mammut.rangeAutoDriver.getSideUltrasonicRange(), 3000);
        mammut.drivetrain.haltDrive();

        while (mammut.left_ods.getLightDetected() < 0.06 && opModeIsActive()) {
            mammut.drivetrain.setPowers(0.2, 0.2);
        }
        mammut.drivetrain.haltDrive();
        sleep(200);
        mammut.drivetrain.resetMotorEncoders();

        mammut.gyroAutoDriver.turn(90);

        mammut.drivetrain.setPowers(-0.3, -0.3);
        sleep(200);

        mammut.baconActivator.sensorScanning();

        while (mammut.rangeAutoDriver.getFrontUltrasonicRange() > 25 && opModeIsActive()) {
            mammut.drivetrain.setPowers(0.3, 0.3);
        }
        mammut.drivetrain.haltDrive();
        mammut.drivetrain.resetMotorEncoders();
        sleep(200);

        if (mammut.colorSensor.blue() >= 2) {
            mammut.baconActivator.armUp();
            sleep(500);
            mammut.drivetrain.setPowers(0.2, 0.2);
            sleep(600);
            mammut.drivetrain.haltDrive();
        } else {
            mammut.baconActivator.armPressing();
            sleep(500);
            mammut.drivetrain.setPowers(0.2, 0.2);
            sleep(500);
            mammut.drivetrain.haltDrive();
        }
        mammut.drivetrain.resetMotorEncoders();
    }
}


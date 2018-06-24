package org.firstinspires.ftc.teamcode.OpModes.Auto;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Robot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Matthew on 7/1/2017.
 */

@Autonomous(name = "Test Auto", group = "TestAutonomous")
public class TestAuto extends LinearOpMode {
    Robot mammut = new Robot(this);

    @Override
    public void runOpMode() throws InterruptedException {
        mammut.init();
        waitForStart();

        float initialGyroReading = mammut.hw.adafruitIMU.getAngularOrientation().firstAngle;
        mammut.hw.drivetrain.setPowers(-0.2, 0.2);

        int loopCounter = 0;
        while(mammut.hw.adafruitIMU.getAngularOrientation().firstAngle - initialGyroReading < 90)
        {
            loopCounter++;
            telemetry.addData("Gyro Reading", mammut.hw.adafruitIMU.getAngularOrientation().firstAngle - initialGyroReading);
            telemetry.addData("Counter", loopCounter);
            telemetry.update();
        }
        mammut.hw.drivetrain.haltDrive();

        telemetry.addData("Initial Reading", initialGyroReading);
        telemetry.addData("Final Reading", mammut.hw.adafruitIMU.getAngularOrientation().firstAngle);
        telemetry.addData("Difference", initialGyroReading - mammut.hw.adafruitIMU.getAngularOrientation().firstAngle);
        telemetry.update();
        sleep(5000);

        float turnAngle = mammut.hw.adafruitIMU.getAngularOrientation().firstAngle - initialGyroReading;
        float correctionAngle = turnAngle - 90;
        initialGyroReading = mammut.hw.adafruitIMU.getAngularOrientation().firstAngle;
        mammut.hw.drivetrain.setPowers(0.2, -0.2);
        while(initialGyroReading - mammut.hw.adafruitIMU.getAngularOrientation().firstAngle < correctionAngle);
        mammut.hw.drivetrain.haltDrive();

        telemetry.addData("Initial Reading", initialGyroReading);
        telemetry.addData("Final Reading", mammut.hw.adafruitIMU.getAngularOrientation().firstAngle);
        telemetry.addData("Difference", initialGyroReading - mammut.hw.adafruitIMU.getAngularOrientation().firstAngle);
        telemetry.update();
        sleep(15000);
    }
}
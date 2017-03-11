package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Extension.Robot;

@Autonomous(name = "Two Beacons Red", group = "RedAutonomus")
public class TwoBeaconsRed extends LinearOpMode {
    private Robot mammut = new Robot(this);

    @Override
    public void runOpMode() throws InterruptedException {
        mammut.init();
        waitForStart();

        mammut.driveForward(2800, 1);
        mammut.driveUntilLineUsingLeftODS(0.06, 0.2);
        mammut.driveForward(250, 0.5);
        mammut.turn(-63);

        mammut.hw.baconActivator.sensorScanning();
        sleep(500);

        mammut.driveForwardsUntilDistance(25, 0.3);
        mammut.pressRedSideBeacon(0.2, 700);

        mammut.driveBackward(-300, -0.5);
        mammut.turn(-87);
        mammut.driveBackward(-2500, -0.75);
        mammut.driveUntilLineUsingLeftODS(0.06, -0.2);
        mammut.driveForward(100, 0.5);
        mammut.turn(83);
        mammut.driveBackwardsUntilDistance(25, -0.3);

        mammut.hw.baconActivator.sensorScanning();
        sleep(500);

        mammut.driveForwardsUntilDistance(25, 0.3);
        mammut.pressRedSideBeacon(0.2, 700);
        mammut.driveBackward(-3000, -0.5);
    }
}


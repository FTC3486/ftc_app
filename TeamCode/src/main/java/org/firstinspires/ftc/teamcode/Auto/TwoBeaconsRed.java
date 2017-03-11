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
        mammut.driveForward(300, 0.5);
        mammut.turn(-63);

        mammut.hw.baconActivator.sensorScanning();
        sleep(200);

        mammut.driveForwardsUntilDistance(25, 0.3);
        mammut.pressRedSideBeacon(0.2, 500);
        mammut.driveBackward(-200, -0.5);
        mammut.turn(-85);
        /*mammut.wallFollowBackward(-0.25, -3000);
        mammut.driveUntilLineUsingLeftODS(0.06, 0.2);
        mammut.turn(90);

        // TODO: Change this to odometric-based movement
        mammut.hw.drivetrain.setPowers(-0.3, -0.3);
        sleep(200);

        mammut.hw.baconActivator.sensorScanning();
        mammut.driveForwardsUntilDistance(25, 0.3);
        mammut.pressRedSideBeacon(0.2,700);*/
    }
}


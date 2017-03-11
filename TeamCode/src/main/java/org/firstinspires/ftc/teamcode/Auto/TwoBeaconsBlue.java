package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Extension.Robot;

@Autonomous(name = "Two Beacons Blue", group = "BlueAutonomus")
public class TwoBeaconsBlue extends LinearOpMode {
    private Robot mammut = new Robot(this);

    @Override
    public void runOpMode() throws InterruptedException {
        mammut.init();
        waitForStart();


        mammut.driveForward(2800, 0.5);
        mammut.driveUntilLineUsingRightODS(0.06, 0.2);
        mammut.driveBackward(-100, -0.5);
        mammut.turn(65);

        mammut.hw.baconActivator.sensorScanning();
        sleep(200);

        mammut.driveForwardsUntilDistance(25, 0.3);
        mammut.pressBlueSideBeacon(0.2, 700);

        mammut.driveBackward(-300, -0.5);


        mammut.turn(-87);
        mammut.driveForward(2500, 0.75);
        mammut.driveUntilLineUsingLeftODS(0.06, 0.2);
        //mammut.driveBackward(-100, -0.5);
        mammut.turn(90);
        mammut.driveBackwardsUntilDistance(25, -0.3);

        mammut.hw.baconActivator.sensorScanning();
        sleep(500);

        mammut.driveForwardsUntilDistance(25, 0.3);
        mammut.pressBlueSideBeacon(0.2, 700);
        mammut.driveBackward(-3000, -0.5);
    }
}


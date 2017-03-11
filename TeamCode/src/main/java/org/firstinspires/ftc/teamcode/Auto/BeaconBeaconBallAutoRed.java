package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Extension.Robot;


/**
 * Created by Owner_2 on 12/31/2016.
 */
@Autonomous(name = "Press Inner Beacon, Outer Beacon and Score balls Red", group = "RedAutonomus")
@Disabled
public class BeaconBeaconBallAutoRed extends LinearOpMode {
    Robot mammut = new Robot(this);
    @Override
    public void runOpMode() throws InterruptedException {
        mammut.init();
        waitForStart();

        mammut.driveForward(2800, 0.5);
        mammut.driveUntilLineUsingRightODS(0.06, 0.2);
        mammut.driveForward(250, 0.5);
        mammut.hw.encoderAutoDriver.driveToTarget(0.3, -7.4, 7.4, 10);

        mammut.hw.baconActivator.sensorScanning();
        sleep(200);

        mammut.driveForwardsUntilDistance(25, 0.3);
        mammut.pressRedSideBeacon(0.2, 500);
        mammut.driveBackward(-500, -0.5);
        mammut.hw.encoderAutoDriver.driveToTarget(0.5,9.5,-9.5,3);
        mammut.driveForward(2000, 0.5);
        mammut.driveUntilLineUsingLeftODS(0.06,0.3);
        mammut.driveForward(300, 0.5);
        mammut.hw.encoderAutoDriver.driveToTarget(0.3, -10.8, 10.8, 4);

        mammut.hw.baconActivator.sensorScanning();
        sleep(200);

        mammut.driveForwardsUntilDistance(25, 0.3);
        mammut.pressRedSideBeacon(0.2, 500);
        mammut.driveBackward(-500, -0.5);
        mammut.hw.encoderAutoDriver.driveToTarget(0.5, 3.8, -3.8, 3);
        mammut.hw.baconActivator.armUp();
    }
}



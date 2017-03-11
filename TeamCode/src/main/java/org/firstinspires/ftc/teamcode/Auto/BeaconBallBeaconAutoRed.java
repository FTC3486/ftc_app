package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Extension.Robot;

/**
 * Created by Owner_2 on 12/31/2016.
 */
@Autonomous(name ="Beacon,Score balls, Beacon - Red", group = "RedAutonomus")
@Disabled
public class BeaconBallBeaconAutoRed extends LinearOpMode {
    Robot mammut = new Robot(this);

    @Override
    public void runOpMode() throws InterruptedException {
        mammut.init();
        waitForStart();

        mammut.hw.encoderAutoDriver.driveToTarget(0.5, 6.5 ,-6.5, 3);
        mammut.driveForward(2200, 0.5);
        mammut.driveUntilLineUsingRightODS(0.06, 0.2);
        mammut.driveForward(250, 0.5);
        mammut.hw.encoderAutoDriver.driveToTarget(0.3, -7.2, 7.2, 10);

        mammut.hw.baconActivator.sensorScanning();
        sleep(200);

        mammut.driveForwardsUntilDistance(25, 0.3);
        mammut.pressRedSideBeacon(0.2, 500);
        mammut.hw.baconActivator.armUp();
    }
}



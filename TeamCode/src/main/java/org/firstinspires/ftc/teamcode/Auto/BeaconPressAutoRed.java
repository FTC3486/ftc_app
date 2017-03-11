package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Extension.Robot;

/**
 * Created by Owner_2 on 12/31/2016.
 */
@Autonomous(name = "Press Beacon and Score balls Red", group = "RedAutonomus")

public class BeaconPressAutoRed extends LinearOpMode {
    Robot mammut = new Robot(this);

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
        mammut.driveBackward(-2350, -0.5);

        while (mammut.hw.accelerator.acceleratorPower < 1 && opModeIsActive()) {
            mammut.hw.accelerator.rampup();
        }
        mammut.hw.accelerator.run();

        mammut.hw.troughGate.openGate();
        sleep(2000);
        mammut.hw.troughGate.closeGate();
        mammut.hw.baconActivator.armUp();
    }
}



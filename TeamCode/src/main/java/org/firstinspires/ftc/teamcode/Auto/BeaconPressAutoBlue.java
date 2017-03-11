package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Extension.Robot;

/**
 * Created by Owner_2 on 1/12/2017.
 */
@Autonomous(name = "Press Beacon and Score balls Blue", group = "BlueAutonomus")

public class BeaconPressAutoBlue extends LinearOpMode {
    Robot mammut = new Robot(this);

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
        mammut.driveBackward(-2350, -0.5);

        while (mammut.hw.accelerator.acceleratorPower < 1 && opModeIsActive()) {
            mammut.hw.accelerator.rampup();
        }

        mammut.hw.accelerator.run();
        sleep(100);

        mammut.hw.troughGate.openGate();
        sleep(2000);

        mammut.hw.troughGate.closeGate();
        mammut.hw.baconActivator.armUp();
    }
}




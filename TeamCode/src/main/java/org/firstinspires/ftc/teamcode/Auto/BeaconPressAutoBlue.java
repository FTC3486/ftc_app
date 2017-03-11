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
        mammut.driveUntilLineUsingLeftODS(0.06, 0.2);
        mammut.driveBackward(-100, -0.5);
        mammut.turn(75);

        mammut.hw.baconActivator.sensorScanning();
        sleep(200);

        mammut.driveForwardsUntilDistance(25, 0.3);
        mammut.pressBlueSideBeacon(0.2, 500);
        mammut.driveBackward(-2100, -0.5);
        //driveStraightBackwards(-2100, -0.5);
        /*encoderAutoDriver.driveToTarget(0.5, -32, -32, 10);
        driveTrain.haltDrive();
        driveTrain.resetMotorEncoders();*/
        while (mammut.hw.accelerator1.accleratorPower < 1 && opModeIsActive()) {
            mammut.hw.accelerator1.rampup();
        }

        mammut.hw.accelerator1.run();
        sleep(100);

        mammut.hw.troughGate.openGate();
        sleep(2000);

        mammut.hw.troughGate.closeGate();
        mammut.hw.baconActivator.armUp();
    }
}




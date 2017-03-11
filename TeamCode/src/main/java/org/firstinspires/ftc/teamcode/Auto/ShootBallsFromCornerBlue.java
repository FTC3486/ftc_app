package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Extension.Robot;

/**
 * Created by Owner_2 on 1/12/2017.
 */
@Autonomous(name = "Score balls From Corner Blue", group = "BlueAutonomus")

public class ShootBallsFromCornerBlue extends LinearOpMode {
    Robot mammut = new Robot(this);

    @Override
    public void runOpMode() throws InterruptedException {
        mammut.init();
        waitForStart();

        sleep(15000);
        mammut.driveBackward(-3000, -0.5);

        while (mammut.hw.accelerator.acceleratorPower < 1 && opModeIsActive()) {
            mammut.hw.accelerator.rampup();
        }
        mammut.hw.accelerator.run();

        mammut.hw.troughGate.openGate();
        sleep(3000);
        mammut.hw.troughGate.closeGate();
        mammut.hw.accelerator.stop();
        mammut.driveBackward(-500, -0.5);
        mammut.hw.baconActivator.armUp();
    }
}


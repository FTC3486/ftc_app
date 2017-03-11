package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Extension.Robot;


/**
 * Created by Owner_2 on 12/1/2016.
 */
@Autonomous(name = "Two Particles and Capball RedAuto", group = "Autonomus2016")
public class ScoreParticles_and_KnockCapball_RedAuto extends LinearOpMode{
    Robot mammut = new Robot(this);

    @Override
    public void runOpMode() {

        waitForStart();

        while (mammut.hw.accelerator.acceleratorPower <1) {
            mammut.hw.accelerator.rampup();
        }
        mammut.hw.accelerator.run();
        mammut.hw.encoderAutoDriver.driveToTarget(0.5, -52, -52, 5.0);

        mammut.hw.troughGate.openGate();
        sleep(2000);

        mammut.hw.accelerator.stop();
        mammut.hw.encoderAutoDriver.driveToTarget(0.5, -8, 8, 3.0);
        mammut.hw.encoderAutoDriver.driveToTarget(0.5, 10, -10, 3.0);
        mammut.hw.encoderAutoDriver.driveToTarget(0.5, -9, -9, 3.0);
    }
}
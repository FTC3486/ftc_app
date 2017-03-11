package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Extension.Robot;

/**
 * Created by Owner_2 on 1/12/2017.
 */
@Autonomous(name = "Score balls From Corner Blue", group = "BlueAutonomus")

@Disabled
public class ShootBallsFromCornerBlue extends LinearOpMode {
    Robot mammut = new Robot(this);

    @Override
    public void runOpMode() throws InterruptedException {
        mammut.init();
        waitForStart();

        mammut.driveBackward(-3400, -0.5);

        while (mammut.hw.accelerator.acceleratorPower < 1) {
            mammut.hw.accelerator.rampup();
        }
        mammut.hw.accelerator.run();

        mammut.hw.troughGate.openGate();
        sleep(2000);
        mammut.hw.troughGate.closeGate();
        sleep(2000);
        mammut.hw.encoderAutoDriver.driveToTarget(0.5, -22, -22, 3);
        mammut.hw.baconActivator.armUp();
    }
}


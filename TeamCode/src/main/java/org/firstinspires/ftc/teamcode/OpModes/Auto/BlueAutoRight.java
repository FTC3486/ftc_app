package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.AutoTWA;

/**
 * Created by Matthew on 7/1/2017.
 */

@Autonomous(name = "Blue Right Auto", group = "BlueAuto")
public class BlueAutoRight extends LinearOpMode {
    AutoTWA twaRobot = new AutoTWA(this);

    @Override
    public void runOpMode() throws InterruptedException {
        twaRobot.init();
        twaRobot.hw.jewelArm.autoInit();

        waitForStart();
        twaRobot.hw.drivetrain.resetMotorEncoders();

         twaRobot.hw.jewelArm.down();
        sleep(1000);
        twaRobot.hw.glyphGrabber.gripBottom(true, twaRobot.hw.glyphSpinner.isFlipped());
        sleep(200);
        twaRobot.hw.glyphLift.lift();
        sleep(500);
        twaRobot.hw.glyphLift.stop();
        sleep(200);

//Detect the Jewel color and follow through with appropriate movement to score the jewel
        if (twaRobot.hw.jewelArm.jewelColor.red() <= 10 && twaRobot.hw.jewelArm.jewelColor.blue() <= 10){
            telemetry.addData("Red Color reading", twaRobot.hw.jewelArm.jewelColor.red());
            telemetry.addData("Blue Color reading", twaRobot.hw.jewelArm.jewelColor.blue());
            telemetry.update();

            sleep(200);
            twaRobot.hw.jewelArm.up();
            sleep(200);
            twaRobot.hw.drivetrain.haltDrive();
            sleep(200);
            twaRobot.hw.gyroAutoDriver.driveStraightBackwards(30, 0.2);
            twaRobot.hw.drivetrain.haltDrive();
        }
         else if (
                 twaRobot.hw.jewelArm.jewelColor.red() > twaRobot.hw.jewelArm.jewelColor.blue()
        ){
            telemetry.addData("Red Color reading", twaRobot.hw.jewelArm.jewelColor.red());
            telemetry.addData("Blue Color reading", twaRobot.hw.jewelArm.jewelColor.blue());
            telemetry.update();
            //twaRobot.hw.encoderAutoDriver.driveToDistanceBackwards(-10);
            twaRobot.hw.gyroAutoDriver.driveStraightBackwards(5, 0.2);
            sleep(200);
            twaRobot.hw.jewelArm.up();
            sleep(200);
            twaRobot.hw.drivetrain.haltDrive();
            sleep(200);
            twaRobot.hw.gyroAutoDriver.driveStraightBackwards(24, 0.2);
            twaRobot.hw.drivetrain.haltDrive();
        }else if (
                twaRobot.hw.jewelArm.jewelColor.blue() > twaRobot.hw.jewelArm.jewelColor.red()
                ){
            telemetry.addData("Red Color reading", twaRobot.hw.jewelArm.jewelColor.red());
            telemetry.addData("Blue Color reading", twaRobot.hw.jewelArm.jewelColor.blue());
            telemetry.update();

            twaRobot.hw.gyroAutoDriver.driveStraightForwards(5, 0.2);

            sleep(200);
            twaRobot.hw.jewelArm.up();
            sleep(200);
            twaRobot.hw.drivetrain.haltDrive();
            sleep(200);
            twaRobot.hw.gyroAutoDriver.driveStraightBackwards(45, 0.2);
            twaRobot.hw.drivetrain.haltDrive();
        }
        //Rotate and score Glyph in Cryptobox
        twaRobot.hw.encoderAutoDriver.spinRight(10, -10);
        sleep(200);
        twaRobot.hw.gyroAutoDriver.driveStraightForwards(356, 0.5);
        sleep(200);
        twaRobot.hw.glyphGrabber.gripBottom(false, twaRobot.hw.glyphSpinner.isFlipped());
        sleep(200);
        twaRobot.hw.gyroAutoDriver.driveStraightBackwards(3, 0.5);
        twaRobot.hw.drivetrain.haltDrive();


    }
}
package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.AutoTWA;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.TWA;

/**
 * Created by Matthew on 7/1/2017.
 */

@Autonomous(name = "Jewel Blue Auto", group = "BlueAuto")
public class JewelAutoBlue extends LinearOpMode {
    AutoTWA twaRobot = new AutoTWA(this);


    //double redminusblue = twaRobot.hw.jewelArm.jewelColor.red() - twaRobot.hw.jewelArm.jewelColor.blue();
    //double blueminusred = twaRobot.hw.jewelArm.jewelColor.blue() - twaRobot.hw.jewelArm.jewelColor.red();

    @Override
    public void runOpMode() throws InterruptedException {
        twaRobot.init();
        twaRobot.hw.jewelArm.autoInit();

        waitForStart();
        twaRobot.hw.drivetrain.resetMotorEncoders();

         twaRobot.hw.jewelArm.down();
        sleep(1000);


        if (twaRobot.hw.jewelArm.jewelColor.red() <= 10 && twaRobot.hw.jewelArm.jewelColor.blue() <= 10){
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
                 //twaRobot.hw.jewelArm.jewelColor.red() >= 30 && twaRobot.hw.jewelArm.jewelColor.blue() < 30
        ){
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
                //twaRobot.hw.jewelArm.jewelColor.blue() >= 30 && twaRobot.hw.jewelArm.jewelColor.red() < 30
                ){
            twaRobot.hw.gyroAutoDriver.driveStraightForwards(5, 0.2);

            sleep(200);
            twaRobot.hw.jewelArm.up();
            sleep(200);
            twaRobot.hw.drivetrain.haltDrive();
            sleep(200);
            twaRobot.hw.gyroAutoDriver.driveStraightBackwards(50, 0.2);
            twaRobot.hw.drivetrain.haltDrive();

        }

       //twaRobot.hw.encoderAutoDriver.spinLeft(-4, 4);

        //twaRobot.hw.gyroAutoDriver.driveStraightForwards(48, 0.2);
        //twaRobot.hw.encoderAutoDriver.driveLeftSideToDistance(-9);
        //twaRobot.hw.encoderAutoDriver.driveRightSideToDistance(9);
    }
}
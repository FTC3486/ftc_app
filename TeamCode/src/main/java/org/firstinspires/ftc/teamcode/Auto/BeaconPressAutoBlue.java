package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Extension.EncoderAutoDriver;
import org.firstinspires.ftc.teamcode.Extension.GyroAutoDriver;
import org.firstinspires.ftc.teamcode.Extension.HardwareConfiguration;
import org.firstinspires.ftc.teamcode.Extension.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.BaconActivator;
import org.firstinspires.ftc.teamcode.Subsystems.CapballHolder;
import org.firstinspires.ftc.teamcode.Subsystems.Column;
import org.firstinspires.ftc.teamcode.Extension.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.ParticleAcclerator;
import org.firstinspires.ftc.teamcode.Subsystems.Pickup;
import org.firstinspires.ftc.teamcode.Subsystems.TroughGate;
import org.firstinspires.ftc.teamcode.Subsystems.TuskGate;

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
        mammut.pressBeacon(2, 0.2);
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




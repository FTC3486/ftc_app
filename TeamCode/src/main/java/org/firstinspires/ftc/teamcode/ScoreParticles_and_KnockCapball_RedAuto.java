package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Owner_2 on 12/1/2016.
 */
@Autonomous(name = "Two Particles and Capball RedAuto", group = "Autonomus2016")
public class ScoreParticles_and_KnockCapball_RedAuto extends LinearOpMode{
    private ElapsedTime     runtime = new ElapsedTime();
    Drivetrain drivetrain = new Drivetrain();
    ParticleAcclerator accelerator1;
    ParticleAcclerator accelerator2;
    Pickup pickup;
    TroughGate troughGate;
    Column column;
    TuskGate tuskGate;
    CapballHolder capballHolder;
    BaconActivator baconActivator;

    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;
    static final double     DRIVE_GEAR_REDUCTION    = 0.8 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.5;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {
        drivetrain.init(hardwareMap);
        pickup = new Pickup("Pickup", hardwareMap);
        troughGate = new TroughGate("Trough Gate", hardwareMap);
        accelerator1 = new ParticleAcclerator("Accelerator 1", hardwareMap);
        accelerator2 = new ParticleAcclerator("Accelerator 2", hardwareMap);
        column = new Column("Column", hardwareMap);
        tuskGate = new TuskGate("Tusk Gate", hardwareMap);
        capballHolder = new CapballHolder("Capball Holder", hardwareMap);
        baconActivator = new BaconActivator("Bacon Activator", hardwareMap);
        accelerator1.accleratorPower = 0;
        accelerator2.accleratorPower = 0;
        baconActivator.armDown();
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        drivetrain.Left1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drivetrain.Left2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drivetrain.Right1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drivetrain.Right2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();

        drivetrain.Left1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drivetrain.Left2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drivetrain.Right1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drivetrain.Right2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Path0", "Starting at %7d :%7d",
                drivetrain.Left1.getCurrentPosition(),
                drivetrain.Left2.getCurrentPosition(),
                drivetrain.Right1.getCurrentPosition(),
                drivetrain.Right2.getCurrentPosition());
        telemetry.update();


        waitForStart();

        while (accelerator1.accleratorPower <1 && accelerator2.accleratorPower <1) {
            accelerator1.rampup();
            accelerator2.rampup();
        }
        accelerator1.run();
        accelerator2.run();
        encoderDrive(DRIVE_SPEED, -33, -33, 3.0);
        sleep(250);
        troughGate.openGate();
        sleep(2000);
        accelerator1.stop();
        accelerator2.stop();
        encoderDrive(TURN_SPEED, -8, 8, 3.0);
        sleep(500);
        encoderDrive(TURN_SPEED, 10, -10, 3.0);
        sleep(500);
        encoderDrive(DRIVE_SPEED, -9, -9, 3.0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }


    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = drivetrain.Left2.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = drivetrain.Right2.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            drivetrain.Left1.setTargetPosition(newLeftTarget);
            drivetrain.Left2.setTargetPosition(newLeftTarget);
            drivetrain.Right1.setTargetPosition(newRightTarget);
            drivetrain.Right2.setTargetPosition(newRightTarget);


            // Turn On RUN_TO_POSITION
            drivetrain.Left1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            drivetrain.Left2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            drivetrain.Right1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            drivetrain.Right2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            drivetrain.Left1.setPower(Math.abs(speed));
            drivetrain.Left2.setPower(Math.abs(speed));
            drivetrain.Right1.setPower(Math.abs(speed));
            drivetrain.Right2.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (drivetrain.Left1.isBusy() && drivetrain.Left2.isBusy() && drivetrain.Right1.isBusy() && drivetrain.Right2.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        drivetrain.Left1.getCurrentPosition(),
                        drivetrain.Left2.getCurrentPosition(),
                        drivetrain.Right1.getCurrentPosition(),
                        drivetrain.Right2.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            drivetrain.Left1.setPower(0);
            drivetrain.Left2.setPower(0);
            drivetrain.Right1.setPower(0);
            drivetrain.Right2.setPower(0);

            // Turn off RUN_TO_POSITION
            drivetrain.Left1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            drivetrain.Left2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            drivetrain.Right1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            drivetrain.Right2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }

    }
}
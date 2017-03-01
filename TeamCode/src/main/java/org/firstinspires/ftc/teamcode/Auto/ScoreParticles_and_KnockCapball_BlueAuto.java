package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.BaconActivator;
import org.firstinspires.ftc.teamcode.Subsystems.CapballHolder;
import org.firstinspires.ftc.teamcode.Subsystems.Column;
import org.firstinspires.ftc.teamcode.Extension.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.ParticleAcclerator;
import org.firstinspires.ftc.teamcode.Subsystems.Pickup;
import org.firstinspires.ftc.teamcode.Subsystems.TroughGate;
import org.firstinspires.ftc.teamcode.Subsystems.TuskGate;

/**
 * Created by Owner_2 on 12/1/2016.
 */
@Autonomous(name = "Two Particles and Capball BlueAuto", group = "Autonomus2016")
public class ScoreParticles_and_KnockCapball_BlueAuto extends LinearOpMode{
    private ElapsedTime     runtime = new ElapsedTime();

    ParticleAcclerator accelerator1;
    ParticleAcclerator accelerator2;
    Pickup pickup;
    TroughGate troughGate;
    Column column;
    TuskGate tuskGate;
    CapballHolder capballHolder;
    BaconActivator baconActivator;
    Drivetrain driveTrain;

    public DcMotor Left1 = null;
    public DcMotor Left2 = null;
    public DcMotor Right1 = null;
    public DcMotor Right2 = null;



    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;
    static final double     DRIVE_GEAR_REDUCTION    = 0.8 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.5;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {
        Left1 = hardwareMap.dcMotor.get("Left 1");
        Left2 = hardwareMap.dcMotor.get("Left 2");
        Right1 = hardwareMap.dcMotor.get("Right 1");
        Right2 = hardwareMap.dcMotor.get("Right 2");

        Left1.setDirection(DcMotor.Direction.REVERSE);
        Left2.setDirection(DcMotor.Direction.REVERSE);
        Right1.setDirection(DcMotor.Direction.FORWARD);
        Right2.setDirection(DcMotor.Direction.FORWARD);

        driveTrain = new Drivetrain.Builder()
                .addLeftMotor(Left1)
                .addLeftMotorWithEncoder(Left2)
                .addRightMotor(Right1)
                .addRightMotorWithEncoder(Right2)
                .build();
        pickup = new Pickup("Pickup", hardwareMap);
        troughGate = new TroughGate("Trough Gate", hardwareMap);
        accelerator1 = new ParticleAcclerator("Accelerator 1", hardwareMap);
        accelerator2 = new ParticleAcclerator("Accelerator 2", hardwareMap);
        column = new Column("Column 1", "Column 2", hardwareMap);
        tuskGate = new TuskGate("Tusk Gate", hardwareMap);
        capballHolder = new CapballHolder("Capball Holder", hardwareMap);
        baconActivator = new BaconActivator("Bacon Activator", hardwareMap);
        accelerator1.accleratorPower = 0;
        accelerator2.accleratorPower = 0;
        baconActivator.armDown();
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        driveTrain.resetMotorEncoders();

        telemetry.addData("Path0", "Starting at %7d :%7d",
                Left1.getCurrentPosition(),
                Left2.getCurrentPosition(),
                Right1.getCurrentPosition(),
                Right2.getCurrentPosition());
        telemetry.update();


        waitForStart();

        while (accelerator1.accleratorPower <1 && accelerator2.accleratorPower <1) {
            accelerator1.rampup();
            accelerator2.rampup();
        }
        accelerator1.run();
        accelerator2.run();
        driveTrain.resetMotorEncoders();
        encoderDrive(DRIVE_SPEED, -33, -33, 3.0);

        sleep(250);
        troughGate.openGate();
        sleep(2000);
        accelerator1.stop();
        accelerator2.stop();
        driveTrain.resetMotorEncoders();
        encoderDrive(TURN_SPEED, 8, -8, 3.0);
        sleep(500);
        driveTrain.resetMotorEncoders();
        encoderDrive(TURN_SPEED, -10, 10, 3.0);
        sleep(500);
        driveTrain.resetMotorEncoders();
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
            newLeftTarget = Left2.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = Right2.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            Left1.setTargetPosition(newLeftTarget);
            Left2.setTargetPosition(newLeftTarget);
            Right1.setTargetPosition(newRightTarget);
            Right2.setTargetPosition(newRightTarget);


            // Turn On RUN_TO_POSITION
            Left1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Left2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Right1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Right2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            Left1.setPower(Math.abs(speed));
            Left2.setPower(Math.abs(speed));
            Right1.setPower(Math.abs(speed));
            Right2.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (Left1.isBusy() && Left2.isBusy() && Right1.isBusy() && Right2.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        Left1.getCurrentPosition(),
                        Left2.getCurrentPosition(),
                        Right1.getCurrentPosition(),
                        Right2.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            Left1.setPower(0);
            Left2.setPower(0);
            Right1.setPower(0);
            Right2.setPower(0);

            // Turn off RUN_TO_POSITION
            Left1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Left2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Right1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Right2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }
}
package org.firstinspires.ftc.teamcode.Extension;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Matthew on 3/6/2017.
 */

public class EncoderAutoDriver {
    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;
    static final double     DRIVE_GEAR_REDUCTION    = 0.8 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    private ElapsedTime runtime = new ElapsedTime();
    private LinearOpMode opMode;
    private Drivetrain drivetrain;

    public EncoderAutoDriver(LinearOpMode inputOpMode, Drivetrain inputDrivetrain)
    {
        opMode = inputOpMode;
        drivetrain = inputDrivetrain;
    }

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        while(opMode.opModeIsActive()) {
            // Determine new target position, and pass to motor controller
            newLeftTarget = (int) drivetrain.getLeftEncoderCount() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = (int) drivetrain.getRightEncoderCount() + (int) (rightInches * COUNTS_PER_INCH);

            drivetrain.setTargetPosition(newLeftTarget, newRightTarget);
            drivetrain.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();

            drivetrain.setPowers(Math.abs(speed), Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (drivetrain.isBusy())) {
                //Wait for completion
            }

            drivetrain.setPowers(0.0,0.0);

            // Turn off RUN_TO_POSITION
            drivetrain.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}

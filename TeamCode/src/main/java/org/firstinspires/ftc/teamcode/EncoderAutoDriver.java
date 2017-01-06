package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Jacob on 2/24/16.
 */
public class EncoderAutoDriver extends AutoDriver {

    public EncoderAutoDriver(LinearOpMode linearOpMode, Drivetrain driveTrain) {
        super(linearOpMode, driveTrain);
    }

    @Override
    public AutoDriver drive_forward(int inches) {
        return null;
    }

    @Override
    public AutoDriver drive_backward(int encoderCount) {
        driveTrain.resetMotorEncoders();
        driveTrain.setPowers(-power, -power);
        while (driveTrain.getLeftEncoderCount() > encoderCount &&
                driveTrain.getRightEncoderCount() > encoderCount &&
                opMode.opModeIsActive()) {}
        driveTrain.haltDrive();
        return null;
    }

    @Override
    public AutoDriver turn_clockwise(int degrees) {
        return null;
    }

    @Override
    public AutoDriver turn_counterclockwise(int degrees) {
        return null;
    }
}

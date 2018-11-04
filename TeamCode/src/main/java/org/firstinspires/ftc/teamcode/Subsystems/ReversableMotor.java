package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Initializable;

public class ReversableMotor implements Initializable {
    private final DcMotor motor;
    private final double defaultPower;

    private enum ReversableMotorState {
        RUNNING,
        REVERSING,
        STOPPED,
    }

    private ReversableMotorState reversableMotorState;

    public ReversableMotor(DcMotor motor, double defaultPower) {
        this.motor = motor;
        this.defaultPower = defaultPower;
    }

    @Override
    public void initialize() {
        stop();
    }

    public void run() {
        run(defaultPower);
    }

    public void run(double power) {
        motor.setPower(power);
        reversableMotorState = ReversableMotorState.RUNNING;
    }

    public void stop() {
        motor.setPower(0);
        reversableMotorState = ReversableMotorState.STOPPED;
    }

    public void reverse() {
        reverse(-defaultPower);
    }

    public void reverse(double power) {
        motor.setPower(power);
        reversableMotorState = ReversableMotorState.REVERSING;
    }

    @Override
    public String toString() {
        switch (reversableMotorState) {
            case RUNNING:
                return "Running";

            case REVERSING:
                return "Running in Reverse";

            case STOPPED:
                return "Stopped";

            default:
                return "Unknown";

        }


    }

}

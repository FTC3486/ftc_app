package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Initializable;

public class ReversableMotor implements Initializable {
    private DcMotor motor;
    private final double power;

    private enum ReversableMotorState {
        RUNNING,
        REVERSING,
        STOPPED,
    }

    ReversableMotorState reversableMotorState;

    public ReversableMotor(DcMotor motor, double power) {
        this.motor = motor;
        this.power = power;
    }

    @Override
    public void initialize() {
        stop();
    }

    public void run() {
        motor.setPower(power);
        reversableMotorState = ReversableMotorState.RUNNING;
    }

    public void stop() {
        motor.setPower(0);
        reversableMotorState = ReversableMotorState.STOPPED;
    }

    public void reverse() {
        motor.setPower(-power);
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

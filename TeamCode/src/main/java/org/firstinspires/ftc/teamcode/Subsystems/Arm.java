package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Initializable;

public class Arm implements Initializable {
    private final Servo armServo;
    private final double startingPosition;
    private final double upPosition;
    private final double downPosition;

    private enum ArmState {
        INITIALIZED,
        EXTENDING,
        EXTENDED,
        RETRACTING,
        RETRACTED,
    }

    private ArmState armState;

    public Arm(Servo armServo, double initializedPosition, double extendedPosition, double retractedPosition) {
        this.armServo = armServo;
        this.startingPosition = initializedPosition;
        this.upPosition = extendedPosition;
        this.downPosition = retractedPosition;
    }

    @Override
    public void initialize() {
        armServo.setPosition(startingPosition);
        armState = ArmState.INITIALIZED;
    }

    public void fullyExtend() {
        armServo.setPosition(upPosition);
        armState = ArmState.EXTENDED;
    }

    public void fullyRetract() {
        armServo.setPosition(downPosition);
        armState = ArmState.RETRACTED;
    }

    public void extend() {
        armServo.setPosition(armServo.getPosition() + 0.01);
        armState = ArmState.EXTENDING;
    }

    public void retract() {
        armServo.setPosition(armServo.getPosition() - 0.01);
        armState = ArmState.RETRACTING;
    }

    @Override
    public String toString() {
        switch (armState) {
            case INITIALIZED:
                return "Initialized";

            case EXTENDING:
                return "Extending";

            case EXTENDED:
                return "Fully Extended";

            case RETRACTING:
                return "Retracting";

            case RETRACTED:
                return "Fully Retracted";

            default:
                return "Unknown";
        }
    }
}
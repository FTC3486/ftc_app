package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by JacobAMason on 11/3/18.
 *
 * Scaling factor should be something relatively small (e.g.: 0.01)
 */
public class SpeedServo implements Initializable {
    private final Servo servo;
    private final double initialPosition;
    private final double scalingFactor;

    private double targetPosition;  // Used for telemetry

    public SpeedServo(Servo servo, double initialPosition, double scalingFactor) {
        this.servo = servo;
        this.initialPosition = initialPosition;
        this.scalingFactor = scalingFactor;
    }

    @Override
    public void initialize() {
        servo.setPosition(initialPosition);
    }

    public void run(float speed) {
        this.targetPosition = Math.min(1.0, Math.max(0.20,
                servo.getPosition() - (scalingFactor * speed)
        ));
        servo.setPosition(targetPosition);
    }

    @Override
    public String toString() {
        return String.format("scalingFactor=%s, targetPosition=%s", scalingFactor, targetPosition);
    }
}

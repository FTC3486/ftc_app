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
        servo.setPosition(Math.min(1.0, Math.max(0.0,
                servo.getPosition() + (scalingFactor * speed)
        )));
    }
}

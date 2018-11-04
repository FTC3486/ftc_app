package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.hardware.Servo;

/**
 *
 * Filename: ContinuousServo.java
 *
 * Description:
 *     This class contains the methods to use Continuous Servo.
 *
 *     NOTE: If you are looking for FIRST's built-in Continuous Servo implementation, it's called
 *     CRServo
 *
 *     While most servos go to a specific location based on the signal received, the Continuous
 *     servo rotates at a specific speed based on that signal.  Its speed is fully proportional
 *     meaning that the further the signal deviates from the center (1500usec), the faster the
 *     servo will rotate.
 *
 *     Normally for a Continuous Servo a positive signal of 0 to < .5 signal would turn the servo
 *     backwards and >.5 to 1 signal would turn the servo forward.
 *
 *     This method uses positive signals to go forward and negative signals to go backwards
 *     to simplify programing.
 *
 * Methods:
 *      ContinuousServo
 *      setPower - 0 to 1 signals the servo to go forward. 0 to -1 signals the servo to go backwards.
 *
 * Example: Rover.hardwareConfiguration.continuousServo.setPower(0.5);

 *
 * Requirements:
 *     - Continuous Servos
 *
 * Changelog:
 *     -Created by Matthew on 2/2/2016.
 *     -Edited file description and documentation 7/25/17

 * .
 */
public class ContinuousServo {
    private Servo servo;

    public ContinuousServo(Servo servo)
    {
       this.servo = servo;
    }

    // power divided by 2 plus .5 will always give a signal of .5 to 1

    public void setPower(double power) {
        servo.setPosition(power/2 + 0.5);
    }

}

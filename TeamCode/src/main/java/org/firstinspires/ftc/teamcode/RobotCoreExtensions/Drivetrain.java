/**
 * Filename: Drivetrain.java
 *
 * Description:
 *     This class makes a drivetrain out of any number of motors. This class gives
 *     information about the motors such as encoder counts, and it is also used to
 *     control the motors (for example, setPowers())
 *
 * Methods:
 *     - haltDrive
 *     - setMode - Not used
 *     - setTargetPosition
 *     - setPowers
 *     - convertInchesToEncoderCounts
 *     - getLeftEncoderCount
 *     - getRightEncoderCount()
 *     - resetMotorEncoder
 *     - toString
 *
 *  *
 * Example:
 *
 * robot.hardwareConfiguration.drivetrain.getLeftEncoderCount();
 *
 * Requirements:
 *     - Drive motors with encoders
 *
 *
 * Changelog:
 *     -Created a long time ago in a far away place when men used stone and chisel to code.
 *     -Edited file description and documentation 7/24/17
 */

package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.LinkedList;
import java.util.List;

public class Drivetrain {
    private double wheelDiameter;
    private double gearRatio;
    private int encoderCountsPerDriverGearRotation;
    private List<DcMotor> allMotorsWithEncoders;
    private List<DcMotor> leftMotors;
    private List<DcMotor> rightMotors;
    private List<DcMotor> leftMotorsWithEncoders;
    private List<DcMotor> rightMotorsWithEncoders;
    private double leftPower;
    private double rightPower;

    private Drivetrain(Builder builder) {
        this.wheelDiameter = builder.wheelDiameter;
        this.gearRatio = builder.gearRatio;
        this.encoderCountsPerDriverGearRotation = builder.encoderCountsPerDriverGearRotation;
        this.leftMotorsWithEncoders = builder.leftMotorsWithEncoders;
        this.rightMotorsWithEncoders = builder.rightMotorsWithEncoders;
        this.leftMotors = builder.leftMotors;
        this.leftMotors.addAll(builder.leftMotorsWithEncoders);
        this.rightMotors = builder.rightMotors;
        this.rightMotors.addAll(builder.rightMotorsWithEncoders);
        this.allMotorsWithEncoders = new LinkedList<>();
        this.allMotorsWithEncoders.addAll(builder.leftMotorsWithEncoders);
        this.allMotorsWithEncoders.addAll(builder.rightMotorsWithEncoders);

        for (DcMotor motor : this.leftMotors) {
            motor.setZeroPowerBehavior(builder.zeroPowerBehavior);
        }

        for (DcMotor motor : this.rightMotors) {
            motor.setZeroPowerBehavior(builder.zeroPowerBehavior);
        }
    }
    // Important configuration required to match the drive train - Wheel size, gear ration and type of motors.

    public static class Builder {
        private double wheelDiameter = 4.31; // Adjusted as a correction factor
        private double gearRatio = 1.2; // Adjusted as a correction factor
        // 1120 is the number for the AndyMark allMotors. Tetrix Motors are 1440 PPR
        private int encoderCountsPerDriverGearRotation = 1120;
        private final List<DcMotor> leftMotors = new LinkedList<>();
        private final List<DcMotor> rightMotors = new LinkedList<>();
        private final List<DcMotor> leftMotorsWithEncoders = new LinkedList<>();
        private final List<DcMotor> rightMotorsWithEncoders = new LinkedList<>();
        private DcMotor.ZeroPowerBehavior zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE;


        public Builder setWheelDiameter(double wheelDiameter) {
            this.wheelDiameter = wheelDiameter;
            return this;
        }

        public Builder setGearRatio(double gearRatio) {
            this.gearRatio = gearRatio;
            return this;
        }

        public Builder setEncoderCountsPerDriverGearRotation(int encoderCountsPerDriverGearRotation) {
            this.encoderCountsPerDriverGearRotation = encoderCountsPerDriverGearRotation;
            return this;
        }

        public Builder setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
            this.zeroPowerBehavior = zeroPowerBehavior;
            return this;
        }

        public Builder addLeftMotor(DcMotor leftMotor) {
            leftMotors.add(leftMotor);
            return this;
        }

        public Builder addLeftMotorWithEncoder(DcMotor leftMotor) {
            leftMotorsWithEncoders.add(leftMotor);
            return this;
        }

        public Builder addRightMotor(DcMotor rightMotor) {
            rightMotors.add(rightMotor);
            return this;
        }

        public Builder addRightMotorWithEncoder(DcMotor rightMotor) {
            rightMotorsWithEncoders.add(rightMotor);
            return this;
        }

        public Drivetrain build() {
            return new Drivetrain(this);
        }
    }

    // Stops the robot by setting all drive train motors to 0 power.

    public void haltDrive() {
        setPowers(0.0, 0.0);
    }

    // Not currently used

    public void setMode(DcMotor.RunMode runMode) {
        for (DcMotor motor : allMotorsWithEncoders) {
            motor.setMode(runMode);
        }
    }

    // Sets Target Position for left and right sides of the drive train.

    public void setTargetPosition(int leftTargetPosition, int rightTargetPosition) {
        for (DcMotor leftMotor : leftMotorsWithEncoders) {
            leftMotor.setTargetPosition(leftTargetPosition);
        }

        for (DcMotor rightMotor : rightMotorsWithEncoders) {
            rightMotor.setTargetPosition(rightTargetPosition);
        }
    }

    // Sets Power for left and right sides of the drive train.

    public void setPowers(double leftPower, double rightPower) {
        this.leftPower = leftPower;
        this.rightPower = rightPower;

        for (DcMotor motor : leftMotors) {
            motor.setPower(leftPower);
        }

        for (DcMotor motor : rightMotors) {
            motor.setPower(rightPower);
        }
    }

    protected long convertInchesToEncoderCounts(double distance) {
        return Math.round(((distance / (Math.PI * wheelDiameter)) / gearRatio) *
                encoderCountsPerDriverGearRotation);
    }

    public double getLeftEncoderCount() {
        if (leftMotorsWithEncoders.size() == 0) {
            return 0;
        }

        double sumValue = 0;

        for (DcMotor leftMotor : leftMotorsWithEncoders) {
            sumValue += leftMotor.getCurrentPosition();
        }

        sumValue /= (leftMotorsWithEncoders.size());
        return sumValue;
    }

    public double getRightEncoderCount() {
        if (rightMotorsWithEncoders.size() == 0) {
            return 0;
        }

        double sumValue = 0;

        for (DcMotor rightMotor : rightMotorsWithEncoders) {
            sumValue += rightMotor.getCurrentPosition();
        }

        sumValue /= rightMotorsWithEncoders.size();
        return sumValue;
    }

    //Resets the motor encoders for the left and right sides of the drive train.

    public void resetMotorEncoders() {
        for (DcMotor motor : allMotorsWithEncoders) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    @Override
    public String toString() {
        return "left pwr: " + String.format("%.2f", leftPower) +
                "\nright pwr: " + String.format("%.2f", rightPower);
    }
}

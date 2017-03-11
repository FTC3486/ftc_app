package org.firstinspires.ftc.teamcode.Extension;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.LinkedList;
import java.util.List;

public class Drivetrain {
    private double wheelDiameter;
    private double gearRatio;
    private int encoderCountsPerDriverGearRotation;
    private List<DcMotor> allMotors;
    private List<DcMotor> allMotorsWithEncoders;
    private List<DcMotor> leftMotors;
    private List<DcMotor> rightMotors;
    private List<DcMotor> leftMotorsWithEncoders;
    private List<DcMotor> rightMotorsWithEncoders;
    private double leftSpeed;
    private double rightSpeed;

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
        this.allMotors = new LinkedList<>();
        this.allMotors.addAll(this.leftMotors);
        this.allMotors.addAll(this.rightMotors);

        for (DcMotor motor : allMotors) {
            motor.setZeroPowerBehavior(builder.zeroPowerBehavior);
        }
    }

    public static class Builder {
        private double wheelDiameter = 4.0;
        private double gearRatio = 1.19;
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

    public void haltDrive() {
        for (DcMotor motor : allMotors) {
            motor.setPower(0);
        }
    }

    public void setMode(DcMotor.RunMode runMode) {
        for (DcMotor motor : allMotorsWithEncoders) {
            motor.setMode(runMode);
        }
    }

    public void setTargetPosition(int leftTargetPosition, int rightTargetPosition) {
        for (DcMotor leftMotor : leftMotorsWithEncoders) {
            leftMotor.setTargetPosition(leftTargetPosition);
        }

        for (DcMotor rightMotor : rightMotorsWithEncoders) {
            rightMotor.setTargetPosition(rightTargetPosition);
        }
    }

    public boolean isBusy() {
        for (DcMotor motor : allMotorsWithEncoders) {
            if (motor.isBusy()) {
                return true;
            }
        }
        return false;
    }

    public void setPowers(double leftSpeed, double rightSpeed) {
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;

        for (DcMotor motor : leftMotors) {
            motor.setPower(leftSpeed);
        }

        for (DcMotor motor : rightMotors) {
            motor.setPower(rightSpeed);
        }
    }

    protected long convertInchesToEncoderCounts(float distance) {
        return Math.round(((distance / (Math.PI * wheelDiameter)) * gearRatio) /
                encoderCountsPerDriverGearRotation);
    }

    protected double getLeftEncoderCount() {
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

    protected double getRightEncoderCount() {
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

    public void resetMotorEncoders() {
        for (DcMotor motor : allMotorsWithEncoders) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    @Override
    public String toString() {
        return "left pwr: " + String.format("%.2f", leftSpeed) +
                "\nright pwr: " + String.format("%.2f", rightSpeed);
    }
}

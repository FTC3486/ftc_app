package org.firstinspires.ftc.teamcode.Extension;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.LinkedList;

public class Drivetrain {
    private double wheelDiameter;
    private double gearRatio;
    private int encoderCountsPerDriverGearRotation;
    private LinkedList<DcMotor> leftMotors;
    private LinkedList<DcMotor> rightMotors;
    private LinkedList<DcMotor> leftMotorsWithEncoders;
    private LinkedList<DcMotor> rightMotorsWithEncoders;
    private double leftSpeed;
    private double rightSpeed;

    private Drivetrain(Builder builder) {
        this.wheelDiameter = builder.wheelDiameter;
        this.gearRatio = builder.gearRatio;
        this.encoderCountsPerDriverGearRotation = builder.encoderCountsPerDriverGearRotation;
        this.leftMotors = builder.leftMotors;
        this.rightMotors = builder.rightMotors;
        this.leftMotorsWithEncoders = builder.leftMotorsWithEncoders;
        this.rightMotorsWithEncoders = builder.rightMotorsWithEncoders;
    }

    public static class Builder {
        private double wheelDiameter = 4.0;
        private double gearRatio = 1.19;
        // 1120 is the number for the AndyMark motors. Tetrix Motors are 1440 PPR
        private int encoderCountsPerDriverGearRotation = 1120;
        private final LinkedList<DcMotor> leftMotors = new LinkedList<DcMotor>();
        private final LinkedList<DcMotor> rightMotors = new LinkedList<DcMotor>();
        private final LinkedList<DcMotor> leftMotorsWithEncoders =
                new LinkedList<DcMotor>();
        private final LinkedList<DcMotor> rightMotorsWithEncoders =
                new LinkedList<DcMotor>();

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

    protected LinkedList<DcMotor> getLeftMotorsWithEncoders() {
        return leftMotorsWithEncoders;
    }

    protected LinkedList<DcMotor> getRightMotorsWithEncoders() {
        return rightMotorsWithEncoders;
    }

    public void haltDrive() {
        for (DcMotor motor : leftMotorsWithEncoders) {
            motor.setPower(0);
        }

        for (DcMotor motor : rightMotorsWithEncoders) {
            motor.setPower(0);
        }

        for (DcMotor motor : leftMotors) {
            motor.setPower(0);
        }

        for (DcMotor motor : rightMotors) {
            motor.setPower(0);
        }
    }

    public void setMode(DcMotor.RunMode runMode) {
        for (DcMotor leftMotor : leftMotorsWithEncoders) {
            leftMotor.setMode(runMode);
        }

        for (DcMotor rightMotor : rightMotorsWithEncoders) {
            rightMotor.setMode(runMode);
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
        boolean leftBusyStatus = true;
        boolean rightBusyStatus = true;

        for (DcMotor leftMotor : leftMotorsWithEncoders) {
            if (!leftMotor.isBusy()) {
                leftBusyStatus = false;
            }
        }
        for (DcMotor rightMotor : rightMotorsWithEncoders) {
            if (!rightMotor.isBusy()) {
                rightBusyStatus = false;
            }
        }
        return leftBusyStatus && rightBusyStatus;
    }

    public void setPowers(double leftSpeed, double rightSpeed) {
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;

        for (DcMotor motor : leftMotorsWithEncoders) {
            motor.setPower(leftSpeed);
        }

        for (DcMotor motor : rightMotorsWithEncoders) {
            motor.setPower(rightSpeed);
        }

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
        double sumValue = 0;

        for (DcMotor leftMotorWithEncoder : leftMotorsWithEncoders) {
            sumValue += leftMotorWithEncoder.getCurrentPosition();
        }

        sumValue = sumValue / (leftMotorsWithEncoders.size());
        return sumValue;
    }

    protected double getRightEncoderCount() {
        double sumValue = 0;

        for (DcMotor rightMotorWithEncoder : rightMotorsWithEncoders) {
            sumValue += rightMotorWithEncoder.getCurrentPosition();
        }

        sumValue = sumValue / leftMotorsWithEncoders.size();
        return sumValue;
    }

    public void resetMotorEncoders() {
        for (DcMotor leftMotorWithEncoders : leftMotorsWithEncoders) {
            leftMotorWithEncoders.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftMotorWithEncoders.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        for (DcMotor rightMotorWithEncoders : rightMotorsWithEncoders) {
            rightMotorWithEncoders.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotorWithEncoders.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }


    @Override
    public String toString() {
        return "left pwr: " + String.format("%.2f", leftSpeed) +
                "\nright pwr: " + String.format("%.2f", rightSpeed);
    }
}

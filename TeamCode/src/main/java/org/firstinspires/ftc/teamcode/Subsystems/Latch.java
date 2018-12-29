package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Initializable;


/**
 * Filename: Latch.java
 * <p>
 * <p>
 * Description:
 * This subsystem controls the lead screw that moves the latching assembly.
 * <p>
 * Methods:
 * retract - Raises the robot as long as the FullyRetracted limit switch is off
 * extend - Lowers the robot as long as the FullyExtended limit switch is off
 * manualRetract - Raise the robot as long as the FullyRetracted limit switch is off
 * manualExtend - Lowers the robot as long as the button is pressed. This bypasses the FullyExtended limit switch
 * manualStop - Stops the latch motor
 * <p>
 * Changelog:
 * -
 * <p>
 * Created by Saatvik Agrawal on 9/29/2018.
 */

public class Latch implements Initializable {
    private final DcMotor latchMotor;
    private final DcMotor latchMotor2;
    private final DigitalChannel latchTop;
    private final DigitalChannel latchBottom;
    private final double retractPower;
    private final double extendPower;

    private enum LatchState {
        RETRACTED,
        RETRACTING,
        MANUAL_RETRACTING,
        EXTENDED,
        EXTENDING,
        MANUAL_EXTENDING,
        MANUAL_STOPPED,
    }
    private LatchState latchState;

    public Latch(
            DcMotor latchMotor,
            DcMotor latchMotor2,
            DigitalChannel latchTop,
            DigitalChannel latchBottom,
            double retractPower,
            double extendPower
    ) {
        this.latchMotor = latchMotor;
        this.latchMotor2 = latchMotor2;
        this.latchTop = latchTop;
        this.latchBottom = latchBottom;
        this.retractPower = retractPower;
        this.extendPower = extendPower;

        this.latchTop.setMode(DigitalChannel.Mode.INPUT);
        this.latchBottom.setMode(DigitalChannel.Mode.INPUT);
    }

    @Override
    public void initialize(){ retract();
    }

    public boolean isFullyRetracted() {
        return !latchBottom.getState();
    }

    public boolean isFullyExtended() {
        return !latchTop.getState();
    }

    public void retract() {
        if (isFullyRetracted()) {
            latchMotor.setPower(0);
            latchMotor2.setPower(0);
            latchState = LatchState.RETRACTED;
        } else {
            latchMotor.setPower(retractPower);
            latchMotor2.setPower(retractPower);
            latchState = LatchState.RETRACTING;
        }
    }

    public void extend() {
        if (isFullyExtended()) {
            latchMotor.setPower(0);
            latchMotor2.setPower(0);
            latchState = LatchState.EXTENDED;
        } else {
            latchMotor.setPower(extendPower);
            latchMotor2.setPower(extendPower);
            latchState = LatchState.EXTENDING;
        }
    }

    public void manualRetract() {
        if (isFullyRetracted()) {
            latchMotor.setPower(0);
            latchMotor2.setPower(0);
            latchState = LatchState.RETRACTED;
        } else {
            latchMotor.setPower(retractPower);
            latchMotor2.setPower(retractPower);
            latchState = LatchState.MANUAL_RETRACTING;
        }
    }

    public void manualExtend() {
        if (isFullyExtended()) {
            latchMotor.setPower(0);
            latchMotor2.setPower(0);
            latchState = LatchState.EXTENDED;
        } else {
            latchMotor.setPower(extendPower);
            latchMotor2.setPower(extendPower);
            latchState = LatchState.MANUAL_EXTENDING;
        }
    }

    public void manualStop() {
        latchMotor.setPower(0);
        latchMotor2.setPower(0);
        latchState = LatchState.MANUAL_STOPPED;
    }

    @Override
    public String toString() {
        switch (latchState) {
            case EXTENDING:
                return "Extending";

            case RETRACTING:
                return "Retracting";

            case MANUAL_EXTENDING:
                return "Manual Extending";

            case MANUAL_RETRACTING:
                return "Manual Retracting";

            case MANUAL_STOPPED:
                return "Stop";

            default:
                return "Unknown";
        }
    }
}

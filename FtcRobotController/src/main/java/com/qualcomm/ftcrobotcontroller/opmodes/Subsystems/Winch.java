package com.qualcomm.ftcrobotcontroller.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Matthew on 1/16/2016.
 */
public class Winch {
    private DcMotor winchMotor;
    private enum winchMotorEnum {IN, OUT, STOP}
    private winchMotorEnum winchMotorState = winchMotorEnum.STOP;

    public Winch(String winchMotor, HardwareMap hardwareMap) {
        this.winchMotor = hardwareMap.dcMotor.get(winchMotor);
    }

    public void out() {
        winchMotor.setPower(1.0);
        winchMotorState = winchMotorEnum.OUT;
    }

    public void in() {
        winchMotor.setPower(-1.0);
        winchMotorState = winchMotorEnum.IN;
    }

    public void stop() {
        winchMotor.setPower(0.0);
        winchMotorState = winchMotorEnum.STOP;
    }

    @Override
    public String toString() {
        switch (winchMotorState) {
            case OUT:
                return "OUT";

            case IN:
                return "IN";

            case STOP:
                return "STOP";

            default:
                return "UNKNOWN";
        }
    }
}

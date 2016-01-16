package com.qualcomm.ftcrobotcontroller.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Matthew on 1/16/2016.
 */
public class Pickup {
    private DcMotor pickup;
    private enum pickupMotorEnum {COLLECT, STOP}
    private pickupMotorEnum pickupState = pickupMotorEnum.STOP;

    public Pickup(String pickup, HardwareMap hardwareMap) {
        this.pickup = hardwareMap.dcMotor.get(pickup);
    }

    public void collect() {
        pickup.setPower(-1.0);
        pickupState = pickupMotorEnum.COLLECT;
    }

    public void stop() {
        pickup.setPower(0.0);
        pickupState = pickupMotorEnum.STOP;
    }

    @Override
    public String toString() {
        switch (pickupState) {
            case COLLECT:
                return "COLLECT";

            case STOP:
                return "STOP";

            default:
                return "UNKNOWN";
        }
    }
}

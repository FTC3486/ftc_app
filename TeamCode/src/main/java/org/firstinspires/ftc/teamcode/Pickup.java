package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * Created by John Paul Ashour on 11/12/2016.
 */

public class Pickup {
    public DcMotor Pickup = null;


    private enum pickupEnum {Run, Stop}
    private pickupEnum PickupState =pickupEnum.Stop;


    public Pickup(String Pickup, HardwareMap hardwareMap) {
        this.Pickup = hardwareMap.dcMotor.get(Pickup);
    }

    public void run() {
        Pickup.setPower(-1.0);
        PickupState = pickupEnum.Run;
    }

    public void stop() {
        Pickup.setPower(0);
        PickupState = pickupEnum.Stop;
    }

    @Override
    public String toString() {
        switch (PickupState){
            case Run:
                return "Running";

            case Stop:
                return "Stopped";

            default:
                return "Unknown";

        }


    }

}

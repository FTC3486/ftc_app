package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by John Paul Ashour on 11/5/2016.
 */

public class ParticleAcclerator {
    public DcMotor Acclerator = null;

    private enum accleratorEnum {Run, Stop}
    private accleratorEnum AccleratorState =accleratorEnum.Stop;


    public ParticleAcclerator(String Acclerator, HardwareMap hardwareMap) {
        this.Acclerator = hardwareMap.dcMotor.get(Acclerator);
    }

    public void run() {
        Acclerator.setPower(-1.0);
        AccleratorState = accleratorEnum.Run;
    }

    public void stop() {
        Acclerator.setPower(0);
        AccleratorState = accleratorEnum.Stop;
    }

    @Override
    public String toString() {
        switch (AccleratorState){
            case Run:
                return "Running";

            case Stop:
                return "Stopped";

            default:
                return "Unknown";

        }


    }


}

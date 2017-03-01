package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static android.os.Looper.loop;
import static android.os.SystemClock.sleep;

/**
 * Created by John Paul Ashour on 11/5/2016.
 */

public class ParticleAcclerator {
    public DcMotor Acclerator = null;

    public double accleratorPower = 0;

    private enum accleratorEnum {Run, Rampup, Rampdown, Stop}
    private accleratorEnum AccleratorState =accleratorEnum.Stop;


    public ParticleAcclerator(String Acclerator, HardwareMap hardwareMap) {
        this.Acclerator = hardwareMap.dcMotor.get(Acclerator);
    }


    public void run() {
        Acclerator.setPower(1.0);
        AccleratorState = accleratorEnum.Run;
    }

    public void rampup(){

        accleratorPower = accleratorPower + 0.001;
        Acclerator.setPower(accleratorPower);

    }

    public void rampdown(){
        AccleratorState = accleratorEnum.Rampdown;

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

            case Rampup:
                return "Ramping Up";

            case Stop:
                return "Stopped";

            default:
                return "Unknown";

        }


    }


}

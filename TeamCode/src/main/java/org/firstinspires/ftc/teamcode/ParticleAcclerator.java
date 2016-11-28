package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static android.os.SystemClock.sleep;

/**
 * Created by John Paul Ashour on 11/5/2016.
 */

public class ParticleAcclerator {
    public DcMotor Acclerator = null;

    private enum accleratorEnum {Run, Rampup, Rampdown, Stop}
    private accleratorEnum AccleratorState =accleratorEnum.Stop;


    public ParticleAcclerator(String Acclerator, HardwareMap hardwareMap) {
        this.Acclerator = hardwareMap.dcMotor.get(Acclerator);
    }

    public void run() {
        Acclerator.setPower(-1.0);
        AccleratorState = accleratorEnum.Run;
    }

    public void rampup(){
        AccleratorState = accleratorEnum.Rampup;
        Acclerator.setPower(0);
        sleep(200);
        Acclerator.setPower(-0.1);
        sleep(200);
        Acclerator.setPower(-0.2);
        sleep(200);
        Acclerator.setPower(-0.3);
        sleep(200);
        Acclerator.setPower(-0.4);
        sleep(200);
        Acclerator.setPower(-0.5);
        sleep(200);
        Acclerator.setPower(-0.6);
        sleep(200);
        Acclerator.setPower(-0.7);
        sleep(200);
        Acclerator.setPower(-0.8);
        sleep(200);
        Acclerator.setPower(-0.9);
        sleep(200);
        Acclerator.setPower(-1.0);
        sleep(1000);
        AccleratorState = accleratorEnum.Run;
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

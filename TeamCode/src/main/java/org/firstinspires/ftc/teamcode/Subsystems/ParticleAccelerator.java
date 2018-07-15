package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by John Paul Ashour on 11/5/2016.
 */

public class ParticleAccelerator {
    public DcMotor Accelerator = null;

    public double acceleratorPower = 0;

    private enum acceleratorEnum {Run, Rampup, Rampdown, Stop}

    private acceleratorEnum AcceleratorState = acceleratorEnum.Stop;


    public ParticleAccelerator(String Acclerator, HardwareMap hardwareMap) {
        this.Accelerator = hardwareMap.dcMotor.get(Acclerator);
    }


    public void run() {
        Accelerator.setPower(1.0);
        AcceleratorState = acceleratorEnum.Run;
    }

    public void rampup() {
        acceleratorPower = acceleratorPower + 0.001;
        Accelerator.setPower(acceleratorPower);

    }

    public void rampdown() {
        AcceleratorState = acceleratorEnum.Rampdown;

    }

    public void stop() {
        Accelerator.setPower(0);
        AcceleratorState = acceleratorEnum.Stop;
    }

    @Override
    public String toString() {
        switch (AcceleratorState) {
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

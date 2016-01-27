package com.qualcomm.ftcrobotcontroller.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Matthew on 1/26/2016.
 */
public class ClimberDump {
    Servo climberDump;

    enum climberDumperEnumeration {HOLDING, DUMPING}

    climberDumperEnumeration climberDumperState = climberDumperEnumeration.HOLDING;

    public ClimberDump(String climberDump, HardwareMap hardwareMap) {
        this.climberDump = hardwareMap.servo.get(climberDump);
        this.holdClimbers();
    }

    private void holdClimbers() {
        //climberDump.setPosition();
        //climberDumperState = climberDumperEnumeration.HOLDING;
    }

    private void dumpClimbers() {
        //climberDump.setPosition();
        //climberDumperState = climberDumperEnumeration.DUMPING;
    }

    @Override
    public String toString() {
        switch (climberDumperState) {
            case HOLDING:
                return "HOLDING";

            case DUMPING:
                return "DUMPING";

            default:
                return "UNKNOWN";
        }
    }
}

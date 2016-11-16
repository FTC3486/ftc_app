package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Owner_2 on 11/15/2016.
 */

public class TroughGate {
    Servo troughGateServo;
    public boolean isOpen = false;


    private enum troughGateServoEnum {Open, Close}

    troughGateServoEnum troughServoState = troughGateServoEnum.Close;
    //HardwareMap hwMap = null;

    public TroughGate(String troughGateServo, HardwareMap hardwareMap) {
        this.troughGateServo = hardwareMap.servo.get(troughGateServo);
        this.closeGate();
    }

    public void closeGate() {
        troughGateServo.setPosition(1);
        troughServoState = troughGateServoEnum.Close;
        isOpen = false;
    }

    public void openGate() {
        troughGateServo.setPosition(0);
        troughServoState = troughGateServoEnum.Open;
        isOpen = true;
    }

    @Override
    public String toString() {
        switch (troughServoState) {
            case Open:
                return "Gate Open";

            case Close:
                return "Gate Closed";

            default:
                return "unknown";
        }
    }
}
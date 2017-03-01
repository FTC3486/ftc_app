package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;



/**
 * Created by Owner_2 on 11/19/2016.
 */

public class TuskGate {
        Servo tuskGateServo;
        public boolean isOpen = false;


        private enum tuskGateServoEnum {Release, Close}

        tuskGateServoEnum tuskServoState = tuskGateServoEnum.Close;


        public TuskGate(String tuskGateServo, HardwareMap hardwareMap) {
            this.tuskGateServo = hardwareMap.servo.get(tuskGateServo);
            this.closeGate();
        }

        public void closeGate() {
            tuskGateServo.setPosition(0.9);
            tuskServoState = tuskGateServoEnum.Close;
            isOpen = false;
        }

        public void releaseTusks() {
            tuskGateServo.setPosition(0.3);
             tuskServoState = tuskGateServoEnum.Release;
            isOpen = true;
        }

        @Override
        public String toString() {
            switch (tuskServoState) {
                case Release:
                    return "Tusks Released";

                case Close:
                    return "Tusk Gate Closed";

                default:
                    return "unknown";
            }
        }
    }


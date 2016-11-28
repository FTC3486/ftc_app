package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Owner_2 on 11/26/2016.
 */

public class BaconServo {
    Servo baconActivatorServo;

    private enum baconServoEnum {Up, Down, Scanning}

    public BaconServo(String baconActivatorServo, HardwareMap hardwareMap){
    this.baconActivatorServo = hardwareMap.servo.get(baconActivatorServo);
    }
}

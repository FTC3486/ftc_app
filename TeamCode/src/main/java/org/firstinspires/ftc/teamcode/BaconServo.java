package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Owner_2 on 11/26/2016.
 */

public class BaconServo {
    Servo baconActivatorServo;

    private enum baconServoEnum {Up, Down, Scanning, Pressing}
    baconServoEnum baconServoState = baconServoEnum.Down;

    public BaconServo(String baconActivatorServo, HardwareMap hardwareMap){
    this.baconActivatorServo = hardwareMap.servo.get(baconActivatorServo);
        this.armDown();
    }

    public void armDown(){
        baconActivatorServo.setPosition(0);
        baconServoState = baconServoEnum.Down;

    }
    public void armUp(){
        baconActivatorServo.setPosition(1);
        baconServoState = baconServoEnum.Up;
    }
    public void armPressing(){
        baconActivatorServo.setPosition(0.5);
        baconServoState = baconServoEnum.Pressing;
    }
    private void sensorScanning(){
        baconActivatorServo.setPosition(0.6);
        baconServoState = baconServoEnum.Scanning;
    }
    @Override
    public String toString(){
        switch (baconServoState){
            case Down:
                return "Bacon Arm Down";
            case Up:
                return "Bacon Arm Up";
            case Pressing:
                return "Arm is set to Press Button";
            case Scanning:
                return "Ready to Scan Beacon Color";
            default:
                return "unknown";
        }

    }
}

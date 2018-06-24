package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Owner_2 on 11/26/2016.
 */

public class BaconActivator {
    private Servo baconActivatorServo;

    private enum baconServoEnum {Up, Down, Scanning, Pressing}
    baconServoEnum baconServoState = baconServoEnum.Down;

    public BaconActivator(String baconActivatorServo, HardwareMap hardwareMap){
    this.baconActivatorServo = hardwareMap.servo.get(baconActivatorServo);
    }

    public void armDown(){
        baconActivatorServo.setPosition(0);
        baconServoState = baconServoEnum.Down;

    }
    public void armUp(){
        baconActivatorServo.setPosition(0.8);
        baconServoState = baconServoEnum.Up;
    }
    public void armPressing(){
        baconActivatorServo.setPosition(0.15);
        baconServoState = baconServoEnum.Pressing;
    }
    public void sensorScanning(){
        baconActivatorServo.setPosition(0.33);
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

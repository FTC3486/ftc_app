package com.qualcomm.ftcrobotcontroller.opmodes.Subsystems;

import android.widget.Switch;

import com.jacobamason.FTCRC_Extensions.ExtendedServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Matthew on 1/16/2016.
 */
public class Turret {
    private DcMotor swivel;
    private enum swivelMotorEnum {LEFT, RIGHT, STOP}
    private swivelMotorEnum swivelState = swivelMotorEnum.STOP;

    private DcMotor extender;
    private enum extenderMotorEnum {IN, OUT, STOP};
    private extenderMotorEnum extenderState = extenderMotorEnum.STOP;

    private ExtendedServo dumper;
    private boolean isDumping = false;

    public Turret(String swivel, String extender, String dumper, HardwareMap hardwareMap) {
        this.swivel = hardwareMap.dcMotor.get(swivel);
        this.extender = hardwareMap.dcMotor.get(extender);
        this.dumper = new ExtendedServo(hardwareMap.servo.get(dumper));

        this.swivel.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        this.swivel.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    public void swivelRight() {
        swivel.setPower(0.5);
        swivelState = swivelMotorEnum.RIGHT;
    }

    public void swivelLeft() {
        swivel.setPower(-0.5);
        swivelState = swivelMotorEnum.LEFT;
    }

    public void swivelStop() {
        swivel.setPower(0.0);
        swivelState = swivelMotorEnum.STOP;
    }

    public void retract() {
        extender.setPower(-1.0);
        extenderState = extenderMotorEnum.IN;
    }

    public void extend() {
        extender.setPower(1.0);
        extenderState = extenderMotorEnum.OUT;
    }

    public void extenderStop() {
        extender.setPower(0.0);
        extenderState = extenderMotorEnum.STOP;
    }

    public void dumpDebris() {
        dumper.setPosition(1.0);
        isDumping = true;
    }

    public void holdDebris() {
        dumper.setPosition(0.0);
        isDumping = false;
    }

    @Override
    public String toString() {
        String returnString = "Swivel ";
        switch(swivelState) {
            case LEFT:
                returnString += "LEFT";
                break;

            case RIGHT:
                returnString += "RIGHT";
                break;

            case STOP:
                returnString += "STOP";
                break;

            default:
                returnString += "UNKNOWN";
                break;
        }

        returnString += "\nExtender ";
        switch(extenderState) {
            case IN:
                returnString += "IN";
                break;

            case OUT:
                returnString += "OUT";
                break;

            case STOP:
                returnString += "STOP";
                break;

            default:
                returnString += "UNKNOWN";
                break;
        }

        returnString += "\nDumper ";
        if(isDumping) {
            returnString += "DUMPING";
        } else {
            returnString += "HOLDING";
        }

        return returnString;
    }
}

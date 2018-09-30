package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * Filename: Latch.java
 *
 *
 * Description:
 *      This subsystem controls the lead screw that moves the latching assembly.
 *
 * Methods:
 *      retracting - Raises the robot as long as the FullyRetracted limit switch is off
 *      extending - Lowers the robot as long as the FullyExtended limit switch is off
 *      manualretract - Raises the robot as long as the buttom is pressed and the FullyRetracted limit switch is off
 *      manualextending - Lowers the robot as long as the button is pressed. This bypasses the FullyExtended limit switch
 *
 * Changelog:
 *      -
 *
 * Created by Saatvik Agrawal on 9/29/2018.
 */

public class Latch {
    private DcMotor latch = null;
    private DigitalChannel latchTop;
    private DigitalChannel latchBottom;



    private enum latchEnum {
        RETRACTING,
        EXTENDING,
        MANUALRETRACTING,
        MANUALEXTENDING,
        STOPPED
    }
    private latchEnum latchState;



    public Latch(String latch, String touchSensor, HardwareMap hardwareMap) {
        this.latch = hardwareMap.dcMotor.get(latch);
        latchTop = hardwareMap.get(DigitalChannel.class, touchSensor);
        latchTop.setMode(DigitalChannel.Mode.INPUT);
        latchBottom = hardwareMap.get(DigitalChannel.class, touchSensor);
        latchBottom.setMode(DigitalChannel.Mode.INPUT);

        stop();
    }

    public boolean isFullyRetracted(){
        return !latchTop.getState();
    }

    public boolean isFullyExtended(){
        return !latchBottom.getState();
    }

//Retracting Latch
    public void retract(){
        if (isFullyExtended()){
            stop();
        }else{
            latch.setPower(1.0);
            latchState = latchEnum.RETRACTING;
        };
    }

//Runs Glyph Lift down
    public void unlatch(){
        latch.setPower(-.1);
    }

//Stops Glyph Lift motion and holds current position
    public void up() {
        latch.setPower(.1);
    }

    public void down() {
        latch.setPower(-.1);
    }

    public void stop(){
        latch.setPower(0);
    }

    @Override
    public String toString() {
        switch (latchState){
            case EXTENDING:
                return "Extending";

            case RETRACTING:
                return "Retracting";

            case MANUALEXTENDING:
                return "Manual Extending";

            case MANUALRETRACTING:
                return "Manual Retracting";

            case STOPPED:
                return "Stopped";

            default:
                return "Unknown";

        }


    }


}

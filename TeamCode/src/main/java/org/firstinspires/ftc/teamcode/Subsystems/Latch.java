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
 *      manualRetracting - Raise the robot as long as the FullyRetracted limit switch is off
 *      manualExtending - Lowers the robot as long as the button is pressed. This bypasses the FullyExtended limit switch
 *
 * Changelog:
 *      -
 *
 * Created by Saatvik Agrawal on 9/29/2018.
 */

public class Latch {
    private DcMotor latch = null;
    //private DigitalChannel latchTop;
    //private DigitalChannel latchBottom;



    private enum latchEnum {
        RETRACTING,
        EXTENDING,
        MANUALRETRACTING,
        MANUALEXTENDING,
        STOPPED
    }
    private latchEnum latchState = latchEnum.STOPPED;



    public Latch(String latch,  HardwareMap hardwareMap) {   //String touchSensor,
        this.latch = hardwareMap.dcMotor.get(latch);
        //latchTop = hardwareMap.get(DigitalChannel.class, touchSensor);
        //latchTop.setMode(DigitalChannel.Mode.INPUT);
        //latchBottom = hardwareMap.get(DigitalChannel.class, touchSensor);
        //latchBottom.setMode(DigitalChannel.Mode.INPUT);

        //stop();
    }

    //public boolean isFullyRetracted(){
     //   return !latchBottom.getState();
    //}

   // public boolean isFullyExtended(){
    //    return !latchTop.getState();
    //}

//Retracting Latch
   // public void retract(){
       // if (isFullyRetracted()){
        //    stop();
       // }
        //else{
        //    latch.setPower(1.0);
        //    latchState = latchEnum.RETRACTING;
         //   }
        //}


//Runs Glyph Lift down
   // public void unlatch()
   // {
   //     latch.setPower(-.1);
   // }

//Stops Glyph Lift motion and holds current position
    //public void extend() {
        //if (isFullyExtended()){
         //   stop();
        //}
        //else{
          //  latch.setPower(-1.0);
         //   latchState = latchEnum.EXTENDING;
       // }
   // }


    public void manualRetract()
    {
        latch.setPower(-1.0);
    }
    public void manualExtend()
    {
        latch.setPower(1.0);
    }

    public void stopped()
    {
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
                return "Stop";

            default:
                return "Unknown";

        }


    }


}

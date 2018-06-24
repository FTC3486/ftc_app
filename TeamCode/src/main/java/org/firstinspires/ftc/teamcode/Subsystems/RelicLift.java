package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * Created by John Paul Ashour on 11/13/2016.
 */

public class RelicLift {
    private DcMotor Lift = null;


    private enum columnEnum {Extend, Retract, Stop}
    private columnEnum ColumnState = columnEnum.Stop;


    public RelicLift(String Lift, HardwareMap hardwareMap) {
        this.Lift = hardwareMap.dcMotor.get(Lift);
    }
//Runs Glyph Lift up
    public void lift(){
        Lift.setPower(-1.0);
    }
//Runs Glyph Lift down
    public void retract(){
        Lift.setPower(1.0);
    }
//Stops Glyph Lift motion and holds current position
    public void stop(){
        Lift.setPower(0);
    }

    @Override
    public String toString() {
        switch (ColumnState){
            case Extend:
                return "Extending";

            case Retract:
                return "Retracting";

            case Stop:
                return "Stopped";

            default:
                return "Unknown";

        }


    }


}

package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * Created by John Paul Ashour on 11/13/2016.
 */

public class Column {
    private DcMotor Column1;
    private DcMotor Column2;

    private enum columnEnum {Extend, Retract, Stop}

    private columnEnum ColumnState = columnEnum.Stop;


    public Column(String Column1, String Column2, HardwareMap hardwareMap) {
        this.Column1 = hardwareMap.dcMotor.get(Column1);
        this.Column2 = hardwareMap.dcMotor.get(Column2);
    }

    public void extend() {
        Column1.setPower(-1.0);
        Column2.setPower(1.0);
    }

    public void retract() {
        Column1.setPower(1.0);
        Column2.setPower(-1.0);
    }

    public void stop() {
        Column1.setPower(0);
        Column2.setPower(0);
    }

    @Override
    public String toString() {
        switch (ColumnState) {
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

package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Matthew on 8/25/2015.
 */

public class DemoAutonomous extends DemoTelemetry {
    int v_state = 0;

    public DemoAutonomous () {

    }

    @Override public void start(){
        super.start();
        reset_drive_encoders();
    }

    static int[] times = new int [3];

    @Override public void loop(){

        switch(v_state) {
            case 0:
                reset_drive_encoders();

                times[0] = 0;
                times[1] = 0;
                times[2] = 0;
                v_state++;

                break;

            case 1:
                run_using_encoders();
                set_drive_power(1.0f, 1.0f);

                if (have_drive_encoders_reached(7000, 7000)) {
                    reset_drive_encoders();

                    set_drive_power(0.0f, 0.0f);

                    v_state++;
                }
                break;

            case 2:
                if (have_drive_encoders_reset ())
                {
                    v_state++;
                }
                else
                {
                    times[0]++;
                }
                break;
            //
            // Turn left until the encoders exceed the specified values.
            //
            case 3:
                run_using_encoders ();
                set_drive_power (-1.0f, 1.0f);
                if (have_drive_encoders_reached (2880, 2880))
                {
                    reset_drive_encoders ();
                    set_drive_power (0.0f, 0.0f);
                    v_state++;
                }
                break;

            default:
                //
                // The autonomous actions have been accomplished (i.e. the state has
                // transitioned into its final state.
                //
                break;
            }

        update_telemetry (); // Update common telemetry
        telemetry.addData ("11", "State: " + v_state);
        telemetry.addData ("12", "Times: " + times[0]);
        telemetry.addData ("13", "Times: " + times[1]);
        telemetry.addData ("14", "Times: " + times[2]);
    }
}

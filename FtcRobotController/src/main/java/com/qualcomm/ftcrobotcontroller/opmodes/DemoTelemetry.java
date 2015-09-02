package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by Matthew on 8/25/2015.
 */
public class DemoTelemetry extends DemoBotHardware {
    /**
     * Update the telemetry with current values from the base class.
     */
    public void update_telemetry ()

    {
        //
        // Send telemetry data to the driver station.
        //
        telemetry.addData
                ( "01"
                        , "Left Drive: "
                                + a_left_drive_power ()
                                + ", "
                                + a_left_encoder_count ()
                );
        telemetry.addData
                ( "02"
                        , "Right Drive: "
                                + a_right_drive_power()
                                + ", "
                                + a_right_encoder_count()
                );

        telemetry.addData("isPressed", String.valueOf(touchSensor.isPressed()));
    } // PushBotTelemetry::loop

} // PushBotTelemetry


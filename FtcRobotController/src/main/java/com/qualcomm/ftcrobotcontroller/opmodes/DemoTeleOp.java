package com.qualcomm.ftcrobotcontroller.opmodes;

//------------------------------------------------------------------------------
//
// PushBotManual
//

import com.FTC3486.FTCRC_Extensions.Drive;

/**
 * Extends the PushBotTelemetry and PushBotHardware classes to provide a basic
 * manual operational mode for the Push Bot.
 *
 * @author SSI Robotics
 * @version 2015-08-01-06-01
 */
public class DemoTeleOp extends DemoTelemetry
{
    Drive driver;



    @Override public void init() {
        driver = new Drive(this, 0.15f);
    }

    @Override public void loop ()

    {
      // driver.tank_drive(motor_left_drive, motor_right_drive);

        //----------------------------------------------------------------------
        //
        // Servo Motors
        //
        // Obtain the current values of the gamepad 'x' and 'b' buttons.
        //
        // Note that x and b buttons have boolean values of true and false.
        //
        // The clip method guarantees the value never exceeds the allowable range of
        // [0,1].
        //
        // The setPosition methods write the motor power values to the Servo
        // class, but the positions aren't applied until this method ends.
        //

        if (gamepad2.x)
        {
            hand_position (servoArm.getPosition() + 0.05);
        }
        else if (gamepad2.b)
        {
            hand_position(servoArm.getPosition() - 0.05);
        }

        //
        // Send telemetry data to the driver station.
        //
       /* update_telemetry (); // Update common telemetry

        telemetry.addData ("13", "GP2 X: " + gamepad2.x);
        telemetry.addData ("14", "GP2 Y: " + gamepad2.b);*/

    } // PushBotManual::loop

} // PushBotManual

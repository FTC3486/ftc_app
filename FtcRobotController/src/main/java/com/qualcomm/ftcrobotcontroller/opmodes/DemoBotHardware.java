package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Matthew on 8/25/2015.
 */
public class DemoBotHardware extends OpMode{
    private DcMotorController motor_controller;
    private DcMotorController motor_controller2;

    //--------------------------------------------------------------------------
    //
    // v_motor_left_drive
    // v_channel_left_drive
    //
    //--------
    // These class members manage the aspects of the left drive motor.
    //--------
    private DcMotor motor_left_drive;
    final int channel_left_drive = 1;

    //--------------------------------------------------------------------------
    //
    // v_motor_right_drive
    // v_channel_right_drive
    //
    //--------
    // These class members manage the aspects of the right drive motor.
    //--------
    private DcMotor motor_right_drive;
    final int channel_right_drive = 2;

    public DemoBotHardware()

    {

    }

    @Override public void init ()

    {
        //
        // Use the hardwareMap to associate class members to hardware ports.
        //
        // Note that the names of the devices (i.e. arguments to the get method)
        // must match the names specified in the configuration file created by
        // the FTC Robot Controller (Settings-->Configure Robot).
        //

        //
        // Connect the drive wheel motors.
        //
        // The direction of the right motor is reversed, so joystick inputs can
        // be more generically applied.
        //
        motor_controller
                = hardwareMap.dcMotorController.get("drive_controller");

        motor_left_drive = hardwareMap.dcMotor.get ("left_drive");

        motor_controller2
                = hardwareMap.dcMotorController.get("drive_controller2");

        motor_right_drive = hardwareMap.dcMotor.get ("right_drive");

        motor_right_drive.setDirection (DcMotor.Direction.REVERSE);
    } // PushBotHardware::init

    @Override public void start ()
    {

    } // PushBotHardware::start


    @Override public void loop ()
    {

    } // PushBotHardware::loop

    @Override public void stop ()
    {

    } // PushBotHardware::stop

    double a_left_drive_power ()
    {
        return motor_left_drive.getPower ();

    } // PushBotManual::a_left_drive_power

    double a_right_drive_power ()
    {
        return motor_right_drive.getPower ();

    } // PushBotManual::a_right_drive_power

    void set_drive_power (double p_left_power, double p_right_power)
    {
        motor_left_drive.setPower (p_left_power);
        motor_right_drive.setPower (p_right_power);

    } // PushBotManual::set_drive_power
    //--------------------------------------------------------------------------
    //
    // run_using_encoders
    //
    /**
     * Sets both drive wheel encoders to run, if the mode is appropriate.
     */
    public void run_using_encoders ()

    {
        DcMotorController.RunMode l_mode
                = motor_controller.getMotorChannelMode
                ( channel_left_drive
                );
        if (l_mode == DcMotorController.RunMode.RESET_ENCODERS)
        {
            motor_controller.setMotorChannelMode
                    ( channel_left_drive
                            , DcMotorController.RunMode.RUN_USING_ENCODERS
                    );
        }

        l_mode = motor_controller2.getMotorChannelMode
                ( channel_right_drive
                );
        if (l_mode == DcMotorController.RunMode.RESET_ENCODERS)
        {
            motor_controller2.setMotorChannelMode
                    ( channel_right_drive
                            , DcMotorController.RunMode.RUN_USING_ENCODERS
                    );
        }

    } // PushBotAuto::run_using_encoders

    public void sleep(long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    /**
     * Resets both drive wheel encoders.
     */
    public void reset_drive_encoders ()

    {
        //
        // Reset the motor encoders on the drive wheels.
        //
        motor_controller.setMotorChannelMode
                ( channel_left_drive
                        , DcMotorController.RunMode.RESET_ENCODERS
                );

        motor_controller2.setMotorChannelMode
                ( channel_right_drive
                        , DcMotorController.RunMode.RESET_ENCODERS
                );

    } // PushBotAuto::reset_drive_encoders

    //--------------------------------------------------------------------------
    //
    // a_left_encoder_count
    //
    //--------
    // Access the left encoder's count.
    //--------
    int a_left_encoder_count ()
    {
        return motor_left_drive.getCurrentPosition ();

    } // PushBotManual::a_left_encoder_count

    //--------------------------------------------------------------------------
    //
    // a_right_encoder_count
    //
    //--------
    // Access the right encoder's count.
    //--------
    int a_right_encoder_count ()
    {
        return motor_right_drive.getCurrentPosition();

    } // PushBotManual::a_right_encoder_count

    boolean have_drive_encoders_reached
    ( double p_left_count
            , double p_right_count
    )
    {
        //
        // Assume failure.
        //
        boolean l_status = false;

        //
        // Have the encoders reached the specified values?
        //
        // TODO Implement stall code using these variables.
        //
        if ((Math.abs(motor_left_drive.getCurrentPosition()) > p_left_count) &&
                (Math.abs(motor_right_drive.getCurrentPosition()) > p_right_count))
        {
            //
            // Set the status to a positive indication.
            //
            l_status = true;
        }

        //
        // Return the status.
        //
        return l_status;

    } // PushBotManual::have_encoders_reached

    boolean have_drive_encoders_reset ()
    {
        //
        // Assume failure.
        //
        boolean l_status = false;

        //
        // Have the encoders reached zero?
        //
        if ((a_left_encoder_count() == 0) && (a_right_encoder_count() == 0))
        {
            //
            // Set the status to a positive indication.
            //
            l_status = true;
        }

        //
        // Return the status.
        //
        return l_status;

    } // PushBotManual::have_drive_encoders_reset

    //--------------------------------------------------------------------------
}



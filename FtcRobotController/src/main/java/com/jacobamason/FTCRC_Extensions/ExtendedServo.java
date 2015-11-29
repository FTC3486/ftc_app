package com.jacobamason.FTCRC_Extensions;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.Range;

import org.apache.http.impl.conn.ProxySelectorRoutePlanner;

import java.util.LinkedList;

/**
 * Created by Jacob on 11/28/2015.
 */
public class ExtendedServo extends Servo
{
    double rate;

    public ExtendedServo(Servo servo)
    {
        super(servo.getController(), servo.getPortNumber());
        this.rate = 0.0;
    }

    @Override
    public void setPosition(double position)
    {
        super.setPosition(
                Range.clip(position, this.minPosition, this.maxPosition));
    }

    public void runServo()
    {
        this.setPosition(this.getPosition() + this.rate);
    }

    public void runIf(boolean bool, double rate)
    {
        if (bool)
        {
            this.rate = rate;
        }
        else
        {
            if (rate == this.rate)
            {
                this.rate = 0.0;
            }
        }
    }
}

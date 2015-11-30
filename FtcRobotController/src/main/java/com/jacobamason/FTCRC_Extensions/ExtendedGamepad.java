package com.jacobamason.FTCRC_Extensions;

import android.view.KeyEvent;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Jacob on 11/28/2015.
 */
public class ExtendedGamepad extends Gamepad
{
    public class Toggle
    {
        boolean dpad_up = false;
        boolean dpad_down = false;
        boolean dpad_right = false;
        boolean dpad_left = false;
        boolean a = false;
        boolean b = false;
        boolean x = false;
        boolean y = false;
        boolean guide = false;
        boolean start = false;
        boolean back = false;
        boolean right_bumper = false;
        boolean left_bumper = false;
        boolean left_stick_button = false;
        boolean right_stick_button = false;
    }

    public Toggle toggle = new Toggle();

    @Override
    public void update(KeyEvent event)
    {
        this.id = event.getDeviceId();
        this.timestamp = event.getEventTime();
        int keyCode = event.getKeyCode();
        if (keyCode == 19)
        {
            this.dpad_up = this.pressed(event);
            this.toggle.dpad_up = !this.toggle.dpad_up;
        }
        else if (keyCode == 20)
        {
            this.dpad_down = this.pressed(event);
            this.toggle.dpad_down = !this.toggle.dpad_down;
        }
        else if (keyCode == 22)
        {
            this.dpad_right = this.pressed(event);
            this.toggle.dpad_right = !this.toggle.dpad_right;
        }
        else if (keyCode == 21)
        {
            this.dpad_left = this.pressed(event);
            this.toggle.dpad_left = !this.toggle.dpad_left;
        }
        else if (keyCode == 96)
        {
            this.a = this.pressed(event);
            this.toggle.a = !this.toggle.a;
        }
        else if (keyCode == 97)
        {
            this.b = this.pressed(event);
            this.toggle.b = !this.toggle.b;
        }
        else if (keyCode == 99)
        {
            this.x = this.pressed(event);
            this.toggle.x = !this.toggle.x;
        }
        else if (keyCode == 100)
        {
            this.y = this.pressed(event);
            this.toggle.y = !this.toggle.y;
        }
        else if (keyCode == 110)
        {
            this.guide = this.pressed(event);
            this.toggle.guide = !this.toggle.guide;
        }
        else if (keyCode == 108)
        {
            this.start = this.pressed(event);
            this.toggle.start = !this.toggle.start;
        }
        else if (keyCode == 4)
        {
            this.back = this.pressed(event);
            this.toggle.back = !this.toggle.back;
        }
        else if (keyCode == 103)
        {
            this.right_bumper = this.pressed(event);
            this.toggle.right_bumper = !this.toggle.right_bumper;
        }
        else if (keyCode == 102)
        {
            this.left_bumper = this.pressed(event);
            this.toggle.left_bumper = !this.toggle.right_bumper;
        }
        else if (keyCode == 106)
        {
            this.left_stick_button = this.pressed(event);
            this.toggle.left_stick_button = !this.toggle.left_stick_button;
        }
        else if (keyCode == 107)
        {
            this.right_stick_button = this.pressed(event);
            this.toggle.right_stick_button = !this.toggle.right_stick_button;
        }

        this.callCallback();
    }

    @Override
    public String toString()
    {
        String str = super.toString();

        String toggleStr = "";
        if(this.toggle.dpad_up) {
            toggleStr = toggleStr + "tog.dpad_up ";
        }

        if(this.toggle.dpad_down) {
            toggleStr = toggleStr + "tog.dpad_down ";
        }

        if(this.toggle.dpad_left) {
            toggleStr = toggleStr + "tog.dpad_left ";
        }

        if(this.toggle.dpad_right) {
            toggleStr = toggleStr + "tog.dpad_right ";
        }

        if(this.toggle.a) {
            toggleStr = toggleStr + "tog.a ";
        }

        if(this.toggle.b) {
            toggleStr = toggleStr + "tog.b ";
        }

        if(this.toggle.x) {
            toggleStr = toggleStr + "tog.x ";
        }

        if(this.toggle.y) {
            toggleStr = toggleStr + "tog.y ";
        }

        if(this.toggle.guide) {
            toggleStr = toggleStr + "tog.guide ";
        }

        if(this.toggle.start) {
            toggleStr = toggleStr + "tog.start ";
        }

        if(this.toggle.back) {
            toggleStr = toggleStr + "tog.back ";
        }

        if(this.toggle.left_bumper) {
            toggleStr = toggleStr + "tog.left_bumper ";
        }

        if(this.toggle.right_bumper) {
            toggleStr = toggleStr + "tog.right_bumper ";
        }

        if(this.toggle.left_stick_button) {
            toggleStr = toggleStr +  "tog.left_stick_button ";
        }

        if(this.toggle.right_stick_button) {
            toggleStr = toggleStr +  "toggle.right_stick_button";
        }

        return str.concat(toggleStr);
    }
}

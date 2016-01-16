package com.jacobamason.FTCRC_Extensions;

import android.view.KeyEvent;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.Enumeration;

/**
 * Created by Jacob on 11/28/2015.
 */
public class GamepadWrapper
{
    private Gamepad gamepad;

    public GamepadWrapper(Gamepad gamepad)
    {
        this.gamepad = gamepad;
    }

    public class ButtonMap
    {
        public boolean dpad_up = false;
        public boolean dpad_down = false;
        public boolean dpad_right = false;
        public boolean dpad_left = false;
        public boolean a = false;
        public boolean b = false;
        public boolean x = false;
        public boolean y = false;
        public boolean guide = false;
        public boolean start = false;
        public boolean back = false;
        public boolean right_bumper = false;
        public boolean left_bumper = false;
        public boolean left_stick_button = false;
        public boolean right_stick_button = false;
    }

    public ButtonMap toggle = new ButtonMap();
    private ButtonMap previousButtonStates  = new ButtonMap();

    public void updateButtonMap(ButtonMap map)
    {
        map.dpad_up = gamepad.dpad_up;
        map.dpad_down = gamepad.dpad_down;
        map.dpad_right = gamepad.dpad_right;
        map.dpad_left = gamepad.dpad_left;
        map.a = gamepad.a;
        map.b = gamepad.b;
        map.x = gamepad.x;
        map.y = gamepad.y;
        map.start = gamepad.start;
        map.back = gamepad.back;
        map.right_bumper = gamepad.right_bumper;
        map.left_bumper = gamepad.left_bumper;
        map.left_stick_button = gamepad.left_stick_button;
        map.right_stick_button = gamepad.right_stick_button;
    }


    public void update()
    {
        if (!gamepad.dpad_up && previousButtonStates.dpad_up)
        {
            this.toggle.dpad_up = !this.toggle.dpad_up;
        }
        if (!gamepad.dpad_down && previousButtonStates.dpad_down)
        {
            this.toggle.dpad_down = !this.toggle.dpad_down;
        }
        if (!gamepad.dpad_left && previousButtonStates.dpad_left)
        {
            this.toggle.dpad_left = !this.toggle.dpad_left;
        }
        if (!gamepad.dpad_right && previousButtonStates.dpad_right)
        {
            this.toggle.dpad_right = !this.toggle.dpad_right;
        }
        if (!gamepad.a && previousButtonStates.a)
        {
            this.toggle.a = !this.toggle.a;
        }
        if (!gamepad.b && previousButtonStates.b)
        {
            this.toggle.b = !this.toggle.b;
        }
        if (!gamepad.x && previousButtonStates.x)
        {
            this.toggle.x = !this.toggle.x;
        }
        if (!gamepad.y && previousButtonStates.y)
        {
            this.toggle.y = !this.toggle.y;
        }
        if (!gamepad.guide && previousButtonStates.guide)
        {
            this.toggle.guide = !this.toggle.guide;
        }
        if (!gamepad.start && previousButtonStates.start)
        {
            this.toggle.start = !this.toggle.start;
        }
        if (!gamepad.back && previousButtonStates.back)
        {
            this.toggle.back = !this.toggle.back;
        }
        if (!gamepad.right_bumper && previousButtonStates.right_bumper)
        {
            this.toggle.right_bumper = !this.toggle.right_bumper;
        }
        if (!gamepad.left_bumper && previousButtonStates.left_bumper)
        {
            this.toggle.left_bumper = !this.toggle.left_bumper;
        }
        if (!gamepad.left_stick_button && previousButtonStates.left_stick_button)
        {
            this.toggle.left_stick_button = !this.toggle.left_stick_button;
        }
        if (!gamepad.right_stick_button && previousButtonStates.right_stick_button)
        {
            this.toggle.right_stick_button = !this.toggle.right_stick_button;
        }

        updateButtonMap(previousButtonStates);
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

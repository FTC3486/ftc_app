package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Jacob on 11/28/2015.
 */
public class GamepadWrapper
{
    private final double leftStickThreshold;
    private final double rightStickThreshold;

    public GamepadWrapper() {
        this.leftStickThreshold = 0.1;
        this.rightStickThreshold = 0.1;
    }

    public GamepadWrapper(double leftStickThreshold, double rightStickThreshold) {
        this.leftStickThreshold = leftStickThreshold;
        this.rightStickThreshold = rightStickThreshold;
    }

    public double getLeftStickThreshold() {
        return leftStickThreshold;
    }

    public double getRightStickThreshold() {
        return rightStickThreshold;
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
    public ButtonMap onPress = new ButtonMap();
    private ButtonMap previousButtonStates = new ButtonMap();

    private void savePreviousButtonStates(Gamepad gamepad)
    {
        previousButtonStates.dpad_up = gamepad.dpad_up;
        previousButtonStates.dpad_down = gamepad.dpad_down;
        previousButtonStates.dpad_right = gamepad.dpad_right;
        previousButtonStates.dpad_left = gamepad.dpad_left;
        previousButtonStates.a = gamepad.a;
        previousButtonStates.b = gamepad.b;
        previousButtonStates.x = gamepad.x;
        previousButtonStates.y = gamepad.y;
        previousButtonStates.guide = gamepad.guide;
        previousButtonStates.start = gamepad.start;
        previousButtonStates.back = gamepad.back;
        previousButtonStates.right_bumper = gamepad.right_bumper;
        previousButtonStates.left_bumper = gamepad.left_bumper;
        previousButtonStates.left_stick_button = gamepad.left_stick_button;
        previousButtonStates.right_stick_button = gamepad.right_stick_button;
    }
    
    public void update(Gamepad gamepad)
    {
        onPress.dpad_up = (gamepad.dpad_up && !previousButtonStates.dpad_up);
        onPress.dpad_down = (gamepad.dpad_down && !previousButtonStates.dpad_down);
        onPress.dpad_left = (gamepad.dpad_left && !previousButtonStates.dpad_left);
        onPress.dpad_right = (gamepad.dpad_right && !previousButtonStates.dpad_right);
        onPress.a = (gamepad.a && !previousButtonStates.a);
        onPress.b = (gamepad.b && !previousButtonStates.b);
        onPress.x = (gamepad.x && !previousButtonStates.x);
        onPress.y = (gamepad.y && !previousButtonStates.y);
        onPress.guide = (gamepad.guide && !previousButtonStates.guide);
        onPress.start = (gamepad.start && !previousButtonStates.start);
        onPress.back = (gamepad.back && !previousButtonStates.back);
        onPress.right_bumper = (gamepad.right_bumper && !previousButtonStates.right_bumper);
        onPress.left_bumper = (gamepad.left_bumper && !previousButtonStates.left_bumper);
        onPress.left_stick_button = (gamepad.left_stick_button && !previousButtonStates.left_stick_button);
        onPress.right_stick_button = (gamepad.right_stick_button && !previousButtonStates.right_stick_button);

        updateToggleButtonStates();

        savePreviousButtonStates(gamepad);
    }

    private void updateToggleButtonStates() {
        if (onPress.dpad_up)
        {
            toggle.dpad_up = !toggle.dpad_up;
        }
        if (onPress.dpad_down)
        {
            toggle.dpad_down = !toggle.dpad_down;
        }
        if (onPress.dpad_left)
        {
            toggle.dpad_left = !toggle.dpad_left;
        }
        if (onPress.dpad_right)
        {
            toggle.dpad_right = !toggle.dpad_right;
        }
        if (onPress.a)
        {
            toggle.a = !toggle.a;
        }
        if (onPress.b)
        {
            toggle.b = !toggle.b;
        }
        if (onPress.x)
        {
            toggle.x = !toggle.x;
        }
        if (onPress.y)
        {
            toggle.y = !toggle.y;
        }
        if (onPress.guide)
        {
            toggle.guide = !toggle.guide;
        }
        if (onPress.start)
        {
            toggle.start = !toggle.start;
        }
        if (onPress.back)
        {
            toggle.back = !toggle.back;
        }
        if (onPress.right_bumper)
        {
            toggle.right_bumper = !toggle.right_bumper;
        }
        if (onPress.left_bumper)
        {
            toggle.left_bumper = !toggle.left_bumper;
        }
        if (onPress.left_stick_button)
        {
            toggle.left_stick_button = !toggle.left_stick_button;
        }
        if (onPress.right_stick_button)
        {
            toggle.right_stick_button = !toggle.right_stick_button;
        }
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        if(toggle.dpad_up) {
            stringBuilder.append("tog.dpad_up ");
        }

        if(toggle.dpad_down) {
            stringBuilder.append("tog.dpad_down ");
        }

        if(toggle.dpad_left) {
            stringBuilder.append("tog.dpad_left ");
        }

        if(toggle.dpad_right) {
            stringBuilder.append("tog.dpad_right ");
        }

        if(toggle.a) {
            stringBuilder.append("tog.a ");
        }

        if(toggle.b) {
            stringBuilder.append("tog.b ");
        }

        if(toggle.x) {
            stringBuilder.append("tog.x ");
        }

        if(toggle.y) {
            stringBuilder.append("tog.y ");
        }

        if(toggle.guide) {
            stringBuilder.append("tog.guide ");
        }

        if(toggle.start) {
            stringBuilder.append("tog.start ");
        }

        if(toggle.back) {
            stringBuilder.append("tog.back ");
        }

        if(toggle.left_bumper) {
            stringBuilder.append("tog.left_bumper ");
        }

        if(toggle.right_bumper) {
            stringBuilder.append("tog.right_bumper ");
        }

        if(toggle.left_stick_button) {
            stringBuilder.append("tog.left_stick_button ");
        }

        if(toggle.right_stick_button) {
            stringBuilder.append("toggle.right_stick_button");
        }

        return stringBuilder.toString();
    }
}

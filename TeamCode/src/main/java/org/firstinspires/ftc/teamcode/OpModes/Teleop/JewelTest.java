package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotConfiguration.RelicRecovery.RelicRecoveryRobot;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.GamepadWrapper;

/**
 * Created by 3486 on 7/15/2017.
 */

@TeleOp
public class JewelTest extends OpMode {
    GamepadWrapper joy1;
    RelicRecoveryRobot relicRecoveryRobot = new RelicRecoveryRobot(hardwareMap);

    @Override
    public void init() {
        joy1 = new GamepadWrapper();
        relicRecoveryRobot.initialize();
    }

    @Override
    public void loop() {
        joy1.update(gamepad1);

        if (gamepad1.left_bumper) {
            relicRecoveryRobot.jewelArm.fullyRetract();
        } else if (gamepad1.right_bumper) {
            relicRecoveryRobot.jewelArm.fullyExtend();
        }

        // TODO: jewelColor should be private. Telemetry should be exposed through toString methods
        telemetry.addData("Green Value", relicRecoveryRobot.jewelColorSensor.green());
        telemetry.addData("Blue Value", relicRecoveryRobot.jewelColorSensor.blue());
        telemetry.addData("Red Value", relicRecoveryRobot.jewelColorSensor.red());
        telemetry.addData("Alpha Value", relicRecoveryRobot.jewelColorSensor.alpha());
        telemetry.addData("ARGB Value", relicRecoveryRobot.jewelColorSensor.argb());
        telemetry.addData("JewelServo", relicRecoveryRobot.jewelArm);
        telemetry.update();
    }
}
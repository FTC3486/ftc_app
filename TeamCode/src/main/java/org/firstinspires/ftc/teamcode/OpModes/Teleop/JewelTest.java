package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotConfiguration.RelicRecovery.RelicRecoveryRobot;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.GamepadWrapper;

/**
 * Created by 3486 on 7/15/2017.
 */

@TeleOp(name = "JewelTest Teleop", group = "Teleop2017")
public class JewelTest extends OpMode {
    //GamepadWrapper joy1;
    //RelicRecoveryRobot robotRecoveryRobot = new RelicRecoveryRobot(hardwareMap);

    //Declare parts of the robot that will be used by this Teleop
    private RelicRecoveryRobot robotRelicRecoveryRobot;
    private GamepadWrapper joy1 = new GamepadWrapper();

    @Override
    public void init() {
        joy1 = new GamepadWrapper();
        robotRelicRecoveryRobot.initialize();
    }

    @Override
    public void loop() {
        joy1.update(gamepad1);

        if (gamepad1.left_bumper) {
            robotRelicRecoveryRobot.jewelArm.fullyRetract();
        } else if (gamepad1.right_bumper) {
            robotRelicRecoveryRobot.jewelArm.fullyExtend();
        }

        // TODO: jewelColor should be private. Telemetry should be exposed through toString methods
        telemetry.addData("Green Value", robotRelicRecoveryRobot.jewelColorSensor.green());
        telemetry.addData("Blue Value", robotRelicRecoveryRobot.jewelColorSensor.blue());
        telemetry.addData("Red Value",robotRelicRecoveryRobot.jewelColorSensor.red());
        telemetry.addData("Alpha Value", robotRelicRecoveryRobot.jewelColorSensor.alpha());
        telemetry.addData("ARGB Value", robotRelicRecoveryRobot.jewelColorSensor.argb());
        telemetry.addData("JewelServo", robotRelicRecoveryRobot.jewelArm);
        telemetry.update();
    }
}
package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotConfiguration.VelocityVortex.Mammut;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.GamepadWrapper;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.TeleopDriver;


/**
 * Created by John Paul Ashour on 11/5/2016.
 */
@TeleOp
@Disabled
public class MammutTeleop extends OpMode {
    private final GamepadWrapper joy1 = new GamepadWrapper();
    private final GamepadWrapper joy2 = new GamepadWrapper();
    private final Mammut mammut = new Mammut(hardwareMap);
    private TeleopDriver driver;

    @Override
    public void init() {
        mammut.initialize();
        driver = new TeleopDriver(mammut);
    }

    @Override
    public void loop() {
        joy1.update(gamepad1);
        joy2.update(gamepad2);

        if (gamepad1.y) {
            mammut.pickup.reverse();
        } else if (joy1.toggle.x) {
            mammut.pickup.run();
        } else {
            mammut.pickup.stop();
        }

        if (gamepad2.right_bumper) {
            mammut.troughGate.open();
        } else {
            mammut.troughGate.close();
        }


        if (joy2.toggle.left_bumper) {
            mammut.accelerator.rampup();
        } else {
            mammut.accelerator.stop();
            mammut.accelerator.acceleratorPower = 0;
        }


        if (gamepad1.right_bumper) {
            mammut.column.extend();
        } else if (gamepad1.right_trigger > 0.5) {
            mammut.column.retract();
        } else {
            mammut.column.stop();
        }

        if (gamepad1.a & gamepad2.a) {
            mammut.tuskGate.releaseTusks();
        }


        if (mammut.tuskGate.isOpen) {
            if (joy1.toggle.b) {
                mammut.capballHolder.captured();
            } else {
                mammut.capballHolder.released();
            }

        } else {
            mammut.capballHolder.colapsed();
        }

        telemetry.addData("Mammut", mammut);
        telemetry.update();
    }

    @Override
    public void stop() {
    }
}

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by John Paul Ashour on 11/5/2016.
 */
@TeleOp(name="Teleop version 1.0", group="Teleop2016")
public class Teleop_Iteration_1 extends OpMode{
    GamepadWrapper joy1;
    GamepadWrapper joy2;
    Drivetrain drivetrain = new Drivetrain();
    ParticleAcclerator acclerator1;
    ParticleAcclerator acclerator2;
    Pickup pickup;
    TroughGate troughGate;
    Column column;
    TuskGate tuskGate;



    @Override
    public void init() {
        drivetrain.init(hardwareMap);
        pickup = new Pickup("Pickup", hardwareMap);
        troughGate = new TroughGate("Trough Gate", hardwareMap);
        acclerator1 = new ParticleAcclerator("Acclerator 1", hardwareMap);
        acclerator2 = new ParticleAcclerator("Acclerator 2", hardwareMap);
        column = new Column("Column", hardwareMap);
        tuskGate = new TuskGate("Tusk Gate", hardwareMap);
        joy1 = new GamepadWrapper();
        joy2 = new GamepadWrapper();
    }

    @Override
    public void init_loop(){
    }

    @Override
    public void start(){
    }

    @Override
    public void loop(){
        joy1.update(gamepad1);
        joy2.update(gamepad2);

        double left;
        double right;
        //double acc;

        //acc = gamepad1.right_bumper;
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;

        drivetrain.Left1.setPower(left);
        drivetrain.Left2.setPower(left);
        drivetrain.Right1.setPower(right);
        drivetrain.Right2.setPower(right);

        if(joy1.toggle.dpad_left){
            pickup.run();
        }
        else {
            pickup.stop();
        }

        if(joy1.toggle.left_bumper){
            troughGate.openGate();
        }
        else {
            troughGate.closeGate();
        }

        if(joy1.toggle.right_bumper){
            acclerator1.run();
        }
        else{
            acclerator1.stop();
        }

        if(joy1.toggle.right_bumper){
            acclerator2.run();
        }
        else{
            acclerator2.stop();
        }

        if(gamepad1.dpad_up){
            column.extend();
        }
        else if(gamepad1.dpad_down){
            column.retract();
        }
        else{
            column.stop();
        }

        if(gamepad1.left_stick_button){
            tuskGate.releaseTusks();
        }
        else{
            tuskGate.closeGate();
        }


        telemetry.addData("left",  "%.2f", left);
        telemetry.addData("right", "%.2f", right);
        telemetry.addData("Accelerator 1", acclerator1);
        telemetry.addData("Accelerator 2", acclerator2);
        telemetry.addData("Pickup",pickup);
        telemetry.addData("Trough Gate", troughGate);
        telemetry.addData("Tusk Gate", tuskGate);
        telemetry.addData("Column",column);
        //telemetry.addData("",);
        //telemetry.addData("",);
    }

    @Override
    public void stop(){
    }

}

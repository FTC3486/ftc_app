package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static android.os.SystemClock.sleep;


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
    CapballHolder capballHolder;



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
        capballHolder = new CapballHolder("Capball Holder", hardwareMap);

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


        if(joy1.toggle.x) {
            left = get_scaled_power_from_gamepad_stick(gamepad1.left_stick_y);
            right = get_scaled_power_from_gamepad_stick(gamepad1.right_stick_y);
        }else {
            left = get_scaled_power_from_gamepad_stick(-gamepad1.right_stick_y);
            right= get_scaled_power_from_gamepad_stick(-gamepad1.left_stick_y);
        }
        drivetrain.Left1.setPower(left);
        drivetrain.Left2.setPower(left);
        drivetrain.Right1.setPower(right);
        drivetrain.Right2.setPower(right);






        if(joy2.toggle.x){
            pickup.run();
        }
        else {
            pickup.stop();
        }

        if(gamepad2.right_bumper){
            troughGate.openGate();
        }
        else {
            troughGate.closeGate();
        }

        if(joy2.toggle.left_bumper){
           while(acclerator1.Acclerator.getPower() >-1.0){
               acclerator1.rampup();
           }
        }
        else {
            acclerator1.stop();
        }
        if (joy2.toggle.left_bumper){
            while (acclerator2.Acclerator.getPower() >-1.0){
                acclerator2.rampup();
            }
        }
        else {
            acclerator2.stop();
        }
        /*if(joy2.toggle.left_bumper){
            acclerator1.run();
            acclerator2.run();
        }
        else if(joy2.toggle.y){
            acclerator1.idle();
        }
        else{
            acclerator1.stop();
            acclerator2.stop();
        }*/


        if(gamepad1.right_bumper){
            column.extend();
        }
        else if(gamepad1.right_trigger > 0.5){
            column.retract();
        }
        else{
            column.stop();
        }

        if(gamepad1.a & gamepad2.a){
            tuskGate.releaseTusks();
        }


        if(tuskGate.isOpen){
            if (joy1.toggle.b){
                capballHolder.captured();
            }else {
                capballHolder.released();
            }

        }else {
            capballHolder.colapsed();
        }

        telemetry.addData("left",  "%.2f", left);
        telemetry.addData("right", "%.2f", right);
        telemetry.addData("Accelerator 1", acclerator1);
        telemetry.addData("Accelerator 2", acclerator2);
        telemetry.addData("Pickup",pickup.PickupState);
        telemetry.addData("Trough Gate", troughGate.troughServoState);
        telemetry.addData("Tusk Gate", tuskGate.tuskServoState);
        telemetry.addData("Column",column);
        telemetry.addData("Capball", capballHolder.capballHolderServoState);

    }

    @Override
    public void stop(){
    }

    private double get_scaled_power_from_gamepad_stick(float stick)
    {
        return -(Math.signum(stick) * ((Math.pow(stick, 2) * (1 - .1)) + .1));
    }

}

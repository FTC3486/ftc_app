package org.firstinspires.ftc.teamcode.OpModes.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Drivetrain;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.GamepadWrapper;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.TeleopDriver;
import org.firstinspires.ftc.teamcode.Subsystems.BaconActivator;
import org.firstinspires.ftc.teamcode.Subsystems.CapballHolder;
import org.firstinspires.ftc.teamcode.Subsystems.Column;
import org.firstinspires.ftc.teamcode.Subsystems.ParticleAccelerator;
import org.firstinspires.ftc.teamcode.Subsystems.Pickup;
import org.firstinspires.ftc.teamcode.Subsystems.TroughGate;
import org.firstinspires.ftc.teamcode.Subsystems.TuskGate;


/**
 * Created by John Paul Ashour on 11/5/2016.
 */
@TeleOp(name="Teleop With DrivetrainBuilder", group="Teleop2016")
@Disabled
public class Teleop_DrivetrainBuilder extends OpMode{
    GamepadWrapper joy1;
    GamepadWrapper joy2;
    Drivetrain drivetrain;
    ParticleAccelerator accelerator;
    Pickup pickup;
    TroughGate troughGate;
    Column column;
    TuskGate tuskGate;
    CapballHolder capballHolder;
    BaconActivator baconActivator;
    TeleopDriver teleopDriver;

    public DcMotor Left1 = null;
    public DcMotor Left2 = null;
    public DcMotor Right1 = null;
    public DcMotor Right2 = null;




    @Override
    public void init() {
        Left1 = hardwareMap.dcMotor.get("Left 1");
        Left2 = hardwareMap.dcMotor.get("Left 2");
        Right1 = hardwareMap.dcMotor.get("Right 1");
        Right2 = hardwareMap.dcMotor.get("Right 2");

        Left1.setDirection(DcMotor.Direction.REVERSE);
        Left2.setDirection(DcMotor.Direction.REVERSE);
        Right1.setDirection(DcMotor.Direction.FORWARD);
        Right2.setDirection(DcMotor.Direction.FORWARD);
        drivetrain = new Drivetrain.Builder()
                .addLeftMotor(Left1)
                .addLeftMotorWithEncoder(Left2)
                .addRightMotor(Right1)
                .addRightMotorWithEncoder(Right2)
                .build();
        teleopDriver = new TeleopDriver(this, drivetrain);
        pickup = new Pickup("Pickup", hardwareMap);
        troughGate = new TroughGate("Trough Gate", hardwareMap);
        accelerator = new ParticleAccelerator("Accelerator 1", hardwareMap);
        column = new Column("Column 1","Column 2", hardwareMap);
        tuskGate = new TuskGate("Tusk Gate", hardwareMap);
        joy1 = new GamepadWrapper();
        joy2 = new GamepadWrapper();
        capballHolder = new CapballHolder("Capball Holder", hardwareMap);
        baconActivator = new BaconActivator("Bacon Activator", hardwareMap);
        accelerator.acceleratorPower = 0;

        baconActivator.armUp();
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


        if(joy1.toggle.x) {
            teleopDriver.tank_drive(gamepad1, TeleopDriver.Direction.BACKWARD);
        } else{
            teleopDriver.tank_drive(gamepad1, TeleopDriver.Direction.FORWARD);
        }



        if(gamepad2.y){
            pickup.reverse();
        }
        else if(joy2.toggle.x){
            pickup.run();
        }else{
            pickup.stop();
        }

        if(gamepad2.right_bumper){
            troughGate.openGate();
        }
        else {
            troughGate.closeGate();
        }



        if (joy2.toggle.left_bumper){
            accelerator.rampup();

        }
        else {
            accelerator.stop();

            //accelerator.acceleratorPower = 0;

        }


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

        /*if(gamepad1.dpad_up){
            baconActivator.armUp();
        } else if(gamepad1.dpad_down){
            baconActivator.armDown();
        }else if (gamepad1.dpad_left){
            baconActivator.armPressing();
        }else if (gamepad1.dpad_right){
            baconActivator.sensorScanning();
        }else {
            baconActivator.armDown();
        }*/


        telemetry.addData("Accelerator 1", accelerator);

        telemetry.addData("Pickup",pickup);
        telemetry.addData("Trough Gate", troughGate);
        telemetry.addData("Tusk Gate", tuskGate);
        telemetry.addData("Column",column);
        telemetry.addData("Capball", capballHolder);
        telemetry.addData("Bacon Activator", baconActivator);
        telemetry.update();
    }

    @Override
    public void stop(){
    }


}

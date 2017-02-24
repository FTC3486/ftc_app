package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


/**
 * Created by John Paul Ashour on 11/5/2016.
 */
@TeleOp(name="Teleop Encoder Test", group="Teleop2016")
public class Teleop_Encoder_Test extends OpMode{
    GamepadWrapper joy1;
    GamepadWrapper joy2;
    Drivetrain driveTrain;
    ParticleAcclerator accelerator1;
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
        driveTrain = new Drivetrain.Builder()
                .addLeftMotorWithEncoder(Left1)
                .addLeftMotorWithEncoder(Left2)
                .addRightMotorWithEncoder(Right1)
                .addRightMotorWithEncoder(Right2)
                .build();
        teleopDriver = new TeleopDriver(this, driveTrain);
        pickup = new Pickup("Pickup", hardwareMap);
        troughGate = new TroughGate("Trough Gate", hardwareMap);
        accelerator1 = new ParticleAcclerator("Accelerator 1", hardwareMap);
        column = new Column("Column 1", "Column 2", hardwareMap);
        tuskGate = new TuskGate("Tusk Gate", hardwareMap);
        joy1 = new GamepadWrapper();
        joy2 = new GamepadWrapper();
        capballHolder = new CapballHolder("Capball Holder", hardwareMap);
        baconActivator = new BaconActivator("Bacon Activator", hardwareMap);
        accelerator1.accleratorPower = 0;
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



        if (joy1.toggle.y){
            driveTrain.setPowers(0.5, 0.5);
        }else if(joy1.toggle.a){
            driveTrain.setPowers(-0.5, -0.5);
        }else if(joy1.toggle.y && joy1.toggle.a){
            driveTrain.haltDrive();
        }else{
            driveTrain.haltDrive();
        }
        if(gamepad1.b){
            driveTrain.resetMotorEncoders();
        }


        telemetry.addData("Left1 Encoder Counts", Left1.getCurrentPosition());
        telemetry.addData("Left2 Encoder Counts", Left2.getCurrentPosition());
        telemetry.addData("Right1 Encoder Counts", Right1.getCurrentPosition());
        telemetry.addData("Right2 Encoder Counts", Right2.getCurrentPosition());
        telemetry.update();
    }

    @Override
    public void stop(){
    }


}

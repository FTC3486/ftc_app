package org.firstinspires.ftc.teamcode.OpModes.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


/**
 * Created by John Paul Ashour on 11/5/2016.
 */
@TeleOp(name="Teleop Encoder Test", group="Teleop2016")
@Disabled
public class Teleop_Encoder_Test extends OpMode{
/*    GamepadWrapper joy1;
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
*/
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
  /*      drivetrain = new Drivetrain.Builder()
                .addLeftMotorWithEncoder(Left1)
                .addLeftMotorWithEncoder(Left2)
                .addRightMotorWithEncoder(Right1)
                .addRightMotorWithEncoder(Right2)
                .build();
        teleopDriver = new TeleopDriver(this, drivetrain);
        pickup = new Pickup("Pickup", hardwareMap);
        troughGate = new TroughGate("Trough Gate", hardwareMap);
        accelerator = new ParticleAccelerator("Accelerator 1", hardwareMap);
        column = new Column("Column 1", "Column 2", hardwareMap);
        tuskGate = new TuskGate("Tusk Gate", hardwareMap);
        joy1 = new GamepadWrapper();
        joy2 = new GamepadWrapper();
        capballHolder = new CapballHolder("Capball Holder", hardwareMap);
        baconActivator = new BaconActivator("Bacon Activator", hardwareMap);
        accelerator.acceleratorPower = 0;
        baconActivator.armUp();*/


    }

    @Override
    public void init_loop(){
    }

    @Override
    public void start(){
    }

    @Override
    public void loop(){
        //double negativepointfive = -0.5;
        //joy1.update(gamepad1);
        //joy2.update(gamepad2);



        if (gamepad1.y){
            Left1.setPower(0.5);
            Left2.setPower(0.5);
            Right1.setPower(0.5);
            Right2.setPower(0.5);
            //drivetrain.setPowers(0.5, 0.5);
        }else if(gamepad2.a){
            Left1.setPower(-0.5);
            Left2.setPower(-0.5);
            Right1.setPower(-0.5);
            Right2.setPower(-0.5);
        }else if(gamepad1.y && gamepad2.a){
            Left1.setPower(0);
            Left2.setPower(0);
            Right1.setPower(0);
            Right2.setPower(0);
            //drivetrain.haltDrive();
        }else{
            Left1.setPower(0);
            Left2.setPower(0);
            Right1.setPower(0);
            Right2.setPower(0);
            //drivetrain.haltDrive();
        }
        if(gamepad1.b){
            Left1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Left1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Left2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Left2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Right1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Right1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Right2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Right2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //drivetrain.resetMotorEncoders();
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

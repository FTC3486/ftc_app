package org.firstinspires.ftc.teamcode.RobotConfiguration.VelocityVortex;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotCoreExtensions.ContinuousServo;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Drivable;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Drivetrain;
import org.firstinspires.ftc.teamcode.RobotCoreExtensions.Initializable;
import org.firstinspires.ftc.teamcode.Subsystems.BaconActivator;
import org.firstinspires.ftc.teamcode.Subsystems.CapballHolder;
import org.firstinspires.ftc.teamcode.Subsystems.Column;
import org.firstinspires.ftc.teamcode.Subsystems.ParticleAccelerator;
import org.firstinspires.ftc.teamcode.Subsystems.OpenCloseServo;
import org.firstinspires.ftc.teamcode.Subsystems.ReversableMotor;
import org.firstinspires.ftc.teamcode.Subsystems.TuskGate;

public class Mammut implements Initializable, Drivable {
    // Components
    private final Drivetrain drivetrain;
    public final ParticleAccelerator accelerator;
    public final ReversableMotor pickup;
    public final OpenCloseServo troughGate;
    public final Column column;
    public final TuskGate tuskGate;
    public final CapballHolder capballHolder;
    public final BaconActivator baconActivator;

    public Mammut(HardwareMap hardwareMap) {
        DcMotor left1 = hardwareMap.dcMotor.get("Left 1");
        DcMotor left2 = hardwareMap.dcMotor.get("Left 2");
        DcMotor right1 = hardwareMap.dcMotor.get("Right 1");
        DcMotor right2 = hardwareMap.dcMotor.get("Right 2");
        left1.setDirection(DcMotor.Direction.REVERSE);
        left2.setDirection(DcMotor.Direction.REVERSE);
        right1.setDirection(DcMotor.Direction.FORWARD);
        right2.setDirection(DcMotor.Direction.FORWARD);

        drivetrain = new Drivetrain.Builder()
                .addLeftMotor(left1)
                .addLeftMotorWithEncoder(left2)
                .addRightMotor(right1)
                .addRightMotorWithEncoder(right2)
                .build();

        pickup = new ReversableMotor(hardwareMap.dcMotor.get("Pickup"), 1.0);

        troughGate = new OpenCloseServo(hardwareMap.servo.get("Trough Gate"),
                0.1, 0.6, 0.1);

        accelerator = new ParticleAccelerator("Accelerator 1", hardwareMap);
        column = new Column("Column 1", "Column 2", hardwareMap);
        tuskGate = new TuskGate("Tusk Gate", hardwareMap);
        capballHolder = new CapballHolder("Capball Holder", hardwareMap);
        baconActivator = new BaconActivator("Bacon Activator", hardwareMap);
    }

    @Override
    public Drivetrain getDrivetrain() {
        return drivetrain;
    }

    @Override
    public void initialize() {
        accelerator.acceleratorPower = 0;
        baconActivator.armUp();
    }

    @Override
    public String toString() {
        return "Mammut{" +
                "drivetrain=" + drivetrain +
                ", accelerator=" + accelerator +
                ", pickup=" + pickup +
                ", troughGate=" + troughGate +
                ", column=" + column +
                ", tuskGate=" + tuskGate +
                ", capballHolder=" + capballHolder +
                ", baconActivator=" + baconActivator +
                '}';
    }
}

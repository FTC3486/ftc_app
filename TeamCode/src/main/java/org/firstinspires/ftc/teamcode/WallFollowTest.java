package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Owner_2 on 1/31/2017.
 */
@Autonomous(name = "Line Follow Test", group = "Test")
@Disabled
public class WallFollowTest extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    ModernRoboticsI2cRangeSensor rangeSensor1;
    ModernRoboticsI2cRangeSensor rangeSensor2;
    Drivetrain driveTrain;
    public DcMotor Left1 = null;
    public DcMotor Left2 = null;
    public DcMotor Right1 = null;
    public DcMotor Right2 = null;

    //Raw value is between 0 and 1
    double odsReadingRaw1;
    double odsReadingRaw2;

    // odsReadingRaw to the power of (-0.5)
    static double odsReadingLinear1;
    static double odsReadingLinear2;

    @Override
    public void runOpMode() throws InterruptedException {
        Left1 = hardwareMap.dcMotor.get("Left 1");
        Left2 = hardwareMap.dcMotor.get("Left 2");
        Right1 = hardwareMap.dcMotor.get("Right 1");
        Right2 = hardwareMap.dcMotor.get("Right 2");

        Left1.setDirection(DcMotor.Direction.REVERSE);
        Left2.setDirection(DcMotor.Direction.REVERSE);
        Right1.setDirection(DcMotor.Direction.FORWARD);
        Right2.setDirection(DcMotor.Direction.FORWARD);
        driveTrain = new Drivetrain.Builder()
                .addLeftMotor(Left1)
                .addLeftMotorWithEncoder(Left2)
                .addRightMotor(Right1)
                .addRightMotorWithEncoder(Right2)
                .build();
        rangeSensor1 = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "Range 1");
        rangeSensor2 = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "Range 2");

        waitForStart();

        while (opModeIsActive()) {

            odsReadingRaw1 = rangeSensor1.rawUltrasonic() / 5;                   //update raw value (This function now returns a value between 0 and 5 instead of 0 and 1 as seen in the video)
            odsReadingRaw2 = rangeSensor2.rawUltrasonic() / 5;
            odsReadingLinear1 = Math.pow(odsReadingRaw1, 0.5);                //calculate linear value
            odsReadingLinear2 = Math.pow(odsReadingRaw2, 0.5);

            //The below two equations operate the motors such that both motors have the same speed when the robot is the right distance from the wall
            //As the robot gets closer to the wall, the left motor received more power and the right motor received less power
            //The opposite happens as the robot moves further from the wall. This makes a proportional and elegant wall following robot.
            //See the video explanation on the Modern Robotics YouTube channel, the ODS product page, or modernroboticsedu.com.
            Left1.setPower(odsReadingLinear1 * 2);
            Left2.setPower(odsReadingLinear1 * 2);
            Right1.setPower(0.5 - (odsReadingLinear1 * 2));
            Right2.setPower(0.5 - (odsReadingLinear1 * 2));

            telemetry.addData("ODS Raw 1:", odsReadingRaw1);
            telemetry.addData("ODS Raw 2:", odsReadingRaw2);
            telemetry.addData("ODS linear 1", odsReadingLinear1);
            telemetry.addData("ODS linear 2", odsReadingLinear2);
            telemetry.addData("Motor Left 1", Left1.getPower());
            telemetry.addData("Motor Left 2", Left2.getPower());
            telemetry.addData("Motor Right 1", Right1.getPower());
            telemetry.addData("Motor Right 2", Right2.getPower());
            telemetry.update();
        }
    }

}

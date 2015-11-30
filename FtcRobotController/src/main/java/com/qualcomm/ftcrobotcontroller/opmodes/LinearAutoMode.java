/* Copyright (c) 2015 Qualcomm Technologies Inc
All rights reserved.
Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:
Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.
Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.
Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.
NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.jacobamason.FTCRC_Extensions.Drive;
import com.jacobamason.FTCRC_Extensions.ExtendedServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Matthew on 11/25/2015.
 */
public class LinearAutoMode extends LinearOpMode {
    //DriveTrain
    Drive driver;
    DcMotor leftfront,leftback,rightfront,rightback;

    //Grappling Hook
    DcMotor tapeMotor;
    ExtendedServo tapeTilt;
    double tapeTiltMonitorPosition;
    double initialTilt = 0.5;
    DcMotor winchMotor;

    //Scoop
    ExtendedServo lr;//left-right, up-down
    double r_state = 0;
    double centerPosition = 0.2; // left 8, center 47, right 85
    double leftPosition = 0.045;
    double rightPosition = 0.35;
    Servo ud;
    double upPosition = 0.55;
    double downPosition = 0.89;

    //Zoop-Zoop
    Servo zleft, zright;
    double zleftInit = 0.3; //0
    double zleftUp = .65;
    double zrightInit = 0.6; //255
    double zrightUp = .2;
    double zleftdown = .85; //140 down, 0 up
    double zrightdown = .01;//80 down, 255 up

    //BlockGate
    Servo blockGate;
    double bgopen = 0.7;
    double bgclose = 0.13;

    //Sweeper;
    DcMotor Sweeper;

    // Safety Hook
    DcMotor safetyHook;

    double b_state = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        //Drivetrain
        leftfront = hardwareMap.dcMotor.get("leftfront");
        leftback = hardwareMap.dcMotor.get("leftback");
        rightfront = hardwareMap.dcMotor.get("rightfront");
        rightback = hardwareMap.dcMotor.get("rightback");
        driver = new Drive(this, 0.15f);

        //Scoop
        lr = new ExtendedServo(hardwareMap.servo.get("lr"));
        lr.setPosition(centerPosition);

        ud = hardwareMap.servo.get("ud");
        ud.setPosition(downPosition);

        //Zoop-Zoop
        zleft = hardwareMap.servo.get("zleft");
        zleft.setPosition(zleftInit);

        zright = hardwareMap.servo.get("zright");
        zright.setPosition(zrightInit);

        //Block Gate
        blockGate = hardwareMap.servo.get("bG");
        blockGate.setPosition(bgclose);

        // Sweeper
        Sweeper = hardwareMap.dcMotor.get("sw");

        //Grappling Hook
        tapeMotor = hardwareMap.dcMotor.get("tapeM");
        tapeTilt = new ExtendedServo(hardwareMap.servo.get("tapeT"));
        tapeTilt.setPosition(initialTilt);
        winchMotor = hardwareMap.dcMotor.get("wM");

        // Safety hook
        safetyHook = hardwareMap.dcMotor.get("safetyHook");


        // wait for the start button to be pressed
        waitForStart();

        //Put Code Here:
        leftback.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightback.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        leftback.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightback.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        while(!(Math.abs(leftback.getCurrentPosition()) > 1000)) {
            leftfront.setPower(-0.5f);
            leftback.setPower(0.5f);
            telemetry.addData("LeftBack Encoders:", leftback.getCurrentPosition());
        }

        leftfront.setPower(0);
        leftback.setPower(0);

        Sweeper.setPower(1.0);
        sleep(1000);
        Sweeper.setPower(0.0);










        // wait for the IR Seeker to detect a signal
        /*while (!irSeeker.signalDetected()) {
            sleep(1000);
        }

        if (irSeeker.getAngle() < 0) {
            // if the signal is to the left move left
            motorRight.setPower(MOTOR_POWER);
            motorLeft.setPower(-MOTOR_POWER);
        } else if (irSeeker.getAngle() > 0) {
            // if the signal is to the right move right
            motorRight.setPower(-MOTOR_POWER);
            motorLeft.setPower(MOTOR_POWER);
        }

        // wait for the robot to center on the beacon
        while (irSeeker.getAngle() != 0) {
            waitOneFullHardwareCycle();
        }

        // now approach the beacon
        motorRight.setPower(MOTOR_POWER);
        motorLeft.setPower(MOTOR_POWER);

        // wait until we are close enough
        while (irSeeker.getStrength() < HOLD_IR_SIGNAL_STRENGTH) {
            waitOneFullHardwareCycle();
        }

        // stop the motors
        motorRight.setPower(0);
        motorLeft.setPower(0);*/
    }
}

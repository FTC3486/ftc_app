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

import com.FTC3486.FTCRC_Extensions.Drive;
import com.qualcomm.ftcrobotcontroller.opmodes.Subsystems.ParkingBrake;
import com.qualcomm.ftcrobotcontroller.opmodes.Subsystems.Pickup;
import com.qualcomm.ftcrobotcontroller.opmodes.Subsystems.Plow;
import com.qualcomm.ftcrobotcontroller.opmodes.Subsystems.TapeMeasure;
import com.qualcomm.ftcrobotcontroller.opmodes.Subsystems.Turret;
import com.qualcomm.ftcrobotcontroller.opmodes.Subsystems.Winch;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Matthew on 11/25/2015.
 */
public class LinearAutoMode extends LinearOpMode {
    ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    Drive driver;
    DcMotor leftfront, leftback, rightfront, rightback;
    TapeMeasure tapeMeasure;
    Winch winch;
    ParkingBrake parkingBrake;
    Turret turret;
    Plow plow;
    Pickup pickup;

    @Override
    public void runOpMode() throws InterruptedException {
        leftfront = hardwareMap.dcMotor.get("leftfront");
        leftback = hardwareMap.dcMotor.get("leftback");
        rightfront = hardwareMap.dcMotor.get("rightfront");
        rightback = hardwareMap.dcMotor.get("rightback");

        tapeMeasure = new TapeMeasure("tapeMotor", "tapeTilt", hardwareMap);
        winch = new Winch("winchMotor", hardwareMap);
        parkingBrake = new ParkingBrake("parkingBrake", hardwareMap);
        turret = new Turret("swivel", "extender", "dumper", hardwareMap);
        plow = new Plow("leftPlow", "rightPlow", hardwareMap);
        pickup = new Pickup("pickup", hardwareMap);

        // wait for the start button to be pressed
        waitForStart();

        while(leftback.getCurrentPosition() < 3000 && this.opModeIsActive()) {
            leftfront.setPower(.6f);
            leftback.setPower(.6f);
            rightfront.setPower(.6f);
            rightback.setPower(.6f);
        }

        leftfront.setPower(0.0f);
        leftback.setPower(0.0f);
        rightfront.setPower(0.0f);
        rightback.setPower(0.0f);




    }
}

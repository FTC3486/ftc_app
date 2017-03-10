package org.firstinspires.ftc.teamcode.Extension;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;

/**
 * Created by Matthew on 3/10/2017.
 */

public class RangeSensor {
    private I2cDeviceSynch reader;

    public RangeSensor(String sensorName, int address, HardwareMap hardwareMap) {
        I2cDevice sensor = hardwareMap.i2cDevice.get(sensorName);
        this.reader = new I2cDeviceSynchImpl(sensor, I2cAddr.create8bit(address), false);
        this.reader.engage();
    }

    public double getSideUltrasonicRange() {
        //Read 2 bytes at a time, starting at 0x04
        byte[] sideSensorCache = reader.read(0x04, 2);
        //sideSensorCache[0] holds Ultrasonic Values; & 0xFF converts the values to be in the range 0-255;
        return sideSensorCache[0] & 0xFF;
    }
}

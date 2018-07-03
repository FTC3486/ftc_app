package org.firstinspires.ftc.teamcode.RobotCoreExtensions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;

/**
 * Created by Matthew on 3/10/2017.
 */

/*
    Filename: RangeSensor.java

    Description:
        This Range Sensor class allows the robot to access two different range sensors at once. The
    original range sensor code wrote the data from both sensors to the same location, so it would not
    allow for two different sensors to be used at the same time. This class solves this problem by
    storing the range sensor data in specified locations (at the "address" specified in the constructor).

    Use:
        The Range Sensor class is used in the hardware configuration to create range sensors for the
    robot to use in a teleop or autonomous setting. A range sensor object should be created in the
    hardware configuration and accessed from a teleop file using an associated robot object. For
    example, type robot.hardwareConfiguration.rangeSensor.getUltraSonicRange() (using the specific
    robot class, hardware configuration, and rangeSensor objects) to access the data from a specific
    sensor.

    The second range sensor requires a different address to be programmed into the sensor.
 */


public class RangeSensor {
    private I2cDeviceSynch reader;

    public RangeSensor(String sensorName, int address, HardwareMap hardwareMap) {
        I2cDevice sensor = hardwareMap.i2cDevice.get(sensorName);
        this.reader = new I2cDeviceSynchImpl(sensor, I2cAddr.create8bit(address), false);
        this.reader.engage();
    }

    public int getUltrasonicRange() {
        //Read 2 bytes at a time, starting at 0x04
        byte[] sideSensorCache = reader.read(0x04, 2);
        //sideSensorCache[0] holds Ultrasonic Values; & 0xFF converts the values to be in the range 0-255;
        return sideSensorCache[0] & 0xFF;
    }
}

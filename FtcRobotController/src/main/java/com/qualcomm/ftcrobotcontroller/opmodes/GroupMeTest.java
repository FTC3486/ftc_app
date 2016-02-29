package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import net.jacobmason.GroupMe.Bot;

public class GroupMeTest extends OpMode {
    @Override
    public void init() {
    }

    @Override
    public void start() {
        Bot bot = new Bot("Waffles", "20217139");
        bot.say("Hello World!");
    }

    @Override
    public void loop() {
    }
}

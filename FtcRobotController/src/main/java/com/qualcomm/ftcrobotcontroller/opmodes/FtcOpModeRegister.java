package com.qualcomm.ftcrobotcontroller.opmodes;

import com.FTC3486.OpModes.BlueAutoPark;
import com.FTC3486.OpModes.BlueAutoStop;
import com.FTC3486.OpModes.RedAutoPark;
import com.FTC3486.OpModes.RedAutoStop;
import com.FTC3486.OpModes.StraightDefense;
import com.FTC3486.OpModes.TeleOp2016;
import com.FTC3486.OpModes.WaitBlueAutoStop;
import com.FTC3486.OpModes.WaitRedAutoStop;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

/**
 * Register Op Modes
 */
public class FtcOpModeRegister implements OpModeRegister {

  /**
   * The Op Mode Manager will call this method when it wants a list of all
   * available op modes. Add your op mode to the list to enable it.
   *
   * @param manager op mode manager
   */
  public void register(OpModeManager manager) {
    manager.register("NullOp", NullOp.class);
    manager.register("TeleOp2016", TeleOp2016.class);
    manager.register("RedAutoPark", RedAutoPark.class);
    manager.register("RedAutoStop", RedAutoStop.class);
    manager.register("WaitRedAutoStop", WaitRedAutoStop.class);
    manager.register("BlueAutoPark", BlueAutoPark.class);
    manager.register("BlueAutoStop", BlueAutoStop.class);
    manager.register("WaitBlueAutoStop", WaitBlueAutoStop.class);
    manager.register("StraightDefense", StraightDefense.class);
  }
}

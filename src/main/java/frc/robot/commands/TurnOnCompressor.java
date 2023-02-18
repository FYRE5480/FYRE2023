package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AirControl;

/** Turns on the compressor manually. */
public class TurnOnCompressor extends CommandBase {

    private final AirControl compressor;

    private boolean onOrOff;

    public TurnOnCompressor(AirControl subsystem, boolean onOrOff) {
        this.compressor = subsystem;
        this.onOrOff = onOrOff;
    }

    /** Turns the compressor on or off based on the boolean passed into the class. */
    public void checkCompressor() {
        if (onOrOff) {
            compressor.turnOnCompressor();
        } else {
            compressor.turnOffCompressor();
        }
    }
}

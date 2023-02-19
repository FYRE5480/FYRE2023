package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AirControl;

/** Turns on the compressor manually. */
public class TurnOnCompressor extends CommandBase {
    // Initialize our AirControl class for air control in the compressor. 
    private final AirControl compressor;

    // Initialize a boolean for checking if the compressor is on or off. 
    private boolean onOrOff = false;

    /**
     * Initializes a new TurnOnCompressor command based on the AirControl class.
     *
     * @param subsystem - The AirControl subsystem to built upon. 
     * @param onOrOff - The boolean to check if the compressor is on or off. 
     */
    public TurnOnCompressor(AirControl subsystem, boolean onOrOff) {
        this.compressor = subsystem;
        this.onOrOff = onOrOff;
    }

    /** 
     * Turns the compressor on or off based on the boolean passed into the class. 
     * */
    public void checkCompressor() {
        if (onOrOff) {
            compressor.turnOnCompressor();
        } else {
            compressor.turnOffCompressor();
        }
    }
}

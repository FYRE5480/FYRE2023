package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Claw;

/**
 * Handler containing methods relating to the actuation of the claw.
 */
public class ActuateClaw extends CommandBase {
    // Initialize our Claw subsystem.
    private Claw claw;

    /**
     * Initialize our actuation commands through our Claw subsystem.
     *
     * @param subsystem - The Claw subsystem to be used. 
     */
    public ActuateClaw(Claw subsystem) {
        this.claw = subsystem; 

        addRequirements(this.claw);
    }

    /**
     * Toggles the piston when the command is initialized. 
     */
    @Override
	public void initialize() {
        claw.togglePiston();
    }
}

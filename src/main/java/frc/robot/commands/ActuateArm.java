package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

/**
 * Handler containing methods relating to the actuation of the arm. 
 */
public class ActuateArm extends CommandBase {
    // Initialize the Arm subsystem. 
    private final Arm arm;

    /**
	 * Initialize our arm actuation through the Arm subsystem.
	 *
	 * @param arm - The Arm subsystem used by this command.
	 */
    public ActuateArm(Arm arm) {
        this.arm = arm;
        
        addRequirements(this.arm);
    }

    /**
     * Actuates the arm according to the current readings of the limit switches. 
     */
    public void actuateArm() {
        if (arm.getSwitchReading("upper")) {
            arm.actuateDown();
        } else if (arm.getSwitchReading("lower")) {
            arm.actuateUp(); 
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    	return false;
    }
}

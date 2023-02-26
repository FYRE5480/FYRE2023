package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Claw;

/**
 * Handler containing methods relating to the actuation of the claw.
 */
public class ActuateClaw extends CommandBase {
    // Initialize our Claw subsystem.
    private Claw claw;
    private String direction;

    /**
     * Initialize our actuation commands through our Claw subsystem.
     *
     * @param subsystem - The Claw subsystem to be used. 
     */
    public ActuateClaw(Claw subsystem, String direction) {
        this.claw = subsystem; 
        this.direction = direction;

        addRequirements(this.claw);
    }

    /**
     * Toggles the piston when the command is initialized. 
     */
    @Override
	public void initialize() {
        //claw.togglePiston();
    }

    /**
     * Runs whatever methods are currently prompted from the arm.  
     */
    @Override
    public void execute() {
        actuate(); 
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        claw.stopMotor();
    }

    /**
     * 
     */

    public void actuate() {
        switch (direction) {
            case "open":
                claw.actuateOpen();
                break;

            case "close":
                claw.actuateClosed();
                break;
            default:
                claw.stopMotor();
                break;
        }
    }

}

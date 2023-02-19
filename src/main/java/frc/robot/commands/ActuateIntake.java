package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

/**
 * Handler for methods relating to the actuation of the intake mechanism. 
 */
public class ActuateIntake extends CommandBase {
    // Initialize the variable for controlling the intake
    private Intake intake;

    // Initialize the variable to discern the direction of actuation. 
    private String direction;

    /**
     * Initialize our actuation commands through our Arm subsystem.
     *
     * @param subsystem - The Arm subsystem to be used. 
     * @param direction - The direction to actuate the intake mechanism. 
     */
    public ActuateIntake(Intake subsystem, String direction) {
        this.intake = subsystem;
        this.direction = direction;

        addRequirements(this.intake);
    }

    /**
     * Lifts the intake according to the direction variable. 
     */
    public void execute() {
        switch (direction) {
            case "lift":
                intake.liftIntake();
                break;

            case "lower":
                intake.lowerIntake();
                break;

            default:
                intake.stopActuationIntake();
                break;
        }
    }

    /**
     * Pauses the actuation of the intake. 
     */
    public void end(boolean interrupted) {
        intake.stopActuationIntake();
    }
}

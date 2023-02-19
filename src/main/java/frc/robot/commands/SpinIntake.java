package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

/**
 * Handler containing methods relating to the movement of the intake flywheels.
 */
public class SpinIntake extends CommandBase {
    // Initialize the Intake subsystem. 
    private Intake intake;

    // Initialize the direction to spin the intake. 
    private String direction;


    /**
     * Creates a method manager for rotating the intake flywheels. 
     *
     * @param subsystem - The Intake subsystem to be built upon. 
     * @param direction - The direction in which the intake flywheels will run. 
     */
    public SpinIntake(Intake subsystem, String direction) {
        this.intake = subsystem;
        this.direction = direction;
        
        addRequirements(this.intake);
    }

    /**
     * Runs whatever methods are currently prompted from the intake. 
     */
    public void execute() {
        // If a button for intake spinning is pressed, the flywheels will spin. 
        spin();

        // If a button for intake actuation is pressed, the intake will actuate.
        actuate();
    }

    public void end(boolean interrupted) {
        intake.stopIntakeSpin();
    }

    /**
     * Spins the intake according to the direction variable.
     */
    public void spin() {
        switch (direction) {
            case "forward":
                intake.spinForward();
                break;

            case "backward":
                intake.spinBackward();
                break;

            default:
                intake.stopIntakeSpin();
                break;
        }
    }


    /**
     * Actuates the arm according to the positions of the limit
     * switches if a button is pressed once.
     */
    public void actuate() {
        if (intake.getSwitchReading("lower")) {
            while (!intake.getSwitchReading("upper")) {
                intake.liftIntake();
            }
        } else if (intake.getSwitchReading("upper")) {
            while (!intake.getSwitchReading("lower")) {
                intake.lowerIntake();
            }
        }
        intake.stopActuationIntake();
    }

    /**
     * Method if we wanted an alternate control method,
     * where we hold down a button to move the intake instead of pressing one once.
     *
     * @param direction - The direction to move the intake.
     */
    public void actuateConstant(String direction) {
        if (intake.getSwitchReading("lower") && direction.equals("raise")) {
            intake.liftIntake();
        } else if (intake.getSwitchReading("upper") && direction.equals("lower")) {
            intake.lowerIntake();
        } else {
            intake.stopActuationIntake();
        }
    }
}

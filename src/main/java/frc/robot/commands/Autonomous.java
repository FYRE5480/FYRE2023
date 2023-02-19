package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;

/*
 * TODO: get PID working, instead of using encoder values, use acceleration and velocity.
 * TODO: overide and complete an isFinished() method for stopping auto.
 * 
 * NOTE: get and reset encoder methods have been added to drivetrain subsystem for ease of use
 * please use 2022's autonomous as an example of what can and should be done
 */  

/** Class tha runs in the autonomous portion of the game. */
public class Autonomous extends CommandBase {
    // Initialize our DriveTrain and Intake subsystems. 
    private final DriveTrain driveTrain; 
    private final Intake intake;

    // Initialize a PID controller for feedback loops. 
    private PIDController PIDAutoController;

    /**
     * Creates a new Autonomous command based on various subsystems.
     *
     * @param subsystemD - The DriveTrain subsystem for controlling robot movement. 
     * @param subsystemI - The Intake subsystem for obtaining new pieces.
     */
    public Autonomous(DriveTrain subsystemD, Intake subsystemI) {
        this.driveTrain = subsystemD;

        // Resets the encoders to their "zero" values. 
        this.driveTrain.resetAhrs();

        this.intake = subsystemI;
        addRequirements(subsystemD, subsystemI);
    }

    @Override
    public boolean isFinished() {
        return false; 
    }
}
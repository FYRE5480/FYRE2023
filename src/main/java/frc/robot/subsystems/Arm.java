package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Subsystem class that controls the arm's movement seperatley from the claw
 */
public class Arm extends SubsystemBase {
    /** Creates a new ExampleSubsystem. */
    public Arm () {}

    @Override
    public void periodic () {
    	// This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic () {
    	// This method will be called once per scheduler run during simulation
    }
}

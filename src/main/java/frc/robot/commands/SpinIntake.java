package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

public class SpinIntake extends CommandBase{
    // Initialize the variable for controlling the intake
    private Intake intake;


    public SpinIntake(Intake subsystem) {
        this.intake = subsystem;
        addRequirements(this.intake);
    }

    public void initialize () {}

    public void execute () {
        
    }

}

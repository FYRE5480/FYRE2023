package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

public class ActuateIntake extends CommandBase{
    // Initialize the variable for controlling the intake
    private Intake intake;
    private String direction;



    public ActuateIntake(Intake subsystem, String direction) {
        this.intake = subsystem;
        this.direction = direction;
        addRequirements(this.intake);
    }

    public void initialize () {}

    public void execute () {
        switch (direction) {
            case "lift" :
                intake.liftIntake();
                break;
            case "lower" :
                intake.lowerIntake();
                break;
            default :
                intake.stopActuationIntake();
                break;
        }
    }

    public void end (boolean interrupted) {
        intake.stopActuationIntake();
    }



}

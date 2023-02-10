package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

public class SpinIntake extends CommandBase{
    // Initialize the variable for controlling the intake
    private Intake intake;
    private String direction;



    public SpinIntake(Intake subsystem, String direction) {
        this.intake = subsystem;
        this.direction = direction;
        addRequirements(this.intake);
    }

    public void initialize () {}

    public void execute () {
        // Checks if the intake needs to spin, and does it
        spin();

        // Checks if the intake needs to actuate, and does it
        actuationOnce();

    }

    public void end (boolean interrupted) {
        intake.stopSpinIntake();
    }



    /**
     * Checks if the intake needs to spin, and does it
     */
    public void spin () {
        switch (direction) {
            case "forward" :
                intake.spinForward();
                break;
            case "backward" :
                intake.spinBackward();
                break;
            default :
                intake.stopSpinIntake();
                break;
        }
    }


    /**
     * Checks if the intake needs to be lifted or lowered, and does it
     */
    public void actuationOnce () {
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
     * @param direction - the direction the intake wants to be moved
     */
    public void actuationConstant (String direction) {
        if (intake.getSwitchReading("lower") && direction.compareTo("raise") == 0) {
            intake.liftIntake();
        } else if (intake.getSwitchReading("upper") && direction.compareTo("lower") == 0) {
            intake.lowerIntake();
        } else {
            intake.stopActuationIntake();
        }
    }
}

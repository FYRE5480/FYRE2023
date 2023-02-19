package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;

/*
 * TODO: get PID working, instead of using encoder values, use acceleration and velocity 
 * TODO: overide and complete an isFinished() method for stopping auto
 * NOTE: get and reset encoder methods have been added to drivetrain subsystem for ease of use
 * please use 2022's autonomous as an example of what can and should be done
 */  

/** Class tha runs in the autonomous portion of the game. */
public class Autonomous extends CommandBase {

    private final DriveTrain drive_train; 
    private final Intake intake;
    private PIDController PIDAutoController;


    /** Instantiates a new autonomous class with its variables. */
    public Autonomous(DriveTrain dt, Intake in) {
        // initializes the drivetrain class as a requirement
        this.drive_train = dt;
        addRequirements(dt);
        // initialize our encoders to their zero values
        this.drive_train.resetAhrs();

        // initializes the intake class as a requirement
        this.intake = in;
        addRequirements(in);
    }


}
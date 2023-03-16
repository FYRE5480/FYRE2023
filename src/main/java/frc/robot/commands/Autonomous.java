package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AutoSubsystem;
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

    private final AutoSubsystem auto = new AutoSubsystem(null);

    // Initialize a PID controller for feedback loops. 
    private PIDController PIDAutoController;

    /**
     * Creates a new Autonomous command based on various subsystems.
     *
     * @param subsystemD - The DriveTrain subsystem for controlling robot movement. 
     * @param subsystemI - The Intake subsystem for obtaining new pieces.
     */
    public Autonomous(DriveTrain subsystemD, Intake subsystemI) {
        // initializes drivetrain and intake as a required subsystem.
        this.driveTrain = subsystemD;
        this.intake = subsystemI;
        addRequirements(subsystemD, subsystemI);

        // resets the encoders to their "zero" values.
        this.driveTrain.resetAhrs();

        // initializes the values for our PID loop
        PIDAutoController = new PIDController(0.0035, 0.0005, 0.0001);
        PIDAutoController.setSetpoint(180);
        PIDAutoController.setTolerance(1);
    }


    /**
     * Runs every time the autonomous command is scheduled.
     */
    public void execute() {
        // driveTrain.tankDrive(
        //     MathUtil.clamp(PIDAutoController.calculate(driveTrain.getGyroscope()), -0.85, 0.85), 
        //     MathUtil.clamp(PIDAutoController.calculate(driveTrain.getGyroscope()), -0.85, 0.85)
        // );
        double time = Timer.getFPGATimestamp();
        autoBalance(time);
        
    }


    private void autoBalance(double time) {
        if (!auto.balance(driveTrain.getPitch())) {
            SmartDashboard.putBoolean("Is Balancing?", false);
            if (time < 1.5) {
                auto.shootCube(time);
            } else {
                auto.move(-0.25);
            }
        } else {
            SmartDashboard.putBoolean("Is Balancing?", true);
        }
    }

    private void autoNoBalance(double time) {
        if (time < 8) {
            if (time < 0.5) {
                if (auto.getDistance(driveTrain.getVelocity('Z'), time) < 1) {
                    auto.move(-0.25);
                } else if (auto.turn(90)) {
                    auto.move(0.25);
                    if (auto.getDistance(driveTrain.getVelocity('Z'), time) > 10) {
                        auto.move(0.25);
                    } else if (auto.turn(-90)) {
                        auto.move(0.25);
                    }
                }
            }
        }
    }



    @Override
    public boolean isFinished() {
        if (PIDAutoController.atSetpoint()) {
            System.out.println("REACHED SETPOINT!!!");
            return true;   
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {} 
}
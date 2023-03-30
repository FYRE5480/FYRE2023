   package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AutoSubsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeActuator;

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
    private final Intake intakeWheels;
    private final IntakeActuator intakeActuator;
    private final PIDController controller = null;
    private final AutoSubsystem auto;
    private int i = 0;

    private String balanceAuto;
    // Initialize a PID controller for feedback loops. 
    private PIDController PIDAutoController;

    /**
     * Creates a new Autonomous command based on various subsystems.
     *
     * @param subsystemD - The DriveTrain subsystem for controlling robot movement. 
     * @param subsystemI - The Intake subsystem for obtaining new pieces.
     */
    public Autonomous(DriveTrain driveTrain, Intake intakeWheels, IntakeActuator intakeActuator) {
        // initializes drivetrain and intake as a required subsystem.
        this.driveTrain = driveTrain;
        this.intakeWheels = intakeWheels;
        this.intakeActuator = intakeActuator;
        this.auto = new AutoSubsystem(controller, this.driveTrain, this.intakeWheels, this.intakeActuator);


        // resets the encoders to their "zero" values.
        this.driveTrain.resetAhrs();

        // initializes the values for our PID loop
        PIDAutoController = new PIDController(0.0035, 0.0005, 0.0001);
        PIDAutoController.setSetpoint(180);
        PIDAutoController.setTolerance(1);

        balanceAuto = SmartDashboard.getString("Balance Auto? ('yes' or 'no')", "no");
    }


    /**
     * Runs every time the autonomous command is scheduled.
     */
    public void execute() {
        // driveTrain.tankDrive(
        //     MathUtil.clamp(PIDAutoController.calculate(driveTrain.getGyroscope()), -0.85, 0.85), 
        //     MathUtil.clamp(PIDAutoController.calculate(driveTrain.getGyroscope()), -0.85, 0.85)
        // );
        //double time = Timer.getFPGATimestamp();
        // if (balanceAuto.equals("yes")) {
        //     autoBalance(time);
        // } else {
        //     autoNoBalance(time);
        // }
        //System.out.println("executing");
        //SmartDashboard.putNumber("Intake Encoder Value", intakeActuator.getEncoder());
        driveTrain.tankDrive(-0.7, 0.7);
    }

    public void test() {
        SmartDashboard.putString("HEY AUTO IS WORKING", "WOOOO");
        driveTrain.arcadeDrive(1, 0);
    }

    private void autoBalance(double time) {
    System.out.println("Hi reaching function");
        if (intakeActuator.setIntakePosition("shoot")) {
            if (auto.checkBalance(driveTrain.getPitch())) {
              SmartDashboard.putBoolean("Is Balancing?", false);
                if (time < 1.5) {
                    auto.shootCube(time);
                } else {
                    auto.move(-0.25);
                }
            } else {
                auto.balance(driveTrain.getPitch());
                SmartDashboard.putBoolean("Is Balancing?", true);
            }
        }
    } 


    private void stupid() {
        if (auto.balance(driveTrain.getPitch())) {
            auto.move(-0.75);
        }
    }



    private void autoNoBalance(double time) {
        if (intakeActuator.setIntakePosition("shoot")) {
            if (time < 8) {
                if (time < 0.5) {
                    auto.shootCube(time);
                } else {
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
    }

    private void autoBalanceNoShoot(double time) {
        System.out.println("calling function");
        if (!auto.checkBalance(driveTrain.getPitch())) {
            System.out.println("moving");
            SmartDashboard.putBoolean("Is Balancing?", false);
            auto.move(-1);
        } else {
            i++;
            System.out.println("balancing");
            auto.balance(driveTrain.getPitch());
            SmartDashboard.putBoolean("Is Balancing?", true);
            SmartDashboard.putNumber("Reached times", i);
        }
    }

    public void autoNoBalanceNoShoot(double time) {
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
        // if (PIDAutoController.atSetpoint()) {
        //     System.out.println("REACHED SETPOINT!!!");
        //     return true;   
        // }
        return false;
    }

    @Override
    public void end(boolean interrupted) {} 
}
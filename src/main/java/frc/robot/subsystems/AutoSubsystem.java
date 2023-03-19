package frc.robot.subsystems;



import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.commands.Autonomous;

import java.util.Hashtable;

/**
 * hi.
 */
public class AutoSubsystem extends PIDSubsystem {

    private final DriveTrain driveTrain;
    private final Intake intake;

    private Hashtable<String, Double> savedPoint = new Hashtable<String, Double>();

    private double distanceTraveled = 0;

    private double initialPitch;

    /**
     * Creates a new AutoSubsystem with PID.
     */
    public AutoSubsystem(PIDController controller, DriveTrain dt, Intake i) {
        super(new PIDController(0.0035, 0.0005, 0.0001));
        getController().setTolerance(1);
        setSetpoint(getSetpoint());
        this.driveTrain = dt;
        this.intake = i;
        driveTrain.resetAhrs();
        savedPoint.put("X", 0.0);
        savedPoint.put("Z", 0.0);
        savedPoint.put("turn", driveTrain.getGyroscope());
        initialPitch = driveTrain.getPitch();
    }


   
    @Override
    public void useOutput(double output, double setpoint) {
        driveTrain.tankDrive(output, output);
    }


    @Override
    public double getMeasurement() {
        return (
            Math.sqrt(
                Math.pow(driveTrain.getVelocity('Z'), 2) 
                + Math.pow(driveTrain.getVelocity('X'), 2)
            ));
    }

    public void move(double speed) {
        driveTrain.tankDrive(speed, -speed);
    }

    /**
     * Turns the robot a set ammount of degrees.

     * @param degrees - the ammount to spin
     */
    public boolean turn(int degrees) {
        if (savedPoint.get("turn") != degrees) {
            driveTrain.arcadeDrive(0, 0.25);
        } else {
            return false;
        }
        return true;
    }

    /**
     * Spins the intake for a specific ammount of time.

     * @param time - the ammount of time to spin the intake
     */
    public void shootCube(double time) {
        if (time < 1) {
            intake.spinForward();
        }

    }

    public double getDistance(float velocity, double time) {
        distanceTraveled += (velocity / time);
        return velocity / time;
    }

    public boolean balance(double pitch) {
        if (pitch > initialPitch + 5) {
            driveTrain.tankDrive(pitch / 100, -pitch / 100);
            return true;
        } else if (pitch < initialPitch - 5) {
            driveTrain.tankDrive(-pitch / 100, pitch / 100);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkBalance(double pitch) {
        if (pitch  > initialPitch + 5) {
            return true;
        } else if (pitch < initialPitch - 5) {
            return true;
        }
        return false;
    }

    public void savePoint() {}
}

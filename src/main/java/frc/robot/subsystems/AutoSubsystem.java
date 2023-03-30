package frc.robot.subsystems;



import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;
import frc.robot.commands.Autonomous;

import java.util.Hashtable;

/**
 * hi.
 */
public class AutoSubsystem extends PIDSubsystem {

    private DriveTrain driveTrain;
    private Intake intakeWheels;
    private IntakeActuator intakeActuator;


    private Hashtable<String, Double> savedPoint = new Hashtable<String, Double>();

    private double distanceTraveled = 0;

    private double initialPitch;

    /**
     * Creates a new AutoSubsystem with PID.
     */
    public AutoSubsystem(PIDController controller, DriveTrain dt, Intake iwheels, IntakeActuator iActuator) {
        super(new PIDController(0.0035, 0.0005, 0.0001));
        getController().setTolerance(1);
        setSetpoint(getSetpoint());
        this.driveTrain = dt;
        this.intakeWheels = iwheels;
        this.intakeActuator = iActuator;
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
        System.out.println("Reaching move command with speed " + speed);
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
            intakeWheels.spinForwardFast();
        }

    }

    public double getDistance(float velocity, double time) {
        distanceTraveled += (velocity / time);
        return velocity / time;
    }

    public boolean balance(double pitch) {
        // the plus and minus 5 are to add a deadband to the balancing to act as a deadband for noise in the gyro
        if (pitch > initialPitch + 7) {
            driveTrain.tankDrive(pitch / 100, -pitch / 100);
            return true;
        } else if (pitch < initialPitch - 7) {
            driveTrain.tankDrive(-pitch / 100, pitch / 100);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkBalance(double pitch) {
        if (pitch  > initialPitch + 7 || pitch < initialPitch - 7   ) {
            return true;
        }
        return false;
    }

    public void savePoint() {}
}

package frc.robot.subsystems;



import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import java.util.Hashtable;

/**
 * hi.
 */
public class AutoSubsystem extends PIDSubsystem {

    private final DriveTrain driveTrain = new DriveTrain();
    private final Intake intake = new Intake();

    private Hashtable<String, Double> savedPoint = new Hashtable<String, Double>();

    private double distanceTraveled = 0;

    /**
     * Creates a new AutoSubsystem with PID.
     */
    public AutoSubsystem(PIDController controller) {
        super(new PIDController(0.0035, 0.0005, 0.0001));
        getController().setTolerance(1);
        setSetpoint(getSetpoint());
        savedPoint.put("X", 0.0);
        savedPoint.put("Z", 0.0);
        savedPoint.put("turn", driveTrain.getGyroscope());
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
        driveTrain.tankDrive(speed, speed);
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
    
    public void savePoint() {}
}

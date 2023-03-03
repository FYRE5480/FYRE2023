package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class AutoSubsystem extends PIDSubsystem{

    public AutoSubsystem(PIDController controller) {
        super(controller);
        //TODO Auto-generated constructor stub
    }


    private final DriveTrain driveTrain; 
    private final Intake intake;

    // Initialize a PID controller for feedback loops. 
    private PIDController PIDAutoController;

   
    @Override
    public void useOutput(double output, double setpoint) {
        driveTrain.getVelocity('X');
    }


    @Override

    public double getMeasurement() {

        return m_shooterEncoder.getRate();

    }
}

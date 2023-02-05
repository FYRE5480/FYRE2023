package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Subsystem class that controls the arm's movement seperatley from the claw
 */
public class Arm extends SubsystemBase {
    // Initialize our motor for our arm. 
    private final WPI_VictorSPX armMotor = new WPI_VictorSPX(Constants.ARM_MOTOR_PORT);

    /**
     * I am unsure if we are using an Encoder or a limit switch
     * for the arm. I decided to add both to the file. Take
     * them out as you wish!
     */
    private final Encoder armEncoder = new Encoder(
        Constants.ARM_ENCODER_PORT_A, 
        Constants.ARM_ENCODER_PORT_B, 
        false, 
        Encoder.EncodingType.k4X
    );
    private final DigitalInput armSwitch = new DigitalInput(Constants.ARM_SWITCH_PORT);

    /** Creates a new ExampleSubsystem. */
    public Arm () {}

    @Override
    public void periodic () {
    	// This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic () {
    	// This method will be called once per scheduler run during simulation
    }
}

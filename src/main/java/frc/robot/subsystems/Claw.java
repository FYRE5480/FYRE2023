package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** 
 * A subsystem to control the claw actuation.
 */
public class Claw extends SubsystemBase {
    // Example for initializing a DoubleSolenoid. Change the model once we know it!
    private DoubleSolenoid clawPiston = new DoubleSolenoid(
        PneumaticsModuleType.CTREPCM, 
        Constants.CLAW_SOLENOID_FORWARD_CHANNEL, 
        Constants.CLAW_SOLENOID_REVERSE_CHANNEL
    );

    /** Creates a new ExampleSubsystem. */
    public Claw () {
        /**
         * Double solenoids must be "set" before they can be used.
         * The options include kOff, kForward, and kReverse. kForward
         * will take the output from the FORWARD channel, while kReverse
         * will take the output from the REVERSE channel. Those ports
         * have been defined as a part of the Constants class. 
         */
        clawPiston.set(Value.kForward);
    }

    /**
     * Dummy function for toggling the clawPiston. To switch between
     * kForward and kReverse, .toggle() must be called on the piston.
     * You do not necessarily have to use this method, but it'd
     * probably be easier to hook it up to the Joystick this way.
     */
    public void togglePiston() {
        clawPiston.toggle();
    }

    @Override
    public void periodic () {
    	// This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic () {
    	// This method will be called once per scheduler run during simulation
    }
}
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** 
 * A subsystem to control the claw actuation.
 */
public class Claw extends SubsystemBase {

    // Initialize the double solenoid to be used for claw movement. 
    private DoubleSolenoid clawPiston = new DoubleSolenoid(
        PneumaticsModuleType.CTREPCM, 
        Constants.CLAW_SOLENOID_FORWARD_CHANNEL, 
        Constants.CLAW_SOLENOID_REVERSE_CHANNEL
    );

    /** Creates a new Claw. */
    public Claw() {
        clawPiston.set(Value.kForward);
    }

    /**
     * Toggles the claw's gripping piston. 
     */
    public void togglePiston() {
        clawPiston.toggle();
    }
}
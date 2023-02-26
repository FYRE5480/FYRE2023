package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
//import edu.wpi.first.wpilibj.PneumaticsModuleType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


/** 
 * A subsystem to control the claw actuation.
 */
public class Claw extends SubsystemBase {

    // Initialize the double solenoid to be used for claw movement. 
    //private DoubleSolenoid clawPiston = new DoubleSolenoid(
    //    PneumaticsModuleType.CTREPCM, 
    //    Constants.CLAW_SOLENOID_FORWARD_CHANNEL, 
    //    Constants.CLAW_SOLENOID_REVERSE_CHANNEL
    //);

    private CANSparkMax clawMotor = new CANSparkMax(Constants.CLAW_MOTOR_PORT, MotorType.kBrushed);
    private final DigitalInput clawSwitch = new DigitalInput(Constants.CLAW_SWITCH_PORT);

    /** Creates a new Claw. */
    public Claw() {
    //    clawPiston.set(Value.kForward);
    }

    /**
     * Toggles the claw's gripping piston. 
     */
    //public void togglePiston() {
    //    clawPiston.toggle();
    //}

    /**
     * Stops the claw actuation motor.
     */
    public void stopMotor() {
        clawMotor.set(0);
    }

    /**
     * 
     */

    public void actuateOpen() {
        clawMotor.set(Constants.CLAW_ACTUATION_SPEED);
    }

     /**
     * 
     */

    /**public void actuateClosed() {
        while (!clawSwitch.get()) {
            clawMotor.set(-Constants.CLAW_ACTUATION_SPEED);
        }
        clawMotor.set(0);
    }*/

    public void actuateClosed() {
        clawMotor.set(-Constants.CLAW_ACTUATION_SPEED);
    }

}
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Subsystem that controls the arm's movement seperately from the claw.
 */
public class Arm extends SubsystemBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    // Initialize our motor for our arm. 
    private final CANSparkMax armMotor = new CANSparkMax(
        Constants.ARM_MOTOR_PORT, 
        MotorType.kBrushless
    );

    // Initialize our encoder for calculating arm movement. 
    private final Encoder armEncoder = new Encoder(
        Constants.ARM_ENCODER_PORT_A, 
        Constants.ARM_ENCODER_PORT_B, 
        false, 
        Encoder.EncodingType.k4X
    );

    // Initialize our limit switches for preventing obscene arm movement. 
    private final DigitalInput armSwitchUpper = new DigitalInput(Constants.ARM_SWITCH_PORT_A);
    private final DigitalInput armSwitchLower = new DigitalInput(Constants.ARM_SWITCH_PORT_B);

    /** Creates a new ExampleSubsystem. */
    public Arm() {
        // Set the safety toggle and expiration on the motors + drivetrain.
        // setupMotors(new CANSparkMax[]{armMotor});

        // Reset and prepare our encoders for calculation.
        setupEncoders(new Encoder[]{armEncoder});
    }
        
    /**
     * Gets the reading of the encoder on the arm motor. 
     *
     * @return - The current position of the arm encoder. 
     */
    public double getEncoder() {
        return armEncoder.getDistance();
    }

    /**
     * Reset the value of the arm encoder. 
     */
    public void resetEncoder() {
        armEncoder.reset();
    }

    /* 
    /**
     * Toggles the safety and sets the expiration for all of the motor objects.

     * @param motors - An array of the motors to edit the properties of. 
     
    public void setupMotors(CANSparkMax[] motors) {
        for (CANSparkMax motor : motors) {
            //motor.setSafetyEnabled(Constants.SAFETY_TOGGLE);
            //motor.setExpiration(Constants.EXPIRATION_TIME);
        }
    }
    */

    /**
     * Reset the encoders and prepare to calculate rotation.
     *
     * @param encoders - An array of the encoders to edit the properties of.
     */
    public void setupEncoders(Encoder[] encoders) {
        for (Encoder encoder : encoders) {
            encoder.reset();
            encoder.setDistancePerPulse(Constants.ENCODER_DISTANCE_CONSTANT);
        }
    }

    /**
     * Gets the reading of the appropriate limit switch. 
     *
     * @param side - The limit switch to be referenced ("upper" or "lower")
     * @return - The current poisition of the prompted limit switch. 
     */
    public boolean getSwitchReading(String side) {
        switch (side) {
            case "upper":
                return armSwitchUpper.get();
                
            case "lower":
                return armSwitchLower.get();

            default: 
                return true;
        }
    }

    /**
     * Actuates the arm up at the ARM_ACTUATION_SPEED. 
     */
    public void actuateUp() {
        while (!getSwitchReading("lower")) {
            armMotor.set(Constants.ARM_ACTUATION_SPEED);
        }
    }

    /**
     * Actuates the arm down at the ARM_ACTUATION_SPEED. 
     */
    public void actuateDown() {
        while (!getSwitchReading("upper")) {
            armMotor.set(-Constants.ARM_ACTUATION_SPEED);
        }
    }

    /**
     * Stops the arm actuation motor.
     */
    public void stopMotor() {
        armMotor.set(0);
    }
}

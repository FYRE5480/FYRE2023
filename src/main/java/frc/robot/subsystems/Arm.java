package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


//import edu.wpi.first.wpilibj.;

/**
 * Subsystem class that controls the arm's movement seperatley from the claw
 */
public class Arm extends SubsystemBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    // Initialize our motor for our arm. 
    private final CANSparkMax armMotor = new CANSparkMax(Constants.ARM_MOTOR_PORT, MotorType.kBrushless);


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
    private final DigitalInput armSwitchUpper = new DigitalInput(Constants.ARM_SWITCH_PORT_1);
    private final DigitalInput armSwitchLower = new DigitalInput(Constants.ARM_SWITCH_PORT_2);

    /** Creates a new ExampleSubsystem. */
    public Arm () {
        // Set the safety toggle and expiration on the motors + drivetrain.
        //setupMotors(new CANSparkMax[]{armMotor});

        // Reset and prepare our encoders for calculation.
        setupEncoders(new Encoder[]{armEncoder});
    }
        
    /**
     * Grab the values of a particular encoder. 

     * @param side - The distance traveled by a particular side of a robot.
     * @return - The current distance traveled of a particular encoder. 
     */
    public double getEncoder() {
        
        return armEncoder.getDistance();

    }

    /**
     * Reset the values of an encoder or encoders.

     * @param side - The side of the robot to reset. 
     */
    public void resetEncoder(String side) {
        
        armEncoder.reset();
        
    }

    /**
     * Toggles the safety and sets the expiration for all of the motor objects.

     * @param motors - An array of the motors to edit the properties of. 
     */

    /**public void setupMotors(CANSparkMax[] motors) {
        for (CANSparkMax motor : motors) {
            //motor.setSafetyEnabled(Constants.SAFETY_TOGGLE);
            //motor.setExpiration(Constants.EXPIRATION_TIME);
        }
    }*/

    /**
     * Reset the encoders and prepare to calculate rotation.

     * @param encoders - An array of the encoders to edit the properties of.
     */
    public void setupEncoders(Encoder[] encoders) {
        for (Encoder encoder : encoders) {
            encoder.reset();
            encoder.setDistancePerPulse(Constants.ENCODER_DISTANCE_CONSTANT);
        }
    }

    public boolean getSwitchReading(String side){
        switch (side) {
            case "upper":
                return armSwitchUpper.get();
                
            case "lower":
                return armSwitchLower.get();

            default: 
                return true;
        }
    }

    public void moveUp(){
        armMotor.set(0.5);
    }

    public void moveDown(){
        armMotor.set(0.5);
    }

    public void stopMotor(){
        armMotor.set(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Encoder Distance (revolutions)", getEncoder());
 
    }

    @Override
    public void simulationPeriodic () {
    	// This method will be called once per scheduler run during simulation
    }
}

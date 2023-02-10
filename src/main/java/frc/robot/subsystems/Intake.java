/** 
 * A subsystem class to control the intake spinning and actuating
 */
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    // There are two motor controllers that exist here, but they are wired together on one PWM port. 
    private final Spark intakeMotor = new Spark(Constants.INTAKE_SPINNER_MOTOR_PORT);
    private final VictorSPX actuationMotor = new VictorSPX(Constants.INTAKE_ACTUATOR_MOTOR_PORT);

    // Sets up the encoders for the intake
    private final DigitalInput intakeSwitchUpper = new DigitalInput(Constants.INTAKE_SWITCH_PORT_1);
    private final DigitalInput intakeSwitchLower = new DigitalInput(Constants.INTAKE_SWITCH_PORT_2);


    /** Creates a new ExampleSubsystem. */
    public Intake () {}

    @Override
    public void periodic () {
    	// This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic () {
    	// This method will be called once per scheduler run during simulation
    }

    /**
     * 
     * @param side - which limit switch's output should read
     * @return boolean - whether the specified limit switch is being engaged.
     * Defaults to true to prevent the motor moving unintentionally
     */
    public boolean getSwitchReading(String side){
        switch (side) {
            case "upper":
                return intakeSwitchUpper.get();
                
            case "lower":
                return intakeSwitchLower.get();

            default: 
                return true;
        }
    }

////////////////////////////////////////////////////
///// This code deals with spinning the intake /////
////////////////////////////////////////////////////
    public void spinForward () {
        intakeMotor.set(0.6);
    }

    public void spinBackward () {
        intakeMotor.set(-0.6);
    }

    public void stopSpinIntake () {
        intakeMotor.set(0.0);
    }
////////////////////////////////////////////////////


///////////////////////////////////////////////////
//// This code deals with actuating the intake ////
///////////////////////////////////////////////////
    public void liftIntake () {
        actuationMotor.set(ControlMode.PercentOutput, 0.3);
    }

    public void lowerIntake () {
        actuationMotor.set(ControlMode.PercentOutput, -0.3);
    }

    public void stopActuationIntake() {
        actuationMotor.set(ControlMode.PercentOutput, 0.0);
    }
///////////////////////////////////////////////////






}

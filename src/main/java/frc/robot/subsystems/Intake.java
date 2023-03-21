// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
import frc.robot.RobotContainer;

/**
 * Subsystem for controlling speed and position of the intake mechanism. 
 */
public class Intake extends SubsystemBase {
    // There are two motor controllers that exist here, but they are wired together to PWM port. 
    //private final Spark intakeMotor = new Spark(Constants.INTAKE_SPINNER_MOTOR_PORT);
    private final CANSparkMax intakeMotor1 = new CANSparkMax(
        Constants.INTAKE_SPINNER1_MOTOR_PORT, 
        MotorType.kBrushed
        );

    private final CANSparkMax intakeMotor2 = new CANSparkMax(
        Constants.INTAKE_SPINNER2_MOTOR_PORT, 
        MotorType.kBrushed
        );
    
    private final CANSparkMax actuationMotor = new CANSparkMax(
        Constants.INTAKE_ACTUATOR_MOTOR_PORT,
        MotorType.kBrushed
        );
    

    // Sets up the encoders for the intake
    private final DigitalInput intakeSwitchUpper = new DigitalInput(Constants.INTAKE_SWITCH_PORT_A);
    private final DigitalInput intakeSwitchLower = new DigitalInput(Constants.INTAKE_SWITCH_PORT_B);

    /** Creates a new Intake subsystem. */
    public Intake() {}

    /**
     * Gets the reading of the appropriate limit switch. 
     *
     * @param side - The limit switch to be referenced ("upper" or "lower")
     * @return - The current poisition of the prompted limit switch. 
     */
    public boolean getSwitchReading(String side) {
        switch (side) {
            case "upper":
                return intakeSwitchUpper.get();
                
            case "lower":
                return intakeSwitchLower.get();

            default: 
                return true;
        }
    }

    // Methods for controlling the speed of the intake. 

    /**
     * Runs the intake forward at the INTAKE_SPEED level.
     */
    public void spinForward() { 
        intakeMotor1.set(Constants.INTAKE_SPEED);
        intakeMotor2.set(-Constants.INTAKE_SPEED);
    }

    public void spinForwadFast() {
        intakeMotor1.set(100);
        intakeMotor2.set(-100);
    }

    /**
     * Runs the intake backward at the INTAKE_SPEED level.
     */
    public void spinBackward() {
        intakeMotor1.set(-Constants.INTAKE_SPEED);
        intakeMotor2.set(Constants.INTAKE_SPEED);
    }

    /**
     * Stops the flywheels on the intake system. 
     */
    public void stopIntakeSpin() {
        intakeMotor1.set(0.0);
        intakeMotor2.set(0.0);
    }
    

    // Methods for controlling the actuation of the intake. 

    /**
     * Runs the actuation motor upwards at the INTAKE_ACTUATION_SPEED level.
     */
    public void liftIntake() {
        if (getSwitchReading("upper")) {
            //actuationMotor.set(ControlMode.PercentOutput, Constants.INTAKE_ACTUATION_SPEED);
            actuationMotor.set(-Constants.INTAKE_ACTUATION_SPEED);
        }

    }

    /**
     * Runs the actuation motor downwards at the INTAKE_ACTUATION_SPEED level. 
     */
    public void lowerIntake() {
        //actuationMotor.set(ControlMode.PercentOutput, -Constants.INTAKE_ACTUATION_SPEED);
        actuationMotor.set(Constants.INTAKE_ACTUATION_SPEED);
    }

    /**
     * Stops the actuation motor on the intake system. 
     */
    public void stopActuationIntake() {
        //actuationMotor.set(ControlMode.PercentOutput, 0.0);
        actuationMotor.set(0.0);
    }

    public void intakeAndActuate(String actuateDirection, String flywheelDirection) {
        switch (actuateDirection) {
            case "lower":
                liftIntake();
                break;
            case "lift":
                lowerIntake();
                break;
            default:
                stopActuationIntake();
                break;
        }

        switch (actuateDirection) {
            case "forwardFast":
                spinForwadFast();
                break;
            case "forward":
                spinForward();
                break;
            case "backward":
                spinBackward();
                break;
            default:
                stopActuationIntake();
                break;
        }
    }

}

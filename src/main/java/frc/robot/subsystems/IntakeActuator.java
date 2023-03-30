// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
import frc.robot.RobotContainer;

/**
 * Subsystem for controlling speed and position of the intake mechanism. 
 */
public class IntakeActuator extends SubsystemBase {
    
    private final CANSparkMax actuationMotor = new CANSparkMax(
        Constants.INTAKE_ACTUATOR_MOTOR_PORT,
        MotorType.kBrushed
        );

    private final Encoder actuationEncoder = new Encoder(
        Constants.INTAKE_ACTUATION_ENCODER_A, 
        Constants.INTAKE_ACTUATION_ENCODER_B, 
        false,
        Encoder.EncodingType.k1X
        );
    

    // Sets up the encoders for the intake
    private final DigitalInput intakeSwitchUpper = new DigitalInput(Constants.INTAKE_SWITCH_PORT_A);
    private final DigitalInput intakeSwitchLower = new DigitalInput(Constants.INTAKE_SWITCH_PORT_B);

    /** Creates a new Intake subsystem. */
    public IntakeActuator() {
        actuationEncoder.reset();
    }

    public double getEncoder() {
        return actuationEncoder.getDistance() * Constants.ARM_ENCODER_DISTANCE_CONSTANT;
    }

// 22 over 48
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
    
    public boolean setIntakePosition(String position) {
        switch (position) {
            case "in":
                if (getEncoder() < Constants.INTAKE_RESTING_ANGLE) {
                    liftIntake();
                } else {
                    return true;
                }
                break;
            case "shoot":
                if (getEncoder() > Constants.INTAKE_SHOOTER_ANGLE) {
                    lowerIntake();
                } else if (getEncoder() < Constants.INTAKE_SHOOTER_ANGLE) {
                    liftIntake();
                } else {
                    return true;
                }
                break;
            case "out":
                if (getEncoder() > 0) {
                    lowerIntake();
                } else if (getEncoder() < 0) {
                    liftIntake();
                } else {
                    return true;
                }
                break;
            default:
                stopActuationIntake();
                break;
        }
        return false;
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
}

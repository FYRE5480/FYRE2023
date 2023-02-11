// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Subsystem for controlling speed and position of the intake mechanism. 
 */
public class Intake extends SubsystemBase {
    // There are two motor controllers that exist here, but they are wired together to PWM port. 
    private final Spark intakeMotor = new Spark(Constants.INTAKE_SPINNER_MOTOR_PORT);
    private final VictorSPX actuationMotor = new VictorSPX(Constants.INTAKE_ACTUATOR_MOTOR_PORT);

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
        intakeMotor.set(Constants.INTAKE_SPEED);
    }

    /**
     * Runs the intake backward at the INTAKE_SPEED level.
     */
    public void spinBackward() {
        intakeMotor.set(-Constants.INTAKE_SPEED);
    }

    /**
     * Stops the flywheels on the intake system. 
     */
    public void stopIntakeSpin() {
        intakeMotor.set(0.0);
    }

    // Methods for controlling the actuation of the intake. 

    /**
     * Runs the actuation motor upwards at the INTAKE_ACTUATION_SPEED level.
     */
    public void liftIntake() {
        actuationMotor.set(ControlMode.PercentOutput, Constants.INTAKE_ACTUATION_SPEED);
    }

    /**
     * Runs the actuation motor downwards at the INTAKE_ACTUATION_SPEED level. 
     */
    public void lowerIntake() {
        actuationMotor.set(ControlMode.PercentOutput, -Constants.INTAKE_ACTUATION_SPEED);
    }

    /**
     * Stops the actuation motor on the intake system. 
     */
    public void stopActuationIntake() {
        actuationMotor.set(ControlMode.PercentOutput, 0.0);
    }
}

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

    /** Creates a new Intake subsystem. */
    public Intake() {}


    // Methods for controlling the speed of the intake. 

    /**
     * Runs the intake forward at the INTAKE_SPEED level.
     */
    public void spinForward() { 
        intakeMotor1.set(Constants.INTAKE_SPEED);
        intakeMotor2.set(-Constants.INTAKE_SPEED);
    }

    public void spinForwadFast() {
        intakeMotor1.set(1.0);
        intakeMotor2.set(-1.0);
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
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.SparkMaxAlternateEncoder;

/**
* The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
* constants. This class should not be used for any other purpose. All constants should be declared
* globally (i.e. public static). Do not put anything functional in this class.
*
* <p>It is advised to statically import this class (or one of its inner classes) wherever the
* constants are needed, to reduce verbosity.
*/
public final class Constants {
    /** The speed that the arm actuation motors should be running at. */
    public static final double ARM_ACTUATION_SPEED = 0.5; 
    
    /** The port of the arm motor. */
    public static final int ARM_MOTOR_PORT = 7;  
    
    /** Encoder type for the built in encoder in the NEO motor */
    public static final SparkMaxAlternateEncoder.Type ARM_ENCODER_TYPE = SparkMaxAlternateEncoder.Type.kQuadrature;

    // the encoder constant has been changed because it only needs to be used for the arm, not the drivetrain
    /** 
    * The constant of the arm encoder's distance per pulse.
    * Set to 189, because the arm currently has a 1 : 189 gear ratio from the neo
    * (1:100 gear box, first chain is 16:22, second chain is 16:22 as well).
    * This can be changed.
    */
    public static final int ARM_ENCODER_DISTANCE_CONSTANT = 189;

    /** The port for the upper arm limit switch. */
    public static final int ARM_SWITCH_PORT_A = 6; 
    
    /** The port for the lower arm limit switch. */
    public static final int ARM_SWITCH_PORT_B = 7; 

    /** The port for the claw limit switch. */
    public static final int CLAW_SWITCH_PORT = 5;
    
    /** The port of the claw solenoid's forward channel. */
    //public static final int CLAW_SOLENOID_FORWARD_CHANNEL = 6;  
    
    /** The port of the claw solenoid's reverse channel. */
    //public static final int CLAW_SOLENOID_REVERSE_CHANNEL = 7;  
    
    /** The deadband for the joysticks to prevent small, irregular movements. */
    public static final double CONTROLLER_DEADBAND = 0.25; 
    
    /** The expiration time for the motor's safety. */
    public static final int EXPIRATION_TIME = Integer.MAX_VALUE;
    
    /** The port of the intake motor that lifts it in and out of the robot. */
    public static final int INTAKE_ACTUATOR_MOTOR_PORT = 8; 
    
    /** The speed that the intake lifting motors should be running at. */
    public static final double INTAKE_ACTUATION_SPEED = 0.6; 
    
    /** The speed that the intake flywheels should be running at. */
    public static final double INTAKE_SPEED = 0.7; 
    
    /** Port for the claw's new motor. */
    public static final int CLAW_MOTOR_PORT = 9;

    /** Actuation speed for claw. */
    public static final double CLAW_ACTUATION_SPEED = 0.3;
    
    /** 
    * The two motor controllers that spin the intake motors exist here,
    * but they are both wired together on one PWM port.
    */
    public static final int INTAKE_SPINNER_MOTOR_PORT = 6;
    
    /** The port for the upper intake limit switch. */
    public static final int INTAKE_SWITCH_PORT_A = 8; 
    
    /** The port for the lower intake limit switch. */
    public static final int INTAKE_SWITCH_PORT_B = 9; 
    
    /** The toggle for reversed movement controls. */
    public static final boolean INVERTED_DRIVE = false; 
    
    /** Toggles the tank drive on the robot. */
    public static final boolean IS_TANK = true;
    
    /** The first port for the left encoder. */
    public static final int LEFT_ENCODER_PORT_A = 3;
    
    /** The second port for the left encoder. */
    public static final int LEFT_ENCODER_PORT_B = 2;
    
    /** The port of the first left drivetrain motor. */
    public static final int LEFT_MOTOR_PORT_A = 0;  
    
    /** The port of the second left drivetrain motor. */
    public static final int LEFT_MOTOR_PORT_B = 1;
    
    /** The port of the third left drivetrain motor. */
    public static final int LEFT_MOTOR_PORT_C = 2;
    
    /** The first port for the right encoder. */
    public static final int RIGHT_ENCODER_PORT_A = 1;
    
    /** The second port for the right encoder. */
    public static final int RIGHT_ENCODER_PORT_B = 0;
    
    /** The port of the first right drivetrain motor. */
    public static final int RIGHT_MOTOR_PORT_A = 3; 
    
    /** The port of the second right drivetrain motor. */
    public static final int RIGHT_MOTOR_PORT_B = 4; 

    /** The port of the second right drivetrain motor. */
    public static final int RIGHT_MOTOR_PORT_C = 5; 

    /**
     * The value that will limit the ammount of acceleration on the robot.
     * It will do this by limiting the ammount of change the joystick will register
     */
    public static final double DRIVETRAIN_ACCELERATION_DAMPENER_VALUE = 0.125;
    
    /** The port of the compressor. */
    //public static final int COMPRESSOR_PCM_PORT = 10;
    
    /** 
     * The default minimum psi the compressor should run at.
     * Value is set very low so that the compressor will always run when the button is held.
     */
    //public static final int COMPRESSOR_MIN_PSI = 0;
    
    /** 
     * The default maximum psi the compressor should run at.
     * Value is set very high so that the compressor will never turn off when the button is held.
    */
    //public static final int COMPRESSOR_MAX_PSI = 60;
    
    /** The toggle for motor safety. */
    public static final boolean SAFETY_TOGGLE = false; 
    
    /** The multipler of the speed of the drivetrain. */
    public static final double THROTTLE = 1.0;
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
* The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
* constants. This class should not be used for any other purpose. All constants should be declared
* globally (i.e. public static). Do not put anything functional in this class.
*
* <p>It is advised to statically import this class (or one of its inner classes) wherever the
* constants are needed, to reduce verbosity.
*/
public final class Constants {
    /** The multipler of the speed of the drivetrain. */
    public static final double THROTTLE = 0.7;
    
    /** The port of the right drivetrain motor. */
    public static final int RIGHT_MOTOR_PORT_1 = 0; 
    
    /** The port of the right drivetrain motor. */
    public static final int RIGHT_MOTOR_PORT_2 = 1; 
    
    /** The port of the left drivetrain motor. */
    public static final int LEFT_MOTOR_PORT_2 = 2;
    
    /** The port of the left drivetrain motor. */
    public static final int LEFT_MOTOR_PORT_1 = 3;  
    
    /** The port of the intake motor that lifts it in and out of the robot*/
    public static final int INTAKE_ACTUATOR_MOTOR_PORT = 4;  
    
    /** One of the ports for the intake motors that spin the intake */
    public static final int INTAKE_SPINNER1_MOTOR_PORT = 8;
    
    /** One of the ports for the intake motors that spin the intake */
    public static final int INTAKE_SPINNER2_MOTOR_PORT = 9;
    
    /** The port of the arm motor */
    public static final int ARM_MOTOR_PORT = 5;  
    
    /**
    * Solenoids utilize two different ports to control
    * when they are to be extended or not. I used
    * 6 and 7 as dummy numbers for the sole purpose of 
    * needed variables.
    */
    
    /** The port of the claw solenoid's forward channel. */
    public static final int CLAW_SOLENOID_FORWARD_CHANNEL = 6;  
    
    /** The port of the claw solenoid's reverse channel. */
    public static final int CLAW_SOLENOID_REVERSE_CHANNEL = 7;  
    
    /** The expiration time for the motor's safety. */
    public static final int EXPIRATION_TIME = Integer.MAX_VALUE;
    
    /** The toggle for motor safety. */
    public static final boolean SAFETY_TOGGLE = false; 
    
    /** The toggle for reversed movement controls. */
    public static final boolean INVERTED_DRIVE = false; 
    
    /** Toggles the tank drive on the robot. */
    public static final boolean IS_TANK = false;
    
    /** The constant of the encoder's distance per pulse. */
    public static final double ENCODER_DISTANCE_CONSTANT = 1.0 / 2048 * 6 * Math.PI;
    
    /** The first port for the left encoder. */
    public static final int LEFT_ENCODER_PORT_A = 3;
    
    /** The second port for the left encoder. */
    public static final int LEFT_ENCODER_PORT_B = 2;
    
    /** The first port for the right encoder. */
    public static final int RIGHT_ENCODER_PORT_A = 1;
    
    /** The second port for the right encoder. */
    public static final int RIGHT_ENCODER_PORT_B = 0;
    
    /** The first port for the arm encoder. */
    public static final int ARM_ENCODER_PORT_A = 5;
    
    /** The second port for the arm encoder. */
    public static final int ARM_ENCODER_PORT_B = 4;
    
    /** The port for the arm limit switch. */
    public static final int ARM_SWITCH_PORT = 6; 
    
    /** The deadband for the joysticks to prevent small, irregular movements. */
    public static final double DEADBAND = 0.25; 
}
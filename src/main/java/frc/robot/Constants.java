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
    public static double throttle = 1.0;
    
    /** The port of the left drivetrain motor. */
    public static final int leftMotorPort = 0;

    /** The port of the right drivetrain motor. */
    public static final int rightMotorPort = 1; 

    /** The expiration time for the motor's safety. */
    public static final int expirationTime = Integer.MAX_VALUE;

    /** The toggle for motor safety. */
    public static final boolean safetyToggle = false; 

    /** The toggle for reversed movement controls. */
    public static final boolean invertedDrive = false; 

    /** Toggles the tank drive on the robot. */
    public static final boolean isTank = false;
    
    /** The constant of the encoder's distance per pulse. */
    public static final double encoderDistanceConstant = 1.0 / 2048 * 6 * Math.PI;

    /** The first port for the left encoder. */
    public static final int leftEncoderPortA = 3;

    /** The second port for the left encoder. */
    public static final int leftEncoderPortB = 2;

    /** The first port for the right encoder. */
    public static final int rightEncoderPortA = 1;

    /** The second port for the right encoder. */
    public static final int rightEncoderPortB = 0;
}
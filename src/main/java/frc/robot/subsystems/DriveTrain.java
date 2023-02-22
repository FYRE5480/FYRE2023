// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
//import for our navX2 board
import com.kauailabs.navx.frc.AHRS;
// import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
// import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


/** Subsystem designed for controlling the bottom driving motors and fetching encoder values. */
public class DriveTrain extends SubsystemBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    // Initialize our motors by referencing their ports.
    private final WPI_VictorSPX left1 = new WPI_VictorSPX(Constants.LEFT_MOTOR_PORT_A);
    private final WPI_VictorSPX right1 = new WPI_VictorSPX(Constants.RIGHT_MOTOR_PORT_A);

    private final WPI_VictorSPX left2 = new WPI_VictorSPX(Constants.LEFT_MOTOR_PORT_B);
    private final WPI_VictorSPX right2 = new WPI_VictorSPX(Constants.RIGHT_MOTOR_PORT_B);

    private final WPI_VictorSPX left3 = new WPI_VictorSPX(Constants.LEFT_MOTOR_PORT_C);
    private final WPI_VictorSPX right3 = new WPI_VictorSPX(Constants.RIGHT_MOTOR_PORT_C);

    // Package our motors into MotorControllerGroups to be added to a DifferentialDrive.
    private final MotorControllerGroup leftMotors = new MotorControllerGroup(left1, left2, left3);
    private final MotorControllerGroup rightMotors = new MotorControllerGroup(right1, right2, right3);
    private final DifferentialDrive diffDrive = new DifferentialDrive(leftMotors, rightMotors);

    // Initializes the nav board. 
    private AHRS ahrs;

    // Initialize our encoders to calculate wheel rotation in autonomous. 
    private final Encoder leftEncoder = new Encoder(
        Constants.LEFT_ENCODER_PORT_A, 
        Constants.LEFT_ENCODER_PORT_B, 
        false, 
        Encoder.EncodingType.k4X
    );

    private final Encoder rightEncoder = new Encoder(
        Constants.RIGHT_ENCODER_PORT_A, 
        Constants.RIGHT_ENCODER_PORT_B, 
        false, 
        Encoder.EncodingType.k2X
    );

    // Initialize our gyroscope for measuring the angle of the bot.
    // private final Gyro driveGyro = new ADXRS450_Gyro(Port.kOnboardCS0);

    /** 
     * Changes settings on the motors + encoders when instantiated.
     */
    public DriveTrain() {
        // Set the safety toggle and expiration on the motors + drivetrain.
        setupMotors(new WPI_VictorSPX[]{left1, right1, left2, right2, left3, right3});

        // Reset and prepare our encoders for calculation.
        setupEncoders(new Encoder[]{leftEncoder, rightEncoder});

        // Instantiates our navx2 board.
        this.ahrs = new AHRS(SPI.Port.kMXP); 
        // Calibrates the navboard
        // Robot needs to be still during this process.
        this.ahrs.calibrate();
    }

    /** 
     * Use the arcade drive mechanic to maneuver the robot's drivetrain. 

     * @param movementSpeed - The movement speed of the arcade drive system.
     * @param rotationalSpeed - The rotational speed of the arcade drive system.
     */
    public void arcadeDrive(double movementSpeed, double rotationalSpeed) {
        // Invert the movement speed if the invertedDrive setting is enabled.
        movementSpeed *= Constants.INVERTED_DRIVE ? -1 : 1;
        diffDrive.arcadeDrive(movementSpeed, rotationalSpeed);
    }

    /** 
     * Use the tank drive mechanic to maneuver the robot's drivetrain. 

     * @param movementSpeedLeft - The movement speed of the left set of motors. 
     * @param movementSpeedRight - The movement speed of the right set of motors. 
     */
    public void tankDrive(double movementSpeedLeft, double movementSpeedRight) {
        int multiplier = Constants.INVERTED_DRIVE ? -1 : 1;
        diffDrive.tankDrive(movementSpeedLeft * multiplier, movementSpeedRight * multiplier);
        
    }

    /**
     * Grab the values of a particular encoder. 

     * @param side - The distance traveled by a particular side of a robot.
     * @return - The current distance traveled of a particular encoder. 
     */
    public double getEncoder(String side) {
        switch (side) {
            case "left":
                return leftEncoder.getDistance();
                
            case "right":
                return rightEncoder.getDistance();

            default: 
                return 0.0;
        }
    }

    /**
     * Reset the values of an encoder or encoders.

     * @param side - The side of the robot to reset. 
     */
    public void resetEncoder(String side) {
        switch (side) {
            case "left":
                leftEncoder.reset();
                break;

            case "right":
                rightEncoder.reset();
                break;

            case "both":
                leftEncoder.reset();
                rightEncoder.reset();
                break;
            
            default:
                break;
        }
    }

    /**
     * Get the orientation of the gyroscope.

     * @return - The current orientation of the gyroscope.
     */
    public double getGyroscope() {
        return ahrs.getAngle();
    }

    /** 
     * Reset only the yaw on the gyroscope.
     */
    public void resetGyroscope() {
        ahrs.reset();
    }

    /** 
     * Gets the acceleration of the navX2 board in a specific direction. 

     * @param direction - should be 'X', 'Y', or 'Z' - 
     *      determines which direction of acceleration is returned
     * 
     * @return the acceleration in the given direction
     */
    public float getAcceleration(char direction) {
        switch (direction) {
            case 'X':
                return ahrs.getWorldLinearAccelX();
            case 'Y':
                return ahrs.getWorldLinearAccelY();
            case 'Z':
                return ahrs.getWorldLinearAccelZ();
            default:
                return 0;
        }
    }

    /** 
     * Gets the velocity of the navX2 board in a specific direction. 

     * @param direction - should be 'X', 'Y', or 'Z' - 
     *      determines which direction of velocity is returned
     * 
     * @return the velocity in the given direction
     */
    public float getVelocity(char direction) {
        switch (direction) {
            case 'X':
                return ahrs.getWorldLinearAccelX();
            case 'Y':
                return ahrs.getWorldLinearAccelY();
            case 'Z':
                return ahrs.getWorldLinearAccelZ();
            default:
                return 0;
        }
    }

    /** Resets all sensors to their respective zero values. */
    public void resetAhrs() {
        ahrs.calibrate();
        ahrs.zeroYaw();
    }

    /**
     * Toggles the safety and sets the expiration for all of the motor objects.

     * @param motors - An array of the motors to edit the properties of. 
     */
    public void setupMotors(WPI_VictorSPX[] motors) {
        for (WPI_VictorSPX motor : motors) {
            motor.setSafetyEnabled(Constants.SAFETY_TOGGLE);
            motor.setExpiration(Constants.EXPIRATION_TIME);
        }

        diffDrive.setSafetyEnabled(Constants.SAFETY_TOGGLE);
        diffDrive.setExpiration(Constants.EXPIRATION_TIME);
    }

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

    /** 
     * Add each of the calculations from our encoders and gyroscopes to our dashboard. 
     */
    @Override
    public void periodic() {
        SmartDashboard.putNumber("GYRO Angle Chart:", getGyroscope());
        SmartDashboard.putNumber("GYRO Reading:", getGyroscope() % 360);
        SmartDashboard.putNumber("Left Encoder Distance (revolutions)", getEncoder("left"));
        SmartDashboard.putNumber("Right Encoder Distance (revolutions)", getEncoder("right"));
        SmartDashboard.putNumber("Driving Throttle", Constants.THROTTLE);
        SmartDashboard.putNumber("Time Total:", DriverStation.getMatchTime());  
    }
}


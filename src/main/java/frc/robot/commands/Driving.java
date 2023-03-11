// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenixpro.signals.Led1OffColorValue;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

/** Handler for most events involving driving and chassis-motor manipulation. */
public class Driving extends CommandBase {
    // Initialize our variables for controlling drivetrain.
    private DriveTrain driveTrain;
    private double leftMovementSpeed;
    private double rightMovementSpeed;

    // Initialize our speed variables for controlling motor speeds.
    private double left;
    private double right;

    /**
     * Initialize our driving commands through our DriveTrain subsystem.

     * @param driveTrain The instance of the DriveTrain which is in line with the controller. 
     */
    public Driving(DriveTrain driveTrain) {
        this.driveTrain = driveTrain; 
        addRequirements(this.driveTrain); 
    }

    /** 
     * Apply the displacement of the controller sticks and apply it to our drivetrain.
     */
    @Override 
    public void execute() {
        // Get the current position of the joystick axis.
        // TODO: switch to XboxController. 

        left = RobotContainer.driverControl.getLeftY();
        right = -RobotContainer.driverControl.getRightY();

        System.out.println(left + " : left, " + right + " : right"); 

        // Reverse the movement speed if the robot is in tank drive.
        leftMovementSpeed = left;

        // Set the rotational speed to the x displacement.
        rightMovementSpeed = right;

        // Apply a deadband to the speed modifiers if they are negligible. 
        double[] speeds = new double[]{leftMovementSpeed, rightMovementSpeed};
        speeds = deadband(speeds);

        // Calculates the power to apply to each set of motors. 
        double leftPower = leftMovementSpeed * Constants.THROTTLE;
        double rightPower = rightMovementSpeed * Constants.THROTTLE;

        // Runs each set of motors based on their calculated power levels. 
        driveTrain.tankDrive(leftPower, rightPower);
    }

    /**
     * Checks if the displacement of each axis is sufficient enough for movement.

     * @param speeds - An array of speeds to compare to the deadband constant.
     */
    public double[] deadband(double[] speeds) { 
        for (int i = 0; i < speeds.length; i++) {
            if (Math.abs(speeds[i]) < Constants.DEADBAND) {
                speeds[i] = 0.0;
            }
        }

        return speeds;
    }
}

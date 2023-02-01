// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

/** Handler for most events involving driving and chassis-motor manipulation. */
public class Driving extends CommandBase {
    // Initialize our variables for controlling drivetrain.
    private DriveTrain driveTrain;
    private double movementSpeed;
    private double rotationalSpeed;

    // Initialize our speed variables for controlling motor speeds.
    private double horizontal;
    private double vertical;

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

        horizontal = RobotContainer.manipulatorControl.getX();
        vertical = -RobotContainer.manipulatorControl.getY(); 

        System.out.println(horizontal + " : horizontal, " + vertical + " : vertical"); 

        // Reverse the movement speed if the robot is in tank drive.
        movementSpeed *= vertical * (Constants.IS_TANK ? -1 : 1);

        // Set the rotational speed to the x displacement.
        rotationalSpeed = horizontal;

        // Apply a deadband to the speed modifiers if they are negligible. 
        double[] speeds = new double[]{ movementSpeed, rotationalSpeed };
        speeds = deadband(speeds);

        // Calculates the power to apply to each set of motors. 
        double leftPower = -(rotationalSpeed - movementSpeed) * Constants.THROTTLE;
        double rightPower = -(rotationalSpeed + movementSpeed) * Constants.THROTTLE;

        // Runs each set of motors based on their calculated power levels. 
        driveTrain.arcadeDrive(leftPower, rightPower);
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

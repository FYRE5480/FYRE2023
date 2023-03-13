// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

/** Handler for most events involving driving and chassis-motor manipulation. */
public class Driving extends CommandBase {
    // Initialize the DriveTrain subsytem.
    private DriveTrain driveTrain;

    // Initialize our speed variables for controlling motor speeds.
    private double leftStick;
    private double rightStick;
    private double rightLimitedStick = 0;
    private double leftLimitedStick = 0;

    // Fetch the driver controller from the RobotContainer.
    private XboxController driverControl;
    
    /**
     * Initialize our driving commands through our DriveTrain subsystem.

     * @param subsystem - The instance of the DriveTrain which is in line with the controller. 
     */
    public Driving(DriveTrain subsystem) {
        this.driveTrain = subsystem; 
        
        addRequirements(this.driveTrain); 
    }

    /** 
     * Apply the displacement of the controller sticks and apply it to our DriveTrain.
     */
    @Override 
    public void execute() {
        // Set the driverControl variable to our XboxController.
        driverControl = RobotContainer.driverControl; 

        // Get the values of the joysticks we will use for our particular drive.
        leftStick = Constants.IS_TANK ? driverControl.getLeftY() : driverControl.getLeftY();
        rightStick = Constants.IS_TANK ? driverControl.getRightY() : driverControl.getRightX(); 

        // Apply a deadband to the joystick directions if they are negligible. 
        double[] speeds = new double[]{ leftStick, rightStick };
        speeds = deadband(speeds);

        rightLimitedStick = driveTrain.limitAcceleration(rightStick, rightLimitedStick);
        leftLimitedStick = driveTrain.limitAcceleration(leftStick, leftLimitedStick);
        
        // Calculates the power to apply to each set of motors. 
        double leftPower = leftLimitedStick * Constants.THROTTLE;
        double rightPower = rightLimitedStick * Constants.THROTTLE;

        // Outputs the positions of each of the joystick axis. 
        System.out.println(leftPower + " : left stick, " + rightPower + " : right stick"); 

        // Runs each set of motors based on their calculated power levels. 
        if (Constants.IS_TANK) {
            driveTrain.tankDrive(leftPower, rightPower);
        } else {
            driveTrain.arcadeDrive(rightPower, leftPower);
        }
    }

    /**
     * Checks if the displacement of each axis is sufficient enough for movement.

     * @param speeds - An array of speeds to compare to the deadband constant.
     */
    public double[] deadband(double[] speeds) { 
        for (int i = 0; i < speeds.length; i++) {
            if (Math.abs(speeds[i]) < Constants.CONTROLLER_DEADBAND) {
                speeds[i] = 0.0;
            }
        }

        return speeds;
    }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenixpro.signals.Led1OffColorValue;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

/** Handler for most events involving driving and chassis-motor manipulation. */
public class Driving extends CommandBase {
    // Initialize the DriveTrain subsytem.
    private DriveTrain driveTrain;
    private double leftMovementSpeed;
    private double rightMovementSpeed;

    // Initialize our speed variables for controlling motor speeds.
    private double leftStick;
    private double rightStick;

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

        if (driverControl.getRightBumper()) {
            driveTrain.tankDrive(-0.7, -0.7);
            return;
        } else if (driverControl.getLeftBumper()) {
            driveTrain.tankDrive(0.7, 0.7); 
            return;
        }

        // Get the values of the joysticks we will use for our particular drive.
        leftStick = Constants.IS_TANK ? -driverControl.getLeftY() : driverControl.getLeftY();
        rightStick = Constants.IS_TANK ? driverControl.getRightY() : driverControl.getRightX(); 


        System.out.println(leftStick + " : left, " + rightStick + " : right"); 


        // Apply a deadband to the speed modifiers if they are negligible. 
        double[] speeds = new double[]{leftStick, rightStick};
        speeds = deadband(speeds);

        /* rightLimitedStick = driveTrain.limitAcceleration(rightStick, rightLimitedStick);
        leftLimitedStick = driveTrain.limitAcceleration(leftStick, leftLimitedStick); */ 

        // Perform calculations to limit the turning power. 
        double sum = Math.abs(leftStick + rightStick); // 0 - 2;
        double average = (sum / 2) * Constants.LIMIT_CONSTANT; // 0 - 0.3; 
        double limitedValue = 1 - average; // 0.7 - 1; 

        //System.out.println(Math.round(sum * 100.0) / 100.0 + " " + Math.round(average * 100.0) / 100.0 + " " + Math.round(limitedValue * 100.0) / 100.0);

        // Calculates the power to apply to each set of motors. 
        leftMovementSpeed = leftStick * Constants.THROTTLE;
        rightMovementSpeed = rightStick * Constants.THROTTLE;

        // Outputs the positions of each of the joystick axis. 
        // System.out.println(leftStick + " : left stick, " + rightStick + " : right stick"); 
        System.out.println(leftMovementSpeed + " : left power, " + rightMovementSpeed + " : right power"); 

        // Runs each set of motors based on their calculated power levels. 
        if (Constants.IS_TANK) {
            driveTrain.tankDrive(leftMovementSpeed, rightMovementSpeed);
        } else {
            driveTrain.arcadeDrive(rightMovementSpeed, leftMovementSpeed);
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

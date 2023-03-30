// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.SwitchCameras;
import frc.robot.subsystems.DriveTrain;
import java.util.Arrays;
import java.util.List;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    // Initialize commands for robot use, such as the auto command and container.
    private Command autoCommand;
    private RobotContainer robotContainer;


    // Create a dummy variable for storing the manipulator controller.
    private static Joystick joystick; 

    // Initialize our camera variables.
    private UsbCamera frontCam;
    private UsbCamera clawCam; 

    // Initialize our network map for switching between viewports, ana an array for cams.
    private NetworkTableEntry cameraSelector;
    private UsbCamera[] cameras;
    
    // Create arrays for storing "left" POV values and "right" POV values.
    private List<Integer> leftValues = Arrays.asList(new Integer[]{225, 270, 315});
    private List<Integer> rightValues = Arrays.asList(new Integer[]{45, 90, 135});

    // Variables for switching between cameras. 
    private boolean hasMoved = false; 
    private int currentIndex = 0; 

    /**
	 * This function is run when the robot is first started up and should be used for any
	 * initialization code.
	 */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer. 
        // This will perform all our button bindings & put our autonomous chooser on the dashboard.
        robotContainer = new RobotContainer();



        // Create a variable for the robot container's joystick.
        joystick = RobotContainer.manipulatorControl;

        // Creates a Camera object for each of the robot's viewports. 
        frontCam = CameraServer.startAutomaticCapture(0);
        clawCam = CameraServer.startAutomaticCapture(1);

        // Fetches a table of all of the current CameraServer instances.
        cameraSelector = NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection");

        // Push our cameras into the array. 
        cameras = new UsbCamera[]{frontCam, clawCam};

        
        SmartDashboard.putString("Balance Auto?", "'yes' or 'no'");
    }

    /**
     * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
     * that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
	public void robotPeriodic() {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }   

    /** This function is called once each time the robot enters Disabled mode. */
    @Override
	public void disabledInit() {}

    @Override
	public void disabledPeriodic() {}

    /** 
     * This autonomous runs the autonomous command selected by your {@link RobotContainer} class. 
	 */
    @Override
	public void autonomousInit() {
        autoCommand = robotContainer.getAutonomousCommand();
        autoCommand.schedule();
    
        // schedule the autonomous command (example)
        if (autoCommand != null) {
            System.out.println("command schedualed");
            autoCommand.schedule();
        }
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        try {
            autoCommand.cancel();
        } catch (Exception e) {
            // TODO: handle exception
            
        }
    }

    /** This function is called periodically during operator control. */
    @Override
  	public void teleopPeriodic() {
        int pov = joystick.getPOV();

        if (pov != -1) {
            if (pov == 0) {
                hasMoved = false; 
            }

            if (leftValues.contains(pov)) {
                cameraSelector.setString(
                    SwitchCameras.switchView(cameras, hasMoved, "l", currentIndex)
                );

                hasMoved = true; 
            } else if (rightValues.contains(pov)) {
                cameraSelector.setString(
                    SwitchCameras.switchView(cameras, hasMoved, "r", currentIndex)
                );

                hasMoved = true; 
            }
        }
    }

    @Override
  	public void testInit() {
    	// Cancels all running commands at the start of test mode.
    	CommandScheduler.getInstance().cancelAll();
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {}

    /** This function is called once when the robot is first started up. */
    @Override
  	public void simulationInit() {}

    /** This function is called periodically whilst in simulation. */
    @Override
	public void simulationPeriodic() {}
}

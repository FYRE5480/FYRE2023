// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.examples.ExampleCommand;
import frc.robot.examples.ExampleSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
    private final ExampleCommand autoCommand = new ExampleCommand(exampleSubsystem);

    // intake definitions
    private final Intake intake = new Intake();

    // Initialize our joystick for manipulation and controller for drivetrain.
    public static final Joystick manipulatorControl = new Joystick(0);
    public static final XboxController driverControl = new XboxController(1); 

    // Create new subsystems for the robot to pull from.
    private static DriveTrain driveTrain = new DriveTrain(); 
    private static Arm arm = new Arm();

    
    // A series of declared Joystick buttons for controlling the robot
    
    /** The big trigger on the front of the flight stick. Spins the intake forward */
    private final JoystickButton J1 = new JoystickButton(manipulatorControl, 1);

    /** The side button on the top of the flight stick where a right handed person's thumb would rest. Spins the intake backward */
    private final JoystickButton J2 = new JoystickButton(manipulatorControl, 2);


    

    /** 
     * The container for the robot. Contains subsystems, OI devices, and commands. 
     */
    public RobotContainer() {
        // Add a new Driving command to the drivetrain.
        driveTrain.setDefaultCommand(new Driving(driveTrain));

        arm.setDefaultCommand(new ActuateArm(arm));
        
	    //-> Series of declared Joystick buttons for controlling purposes. 


        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        // Note that the {button}.whileHeld({command}) has been depricated
        J1.whileTrue(new SpinIntake(intake, "forward"));
        J2.whileTrue(new SpinIntake(intake, "backward"));
         
        

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return autoCommand;
    }
}

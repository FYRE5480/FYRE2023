// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ActuateArm;
import frc.robot.commands.Driving;
import frc.robot.commands.SpinIntake;
import frc.robot.examples.ExampleCommand;
import frc.robot.examples.ExampleSubsystem;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;

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

    // Create new subsystems for the robot to pull from.
    private static Arm arm = new Arm();
    private static DriveTrain driveTrain = new DriveTrain(); 
    private static Intake intake = new Intake();

    // Initialize our joystick for manipulation and controller for drivetrain.
    public static final Joystick manipulatorControl = new Joystick(0);
    public static final XboxController driverControl = new XboxController(1); 
    
    // A series of declared Joystick buttons for controlling the robot.
    
    // Big trigger on the front of the stick. Unused.
    // private final JoystickButton joystick1 = new JoystickButton(manipulatorControl, 1);

    // Side button on the top of the stick where the thumb would rest. Unused.
    // private final JoystickButton joystick2 = new JoystickButton(manipulatorControl, 2);

    // Button on left side of stick where thumb lies. Actuates arm up or down. 
    private final JoystickButton joystick2 = new JoystickButton(manipulatorControl, 2);

    // Front-most button on the left-top of the stick. Runs intake backward.
    private final JoystickButton joystick3 = new JoystickButton(manipulatorControl, 3);

    // Front-most button on the right-top of the stick. Runs intake forward.
    private final JoystickButton joystick5 = new JoystickButton(manipulatorControl, 5);

    /** 
     * The container for the robot. Contains subsystems, OI devices, and commands. 
     */
    public RobotContainer() {
        // Add a new Driving command to the drivetrain.
        driveTrain.setDefaultCommand(new Driving(driveTrain));
        arm.setDefaultCommand(new ActuateArm(arm));

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
        joystick5.whileTrue(new SpinIntake(intake, "forward"));
        joystick3.whileTrue(new SpinIntake(intake, "backward"));

        joystick2.whileTrue(new ActuateArm(arm));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous.
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return autoCommand;
    }
}

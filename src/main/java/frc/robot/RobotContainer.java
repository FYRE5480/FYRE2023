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
import frc.robot.commands.ActuateClaw;
import frc.robot.commands.Driving;
import frc.robot.commands.SpinIntake;
import frc.robot.commands.TurnOnCompressor;
import frc.robot.examples.ExampleCommand;
import frc.robot.examples.ExampleSubsystem;
import frc.robot.subsystems.AirControl;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
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
    private static Claw claw = new Claw(); 
    private static DriveTrain driveTrain = new DriveTrain(); 
    private static Intake intake = new Intake();
    private static AirControl compressor = new AirControl();

    // Initialize our joystick for manipulation and controller for drivetrain.
    public static final Joystick manipulatorControl = new Joystick(0);
    public static final XboxController driverControl = new XboxController(1); 
    
    // A series of declared Joystick buttons for controlling the robot.
    
    /** Big trigger on the front of the stick. Toggles the claw. */
    private final JoystickButton joystick1 = new JoystickButton(manipulatorControl, 1);

    /** Button on left side of stick where thumb lies. Actuates arm fully up or down. */
    private final JoystickButton joystick2 = new JoystickButton(manipulatorControl, 2);

    /** Bottom-left button on the top of the stick. Runs intake backward. */
    private final JoystickButton joystick3 = new JoystickButton(manipulatorControl, 3);

    /** 
     * Bottom-right button on the top of the stick. 
     * Actuates the arm down while pressed, unless it is already actuating. 
     */
    private final JoystickButton joystick4 = new JoystickButton(manipulatorControl, 4);

    /** Top-left button on the top of the stick. Runs intake forward. */
    private final JoystickButton joystick5 = new JoystickButton(manipulatorControl, 5);

    /** 
     * Top-right button on the top of the stick. 
     * Actuates the arm up while pressed, unless it is already actuating. 
     */
    private final JoystickButton joystick6 = new JoystickButton(manipulatorControl, 6);

    /** 
     * Bottom right button on the base of the stick.
     * Turns on the compressor while pressed and held. 
     */
    private final JoystickButton joystick12 = new JoystickButton(manipulatorControl, 12);

    /** 
     * The container for the robot. Contains subsystems, OI devices, and commands. 
     */
    public RobotContainer() {
        // Add a new Driving command to the drivetrain.
        driveTrain.setDefaultCommand(new Driving(driveTrain));

        // Configure the button bindings.
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

        // Actuates the claw when a button is pressed
        joystick1.whileTrue(new ActuateClaw(claw)); 

        // Actuates the arm up or down when a button is pressed
        joystick2.whileTrue(new ActuateArm(arm, "full"));

        // Spins the intake forward or backward while two respective buttons are held
        joystick3.whileTrue(new SpinIntake(intake, "backward"));
        joystick5.whileTrue(new SpinIntake(intake, "forward"));

        // Actuates the arm up or down while two respective buttons are pressed
        joystick4.whileTrue(new ActuateArm(arm, "down"));
        joystick6.whileTrue(new ActuateArm(arm, "up"));

        // Turns on the compressor while the button is held
        joystick12.whileTrue(new TurnOnCompressor(compressor, false));
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

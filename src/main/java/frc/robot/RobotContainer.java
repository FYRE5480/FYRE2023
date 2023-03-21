// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import edu.wpi.first.wpilibj.Compressor;
//simport edu.wpi.first.wpilibj.PneumaticsModuleType;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ActuateArm;
import frc.robot.commands.ActuateClaw;
import frc.robot.commands.ActuateIntake;
import frc.robot.commands.Autonomous;
import frc.robot.commands.Driving;
import frc.robot.commands.SpinIntake;
// import frc.robot.commands.TurnOnCompressor;
import frc.robot.examples.ExampleCommand;
import frc.robot.examples.ExampleSubsystem;
//import frc.robot.subsystems.AirControl;
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
    private final Autonomous autoCommand = new Autonomous(driveTrain, intake);

    // Create new subsystems for the robot to pull from.
    private static Arm arm = new Arm();
    private static Claw claw = new Claw(); 
    private static DriveTrain driveTrain = new DriveTrain(); 
    private static Intake intake = new Intake();
    //private static AirControl compressor = new AirControl();
    //private Compressor compressor = new Compressor(
    //    Constants.COMPRESSOR_PCM_PORT, PneumaticsModuleType.CTREPCM
    //);
    
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
     * Middle-left button on the base of the stick.
     * Actuates the intake up while pressed, unless it is already actuating. 
     */
    private final JoystickButton joystick9 = new JoystickButton(manipulatorControl, 9);

    /** 
     * Middle-right button on the base of the stick.
     * Actuates the intake up while pressed, unless it is already actuating. 
     */
    private final JoystickButton joystick10 = new JoystickButton(manipulatorControl, 10);

    /** 
     * The container for the robot. Contains subsystems, OI devices, and commands. 
     */
    public RobotContainer() {
        // Add a new Driving command to the drivetrain.
        driveTrain.setDefaultCommand(new Driving(driveTrain));

        // Configure the button bindings.
        //compressor.enableAnalog(0, 60);
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

        // opens the claw when a button is pressed
        // joystick1.whileTrue(new ActuateClaw(claw, "open")); 

        // Actuates the arm up or down when a button is pressed
        //joystick2.whileTrue(new ActuateArm(arm, "full"));

        //closes the claw while held
        joystick2.whileTrue(new ActuateClaw(claw, "closed")); 


        joystick3.and(joystick9).whileTrue(new SpinIntake(intake, "backward", true, "lower"));
        joystick3.and(joystick10).whileTrue(new SpinIntake(intake, "backward", true, "lift"));
        joystick5.and(joystick9).whileTrue(new SpinIntake(intake, "forward", true, "lower"));
        joystick5.and(joystick10).whileTrue(new SpinIntake(intake, "forward", true, "lift"));

        
        // Spins the intake forward or backward while two respective buttons are held
        joystick3.whileTrue(new SpinIntake(intake, "backward", false, null));
        joystick5.whileTrue(new SpinIntake(intake, "forward", false, null));
        joystick5.and(joystick1).whileTrue(new SpinIntake(intake, "forwardFast", false, null));

        // Actuates the intake up or down  while two respective buttons are held
        joystick9.whileTrue(new ActuateIntake(intake, "lower"));
        joystick10.whileTrue(new ActuateIntake(intake, "lift"));

        // Actuates the arm up or down while two respective buttons are pressed
        joystick4.whileTrue(new ActuateArm(arm, "down"));
        joystick6.whileTrue(new ActuateArm(arm, "up"));

        //SmartDashboard.putNumber("Current PSI", compressor.getPressure());
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

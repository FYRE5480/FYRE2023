package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

/**
 * Handler containing methods relating to the actuation of the arm. 
 */
public class ActuateArm extends CommandBase {
    // Initialize the Arm subsystem. 
    private final Arm arm;

    // Initialize a variable for arm movement direction.
    private String direction;

    /**
	 * Initialize our arm actuation through the Arm subsystem.
	 *
	 * @param subsystem - The Arm subsystem used by this command.
     * @param direction - The direction of actuation. 
	 */
    public ActuateArm(Arm subsystem, String direction) {
        this.arm = subsystem;
        this.direction = direction; 
        
        addRequirements(this.arm);
    }

    /**
     * Runs whatever methods are currently prompted from the arm.  
     */
    @Override
    public void execute() {
        // If a button for actuating the arm is pressed, perform actuation.
        actuate(); 
    }

    /**
     * Checks to see if the arm is able to actuate freely and is not 
     * presently in motion. If it is, it will perform the proper actuation.
     */
    public void actuate() {
        switch (direction) {
            case "up":
                arm.actuateUp();
                break;

            case "down":
                arm.actuateDown();
                break;
            // case "full":
            //     actuateArmFull();
            //     break;
            default:
                // will force the arm to stay up until the driver presses down
                if ((arm.getEncoder() == 90 || arm.getEncoder() == -90) && (arm.getEncoder() <= 95 || arm.getEncoder() >= -95)) {
                    arm.actuateUp();
                //} else {
                    //arm.stopMotor();
                }
                break;
        }
    }

    /**
     * Actuates the arm to full upper or full lower according to 
     * the current readings of the limit switches. 
     */
    public void actuateArmFull() {
        actuationInMotion = true; 

        if (arm.getSwitchReading("upper")) {
            arm.actuateDownFull();
        } else if (arm.getSwitchReading("lower")) {
            arm.actuateUpFull(); 
        }

        actuationInMotion = false; 
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        arm.stopMotor();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    	return false;
    }
}

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;

public class ActuateArm extends CommandBase {
    
    private final Arm arm;

    private boolean buttonUp;
    private boolean buttonDown;
    private boolean buttonSwitch;


    /**
	 * Creates a new ExampleCommand.
	 *
	 * @param subsystem The subsystem used by this command.
	 */
    public ActuateArm(Arm arm) {
        this.arm = arm;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(this.arm);
    }

    public void moveArmOneButton() {
        if(arm.getSwitchReading("upper")){
            while(!arm.getSwitchReading("lower")){
                arm.moveDown();
            }
            arm.stopMotor();
        } else if(arm.getSwitchReading("lower")){
            while(!arm.getSwitchReading("upper")){
                arm.moveUp();
            }
            arm.stopMotor();
        }
    }

    public void moveArmTwoButton(boolean up) {
        if(up && !arm.getSwitchReading("upper")){
            arm.moveUp();
        } else if(!up && !arm.getSwitchReading("lower")){
            arm.moveDown();
        } else if(up) {
            arm.moveUp();
        } else if(!up){
            arm.moveDown();
        } else {
            arm.stopMotor();
        }
    }
    
    // Called when the command is initially scheduled.
    @Override
	public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        buttonSwitch = RobotContainer.manipulatorControl.getRawButton(2);
        buttonUp = RobotContainer.manipulatorControl.getRawButton(5);
        buttonDown = RobotContainer.manipulatorControl.getRawButton(3);

        if(buttonUp){
            moveArmTwoButton(true);
        } else if(buttonDown){
            moveArmTwoButton(false);
        } else if(buttonSwitch){
            moveArmOneButton();
        }

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
    	return false;
    }
    
}

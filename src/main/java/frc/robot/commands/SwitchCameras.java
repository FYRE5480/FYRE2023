package frc.robot.commands;

import edu.wpi.first.cscore.UsbCamera;

/**
 * Method contaier for switching between cameras on the joystick.
 */
public class SwitchCameras {
    /**
     * Attempts to switch the viewport on the SmartDashboard, depending on
     * the direction of the joystick POV and if it has already been switched 
     * previously. 
     *
     * @param has - Checks if a switch has already occurred previously. 
     * @param direction - The current direction of the joystick POV. 
     * @return - The String of the new camera to switch to. 
     */
    public static String switchView(UsbCamera[] cameras, boolean has, String direction, int index) {
        if (!has) {
            if (direction.equals("l")) {
                return cameras[
                    index - 1 < 0 ? cameras.length - 1 : index - 1
                ].getName();
            } else if (direction.equals("r")) {
                return cameras[
                    index + 1 >= cameras.length ? 0 : index + 1
                ].getName();
            }
        }

        return cameras[index].getName(); 
    }
}

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Constants;


/** The class for operating a compressor. */
public class AirControl {

    // Placeholder variables for the minimum and maximum PSI the compressor should recognize
    // These can (and proabably should) be moved to constants when a permanent value is found
    private int minPsi = 0;
    private int maxPsi = 0;

    private Compressor compressor = new Compressor(
        Constants.COMPRESSOR_PCM_PORT, PneumaticsModuleType.CTREPCM
    );

    /** Creates a new Compressor subsystem. */
    public AirControl() {
        compressor.enableAnalog(minPsi, maxPsi);
    }


    
}

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Constants;


/** The class for operating a compressor. */
public class AirControl {

    /**
     * Local variable to keep track of the minimum PSI for the compressor. 
     * The local variable exists because we might want to change the values
     * from the default set in the constants file.
    */
    private int minPsi;

    /**
     * Local variable to keep track of the maximum PSI for the compressor. 
     * The local variable exists because we might want to change the values
     * from the default set in the constants file.
    */
    private int maxPsi;

    // These variables are set to the constant defualt in the constructor

    private Compressor compressor = new Compressor(
        Constants.COMPRESSOR_PCM_PORT, PneumaticsModuleType.CTREPCM
    );

    /** Creates a new Compressor subsystem. */
    public AirControl() {
        this.minPsi = Constants.COMPRESSOR_MIN_PSI;
        this.maxPsi = Constants.COMPRESSOR_MAX_PSI;
    }


    /** Turns the compressor on when called. */
    public void turnOnCompressor() {
        compressor.enableAnalog(this.minPsi, this.maxPsi);
    }

    /** Turns the compressor off when called. */
    public void turnOffCompressor() {
        compressor.disable();
    }
    
}

package edu.wpi.first.wpilibj.templates.armAngle;

import edu.wpi.first.wpilibj.Encoder;

/**
 *
 * @author apalepu
 */
public class ArmEncoder extends Encoder{
    private boolean isCalibrated = false;
    
    public ArmEncoder(final int aSlot, final int aChannel,
            final int bSlot, final int bChannel,
            boolean reverseDirection, final EncodingType encodingType){
        super(aSlot, aChannel, bSlot, bChannel, reverseDirection, encodingType);
    }
    
    public void calibrate() {
        //this resets
        reset();
        //this starts
        start();
        isCalibrated = true;
    }
    
    //public void setUpperPoint() {}
    
    public boolean isCalibrated() {
        return isCalibrated;
    }
    
    public double pidGet() {
        return getDistance();
    }
}
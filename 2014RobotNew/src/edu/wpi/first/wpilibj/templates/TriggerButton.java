package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.InternalButton;

/**
 *
 * @author skodali
 */
public class TriggerButton extends InternalButton{
    
    
    private final Joystick joystick;
    private final int axisNumber;
    private final Hand hand;
    
    public TriggerButton(Joystick joystick, int axisNumber, Hand hand){
        this.joystick = joystick;
        this.axisNumber = axisNumber;
        this.hand = hand;
    }
     
    
    public boolean get(){
        if(hand == Hand.kLeft){
            return joystick.getRawAxis(axisNumber) > 0.5;
        }else{
            return joystick.getRawAxis(axisNumber) < -0.5;
        }
    }
    
}

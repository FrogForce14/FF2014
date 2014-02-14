/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.InternalButton;

/**
 *
 * @author skodali
 */
public class TriggerButton extends InternalButton{
    Joystick joystick;
    int axisNumber;
    boolean isRight;
    public TriggerButton(Joystick joystick, int axisNumber, boolean isRight){
        this.joystick = joystick;
        this.axisNumber = axisNumber;
        this.isRight = isRight;
    }
     
    
    public boolean get(){
        if(isRight == true){
            return joystick.getRawAxis(axisNumber) > 0.5;
        }else{
            return joystick.getRawAxis(axisNumber) < -0.5;
        }
    }
    
}

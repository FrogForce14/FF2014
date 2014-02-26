/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author skodali
 * 
 * For testing
 */
public class PrintTalon extends Talon{
    
     public PrintTalon(final int channel) {
        super(channel);
    }

    /**
     * Constructor that specifies the digital module.
     *
     * @param slot The slot in the chassis that the digital module is plugged into.
     * @param channel The PWM channel on the digital module that the Victor is attached to.
     */
    public PrintTalon(final int slot, final int channel) {
        super(slot, channel);
    }
    
    public void set(double speed){
        System.out.println(speed);
    }
    
}

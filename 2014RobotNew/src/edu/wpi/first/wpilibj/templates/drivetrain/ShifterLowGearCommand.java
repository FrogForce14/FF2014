/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.drivetrain;

import edu.wpi.first.wpilibj.templates.InstantaneousCommand;

/**
 *
 * @author skodali
 */
public class ShifterLowGearCommand extends InstantaneousCommand {
    protected void action(){
        Drivetrain.lowGear();
    }
}

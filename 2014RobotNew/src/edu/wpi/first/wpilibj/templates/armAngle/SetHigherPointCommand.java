package edu.wpi.first.wpilibj.templates.armAngle;

import edu.wpi.first.wpilibj.templates.InstantaneousCommand;

/**
 * 
 * @author skodali
 */
public class SetHigherPointCommand extends InstantaneousCommand {
    protected void action(){
        Angler.setHigherLimit();
    }
}

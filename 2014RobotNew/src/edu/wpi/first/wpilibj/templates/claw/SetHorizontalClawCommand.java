package edu.wpi.first.wpilibj.templates.claw;

import edu.wpi.first.wpilibj.templates.InstantaneousCommand;
import edu.wpi.first.wpilibj.templates.claw.Claw.Position;

/**
 *
 * @author Bryce
 */
public class SetHorizontalClawCommand extends InstantaneousCommand {
    
    public final Position position;
    
    public SetHorizontalClawCommand(Position position) {
        this.position = position;
    }
    
    protected void action(){
        Claw.horizontalClawSet(position);
    }
}

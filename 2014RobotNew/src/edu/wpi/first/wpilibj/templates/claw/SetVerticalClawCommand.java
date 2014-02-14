package edu.wpi.first.wpilibj.templates.claw;

import edu.wpi.first.wpilibj.templates.InstantaneousCommand;
import edu.wpi.first.wpilibj.templates.claw.Claw.Position;

/**
 *
 * @author Bryce
 */
public class SetVerticalClawCommand extends InstantaneousCommand {
    
    public final Position position;
    
    public SetVerticalClawCommand(Position position) {
        this.position = position;
    }
    
    protected void action(){
        Claw.verticalClawSet(position);
    }
}

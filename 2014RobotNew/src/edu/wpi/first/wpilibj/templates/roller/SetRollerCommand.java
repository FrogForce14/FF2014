package edu.wpi.first.wpilibj.templates.roller;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.roller.Roller.Direction;

/**
 *
 * @author skodali
 */
public class SetRollerCommand extends Command{
    
    public final Direction direction;
    
    public SetRollerCommand(Direction direction) {
        this.direction = direction;
    }//super just refers to instant command instead of normal comand like normal. 

     protected final void initialize(){
         Roller.set(direction);
    }
    
    protected final void execute() {
    }

    protected final boolean isFinished() {
        return !OI.rollerIn.get() && !OI.rollerOut.get();
    }

    protected final void end() {
        Roller.set(Roller.Direction.OFF);
    }

    protected final void interrupted() {
    }
}
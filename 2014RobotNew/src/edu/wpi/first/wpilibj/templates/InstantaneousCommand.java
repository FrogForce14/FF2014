package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.command.Command;

/**
 * so this is a really cool class, it is a command that will always finish imediately. I get it, but why? so that commands like setRollerComamnd are simlper
 * @author Anil is...yeah
 */
public abstract class InstantaneousCommand extends Command {
    
    protected abstract void action();
    
    protected final void initialize(){
        action();
    }
    
    protected final void execute() {
    }

    protected final boolean isFinished() {
        return true;
    }

    protected final void end() {
    }

    protected final void interrupted() {
    }
}

package edu.wpi.first.wpilibj.templates.winch;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author skodali
 */
public class PullBackWinchCommand extends Command {
    
    public PullBackWinchCommand() {
        //requires(Winch.getInstance());
        this.setInterruptible(false);
    }

    protected void initialize() {   
        Winch.pullBack();
    }
    
    protected boolean isFinished() {
        return !Winch.isPullingBack();
    }

    protected void execute() {}
    protected void end() {}
    protected void interrupted() {}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.winch;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author skodali
 */
public class PullBackWinchCommand extends Command {
    
   
    
    public PullBackWinchCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Winch.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {   
        Winch.pullBack();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Winch.isPullingBack();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

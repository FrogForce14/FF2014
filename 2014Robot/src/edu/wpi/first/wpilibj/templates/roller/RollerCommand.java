/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.roller;

import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author skodali
 */
public class RollerCommand extends Command {
    
    
    Value getValue;
    
    public RollerCommand(Value value) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Roller.getInstance());
        getValue = value;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Roller.set(getValue);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

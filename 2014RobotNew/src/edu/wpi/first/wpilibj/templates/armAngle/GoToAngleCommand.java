package edu.wpi.first.wpilibj.templates.armAngle;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.templates.armAngle.Angler.Angle;

/**
 * 
 * @author skodali
 */
public class GoToAngleCommand extends Command {
    public final Angle angle;
    public GoToAngleCommand(Angle angle) {
        requires(Angler.getInstance());
        setInterruptible(false);
        this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Angler.setSetpoint(angle.angle);
        Angler.pidEnable(); 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Angler.onTarget() && Angler.isStopped();
    }

    // Called once after isFinished returns true
    protected void end() {
        Angler.pidDisable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}


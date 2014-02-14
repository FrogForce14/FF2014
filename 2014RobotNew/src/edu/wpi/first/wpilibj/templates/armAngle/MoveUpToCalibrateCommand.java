package edu.wpi.first.wpilibj.templates.armAngle;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author skodali
 */
public class MoveUpToCalibrateCommand extends Command {
    public final double speed;
    public MoveUpToCalibrateCommand(double speed) {
        requires(Angler.getInstance());
        this.speed = speed;
    }
    
    public MoveUpToCalibrateCommand(double speed, double timeout) {
        requires(Angler.getInstance());
        this.speed = speed;
        setTimeout(timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("MoveUpCommand");
        Angler.setSpeed(Math.abs(speed));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return  Angler.isCalibrateLimitSwitchPressed() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package edu.wpi.first.wpilibj.templates.armAngle;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author skodali
 */
public class MoveDownCommand extends Command {
    public final double speed;
    
    public MoveDownCommand(double speed, double timeout) { 
        super("Move Arm Down");
        requires(Angler.getInstance());
        this.speed = speed;
        setTimeout(timeout);
    }

    protected void initialize() {
    }

    protected void execute() {
        Angler.setSpeed(Math.abs(speed) * -1);
    }

    protected boolean isFinished() {
        return  Angler.isLowerLimitSwitchPressed() || isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

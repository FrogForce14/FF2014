package edu.wpi.first.wpilibj.templates.armAngle;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author skodali
 */
public class MoveUpCommand extends Command {
    public final double speed;
    public MoveUpCommand(double speed) {
        super("Move Arm Up");
        requires(Angler.getInstance());
        this.speed = speed;
    }
    
    public MoveUpCommand(double speed, double timeout) {
        super("Move Arm Up");
        requires(Angler.getInstance());
        this.speed = speed;
        setTimeout(timeout);
    }

    protected void initialize() {
    }

    protected void execute() {
        Angler.setSpeed(Math.abs(speed));
    }

    protected boolean isFinished() {
        return  Angler.isUpperLimitSwitchPressed() || isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

package edu.wpi.first.wpilibj.templates.armAngle;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author skodali
 */
public class MoveUpToCalibrateCommand extends Command {
    public final double speed;
    public MoveUpToCalibrateCommand(double speed) {
        super("Move Arm Up To Calibrate");
        requires(Angler.getInstance());
        this.speed = speed;
    }
    
    public MoveUpToCalibrateCommand(double speed, double timeout) {
        super("Move Arm Up To Calibrate");
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
        return  Angler.isCalibrateLimitSwitchPressed() || isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

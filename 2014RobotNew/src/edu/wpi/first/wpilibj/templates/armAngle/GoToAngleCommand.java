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
        super("Arm Go To " + angle.angle);
        requires(Angler.getInstance());
        setInterruptible(true);
        this.angle = angle;
    }

    protected void initialize() {
        Angler.setSetpoint(angle.angle);
        Angler.pidEnable(); 
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Angler.onTarget() && Angler.isStopped();
    }

    protected void end() {
        Angler.pidDisable();
    }

    protected void interrupted() {
        end();
    }
}


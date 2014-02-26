package edu.wpi.first.wpilibj.templates.armAngle;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Suhas
 */
public class ManualAnglerCommand extends Command {
    
    public ManualAnglerCommand() {
        super("Manual Angle");
        requires(Angler.getInstance());
        setInterruptible(true);//neeed to be able to interrupt it with buttons.
    }

    protected void initialize() {
    }

    protected void execute() {
        SmartDashboard.putBoolean("Manual Angler ", true);
        Angler.manualAngler();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
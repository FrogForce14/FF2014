package edu.wpi.first.wpilibj.templates.armAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author apalepu
 */
public class CalibrateCommandGroup extends CommandGroup {
    public final static double CALIBRATE_FAST_SPEED = .5;
    public final static double CALIBRATE_SLOW_SPEED = .2;
    public CalibrateCommandGroup() {
        addSequential(new MoveUpToCalibrateCommand(CALIBRATE_FAST_SPEED));
        addSequential(new MoveDownCommand(CALIBRATE_SLOW_SPEED, .25));
        addSequential(new MoveUpToCalibrateCommand(CALIBRATE_SLOW_SPEED));
        addSequential(new SetHigherPointCommand());
    }
}

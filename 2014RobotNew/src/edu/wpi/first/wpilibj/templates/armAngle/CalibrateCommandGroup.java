package edu.wpi.first.wpilibj.templates.armAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author apalepu
 */
public class CalibrateCommandGroup extends CommandGroup {
    public final static double CALIBRATE_FAST_SPEED = .5;
    public final static double CALIBRATE_SLOW_SPEED = .3;
    public CalibrateCommandGroup() {
        super("Arm Calibrate");
        //addSequential(new MoveDownCommand(CALIBRATE_FAST_SPEED, .25));
        //addSequential(new MoveUpToCalibrateCommand(CALIBRATE_FAST_SPEED));
        addSequential(new MoveDownCommand(CALIBRATE_SLOW_SPEED, .5));
        addSequential(new MoveUpToCalibrateCommand(CALIBRATE_FAST_SPEED));
        addSequential(new SetHigherPointCommand());
    }
}

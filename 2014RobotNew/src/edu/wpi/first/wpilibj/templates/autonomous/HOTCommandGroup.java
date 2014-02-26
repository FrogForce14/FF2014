package edu.wpi.first.wpilibj.templates.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.armAngle.Angler.Angle;
import edu.wpi.first.wpilibj.templates.armAngle.CalibrateCommandGroup;
import edu.wpi.first.wpilibj.templates.armAngle.GoToAngleCommand;
import edu.wpi.first.wpilibj.templates.drivetrain.DriveStraightCommand;
import edu.wpi.first.wpilibj.templates.drivetrain.ShifterHighGearCommand;

/**
 *
 * @author skodali
 */
public class HOTCommandGroup extends CommandGroup {
    
    
    double rotations = 5;
    
    
    public HOTCommandGroup() {
        addParallel(new ShifterHighGearCommand());
        addSequential(new CalibrateCommandGroup());
        addParallel(new DriveStraightCommand(-rotations*2));
        addSequential(new GoToAngleCommand(Angle.HIGH_SHOT));
        addSequential(new AutonFireCommandGroup());
    }
}

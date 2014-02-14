package edu.wpi.first.wpilibj.templates.winch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.armAngle.GetDistance;
import edu.wpi.first.wpilibj.templates.claw.Claw.Position;
import edu.wpi.first.wpilibj.templates.claw.SetHorizontalClawCommand;
import edu.wpi.first.wpilibj.templates.claw.SetVerticalClawCommand;

/**
 *
 * @author Bryce
 */
public class FireCommandGroup extends CommandGroup {
    public FireCommandGroup(){
        setInterruptible(false);
        addSequential(new GetDistance());
        addSequential(new SetVerticalClawCommand(Position.OPEN));
        addSequential(new SetHorizontalClawCommand(Position.OPEN));
        addSequential(new WaitCommand(.15));
        addSequential(new DisengageSafetyCommand());
        addSequential(new WaitCommand(.125));
        addSequential(new DisengageWinchCommand());
        addSequential(new WaitCommand(2));
        addSequential(new EngageWinchCommand());
        addParallel(new EngageSafetyCommand());
        addSequential(new PullBackWinchCommand());
    }
}

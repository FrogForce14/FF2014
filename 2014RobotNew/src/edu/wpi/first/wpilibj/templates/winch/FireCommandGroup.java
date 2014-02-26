package edu.wpi.first.wpilibj.templates.winch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.armAngle.PrintAngle;
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
        addShot();
        addPostShot();
    }
    
    protected void addShot(){
        addParallel(new PrintAngle());
        addParallel(new SetVerticalClawCommand(Position.OPEN));
        addParallel(new SetHorizontalClawCommand(Position.OPEN));
        addSequential(new WaitCommand(.125));
        addParallel(new DisengageSafetyCommand());
        addSequential(new WaitCommand(.125));
        addSequential(new DisengageWinchCommand());
        addSequential(new WaitCommand(.75));
    }
    
    protected void addPostShot(){
        addSequential(new EngageWinchCommand());
        addParallel(new EngageSafetyCommand());
        addSequential(new PullBackWinchCommand());
    }
}

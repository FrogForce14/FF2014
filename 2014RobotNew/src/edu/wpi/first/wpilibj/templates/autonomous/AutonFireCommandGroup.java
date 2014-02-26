package edu.wpi.first.wpilibj.templates.autonomous;

import edu.wpi.first.wpilibj.templates.winch.FireCommandGroup;

/**
 *
 * @author Bryce
 */
public class AutonFireCommandGroup extends FireCommandGroup {
    
    public AutonFireCommandGroup() {
        addShot();
        addSequential(new StartAutonDrivingCommand());
        addParallel(new AutonSetClawsCommand());
        addPostShot();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.armAngle.Angler.Angle;
import edu.wpi.first.wpilibj.templates.armAngle.CalibrateCommandGroup;
import edu.wpi.first.wpilibj.templates.armAngle.GoToAngleCommand;
import edu.wpi.first.wpilibj.templates.drivetrain.DriveStraightCommand;
import edu.wpi.first.wpilibj.templates.claw.SetVerticalClawCommand;
import edu.wpi.first.wpilibj.templates.claw.Claw.Position;
import edu.wpi.first.wpilibj.templates.claw.SetHorizontalClawCommand;
import edu.wpi.first.wpilibj.templates.drivetrain.ShifterHighGearCommand;
import edu.wpi.first.wpilibj.templates.winch.FireCommandGroup;

/**
 *
 * @author skodali
 */
public class NOTCommandGroup extends CommandGroup {
    
    
    double rotations = 5;
    double notWait = 2;
    
    public NOTCommandGroup() {
        addParallel(new ShifterHighGearCommand());
        addSequential(new CalibrateCommandGroup());
        addParallel(new DriveStraightCommand(-rotations*2));
        addSequential(new GoToAngleCommand(Angle.HIGH_SHOT));
        addSequential(new WaitCommand(notWait));
        addSequential(new GoToAngleCommand(Angle.HIGH_SHOT));
        addSequential(new AutonFireCommandGroup());
    }
}

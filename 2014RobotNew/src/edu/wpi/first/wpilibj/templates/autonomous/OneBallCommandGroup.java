/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.armAngle.Angler.Angle;
import edu.wpi.first.wpilibj.templates.armAngle.GoToAngleCommand;
import edu.wpi.first.wpilibj.templates.drivetrain.DriveStraightCommand;
import edu.wpi.first.wpilibj.templates.claw.SetVerticalClawCommand;
import edu.wpi.first.wpilibj.templates.claw.Claw.Position;
import edu.wpi.first.wpilibj.templates.winch.FireCommandGroup;

/**
 *
 * @author skodali
 */
public class OneBallCommandGroup extends CommandGroup {
    
    
    double rotations = 17;
    
    
    public OneBallCommandGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    addSequential(new GoToAngleCommand(Angle.LOW_SHOT));
    addSequential(new SetVerticalClawCommand(Position.OPEN));
    addSequential(new FireCommandGroup());
    addSequential(new WaitCommand(.5));
    addSequential(new SetVerticalClawCommand(Position.CLOSE));
    addSequential(new DriveStraightCommand(rotations*2));
    }
}

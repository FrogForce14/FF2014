/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author skodali
 */
public class TeleopDriveCommand extends Command {
    
    public TeleopDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Drivetrain.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to . commandbase is dumb, don't use it.
    protected void execute() {// All these commands run automatically or do you need to call them s omewhere? need to call them. Not all of them will go in 
        Drivetrain.teleopDrive();
    }//what i think you should do is make code with all of them, and then we can fix it to make it more ideal afterwards once it's working. All of the group memebrs? idk up to you. All of 'them"? I'd actually set up github so they coudl veiw it easily from home (and then abondon it once competitions start to avoid any confusion. Ok

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Drivetrain.setMotors(0, 0);//make a set motors function for this. or even just a .stop()
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

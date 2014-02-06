/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.camera;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author skodali
 */
public class HotOrNot extends Command {
    
    Camera camera = Camera.getInstance();
    int x = 0;
    
    public HotOrNot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(camera);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
            camera.getImage();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.camera;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.templates.autonomous.OneBallCommandGroup;
import edu.wpi.first.wpilibj.templates.camera.Camera.CameraRunnable;

/**
 *
 * @author skodal
 */
public class HotOrNot extends Command {
    CameraRunnable cameraRunnable = new CameraRunnable();
    Thread camera;
    //change in WaitCommandGroup as well.
    
    public HotOrNot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Camera.getInstance());
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        camera = new Thread(cameraRunnable);
        camera.start();
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(cameraRunnable.isDone){
            
            if(cameraRunnable.isHot){
                //(new OneBallCommandGroup()).start();
                System.out.println("SOOO HOOOOOT");
                cancel();
            }else{
                //(new WaitThenShootCommandGroup()).start();
                System.out.println("SOOOO NOOOOOT");
                cancel();
            }
          
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return cameraRunnable.isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

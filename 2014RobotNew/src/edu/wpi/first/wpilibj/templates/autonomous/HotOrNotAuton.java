package edu.wpi.first.wpilibj.templates.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.templates.camera.Camera;
import edu.wpi.first.wpilibj.templates.camera.Camera.CameraRunnable;

/**
 *
 * @author skodal
 */
public class HotOrNotAuton extends Command {
    CameraRunnable cameraRunnable = new CameraRunnable();
    Thread camera;
    
    public HotOrNotAuton() {
        requires(Camera.getInstance());
        
    }

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
                Mode.AUTON_PRE_SHOT.addCommand(new HOTCommandGroup());
            }else{
                //(new WaitThenShootCommandGroup()).start();
                System.out.println("SOOOO NOOOOOT");
                Mode.AUTON_PRE_SHOT.addCommand(new NOTCommandGroup());
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
        System.out.println("HotOrNotAuton Interrupted");
    }
}

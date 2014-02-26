/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author skodali
 */
public class TurnCommand extends Command implements PIDSource, PIDOutput{
    
    double rotationDistance;
    
    
    public TurnCommand(double rotationDistance, double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.rotationDistance = rotationDistance;
    }
    
    
    public double pidGet(){
        return Drivetrain.getRightDistance() - Drivetrain.getLeftDistance();
    }
    
    
    public void pidWrite(double rotation){
        Drivetrain.arcadeDrive(0, rotation);
    }
    

    private double P = .05, I = 0,  D = 0;
    
    /**                                             
     * PID controller. 
     */
    private final PIDController pid = new PIDController(P, I, D, this, this);
    
    /**
     * Called once when the command is scheduled to run.
     * Resets and starts the encoders and PID controller.
     */
    protected void initialize() {
        Drivetrain.resetEncoders();
        Drivetrain.startEncoders();  
        pid.reset();
        pid.setSetpoint(rotationDistance);
        pid.enable();  
        }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
        pid.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author skodali
 */
public class DriveStraightCommand extends Command implements PIDSource, PIDOutput{
    
    /**
     * Creates a new command to drive straight the specified distance.
     * Each side of the drivetrain should move distance/2 rotations.
     * @param distance 
     */
    public DriveStraightCommand(double distance) {
        super("DriveStraightCommand"); //==never really got the point of this==// //This is just so that it knows what to be called on smart dashboard
        requires(Drivetrain.getInstance()); //==I think I remember why we need this==// makes it so only one drivetrain can run
        this.distance = distance; //== no idea==// Ah, I see
        setTimeout(distance*.1); //==wut is timeout==// 
    }
    
    /**
     * Returns the distance the drivetrain has moved. Used by the PID controller.
     * @return distance moved
     * @see PIDController
     * @see PIDSource
     */
    public double pidGet() {
        double tempDistance = Drivetrain.getDistance(); //==ok==//
        SmartDashboard.putNumber("Drive Straight Error", (pid.getSetpoint() - tempDistance)); //==understand==//
        return tempDistance; //kewl
    }    
    
    /**
     * Outputs the value from the PID controller, along with a value to keep the robot straight, to the Drivetrain.
     * @param speed speed from PID controller
     * @see PIDController
     * @see PIDOutput
     */
    public void pidWrite(double speed) {
      
        Drivetrain.drive(speed, straighteningK);//ok, so i'll find that website
    }
        //==yeah, so pidGet and pidWrite, where are they called? Oh, so you modified the code inthere? no all the modifications are in this file. SO you just changed pidWrite, though it was already a commandyes
    /**
     * Parameters that control the PID controller.
     */
    private double P = .05, I = 0,  D = 0, straighteningK = 0.000007, distance;
    
    /**                                             //== so, what do these values do, and how does the pid actually do what it does?==//
     * PID controller. 
     */
    private final PIDController pid = new PIDController(P, I, D, this, this);
    //yup, I get t
    /**
     * Called once when the command is scheduled to run.
     * Resets and starts the encoders and PID controller.
     */
    protected void initialize() {
        Drivetrain.resetEncoders();
        Drivetrain.startEncoders();  //==yup==//
        pid.reset();
        pid.setSetpoint(distance);
        pid.enable();  //==Okay, but when is this whole drivestraightcommand called, and what if anything in here starts the drivetrain going and how does the pid modify the behavior of drivetrain?
    }                       //about encoders, how are the counts translated into rotations? 
    
    /**
     * Called repeatedly when this Command is scheduled to run.
     * Doesn't do anything, the PIDTask loop takes care of the periodic operations.
     */ 
    protected void execute(){ 
    }

    /**
     * Returns true once the PID controller is on target. 
     * The command is stopped when this becomes true.
     * @return whether or not this is done
     * @see PIDController#onTarget()
     */ 
    protected boolean isFinished() {
        return (Math.abs(distance - pidGet()) < 0.1)||isTimedOut(); 
    }

    /**
     * Called once when the command finishes. 
     * Stops the PID Controller.
     */ 
    protected void end() {
        pid.disable();
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run. Calls {@link #end()}.
     */ 
    protected void interrupted() {
        System.err.println("DO NOT INTERRUPT DRIVETRAIN STRAIGHT PID!");
        end();
    }
    
    
}


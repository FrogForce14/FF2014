/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.verticalClaw;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author skodali
 */
public class ClawSolenoid extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    private final static ClawSolenoid instance = new ClawSolenoid();
    
    private ClawSolenoid(){}
    
    //make a double solenoid
    static DoubleSolenoid claw = new DoubleSolenoid(RobotMap.VERTICAL_CLAW_SOLENOID_A, RobotMap.VERTICAL_CLAW_SOLENOID_B);
    
    //sets openPosition of the Solenoid, can be changed at this one point, and everything else will depend on it
    static Value OPEN_POSITION = DoubleSolenoid.Value.kForward;
    //This sets closed position to opposite of openPosition
    static Value CLOSED_POSITION = (OPEN_POSITION == DoubleSolenoid.Value.kForward) ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward;
    //open method, to set claw to open position
    public static void openClaw(){
        claw.set(OPEN_POSITION);
    }
    // closed method, to set claw closed
    public static void closeClaw(){
        claw.set(CLOSED_POSITION);
    }
    //method to possibly change the position of the solenoid to opposite of what it is
    public static void changeClawPosition(){
        if(claw.get() == OPEN_POSITION){
            closeClaw();
        }else if(claw.get() == CLOSED_POSITION){
        openClaw();
    }
    }
    public static ClawSolenoid getInstance(){
        return instance;
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

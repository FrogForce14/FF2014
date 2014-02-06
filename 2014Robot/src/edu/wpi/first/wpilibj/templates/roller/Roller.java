/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.roller;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author skodali
 */
public class Roller extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    private static Roller instance = new Roller();
    
    private Roller(){}
    
    public final static Value PULLBACK_SPEED = Relay.Value.kForward;
    
    public final static Relay.Value PUSHFORWARD_SPEED = (PULLBACK_SPEED == Relay.Value.kForward) ? Relay.Value.kReverse : Relay.Value.kForward;
    
    
    private static Relay roller = new Relay(RobotMap.ROLLER_RELAY);
    
    //this just changes whatever the roller is at. If it's off, it'll turn it on, vice versa. 
    //may need to make it so that there is another one for 
    public static void setOut(){
            roller.set(PULLBACK_SPEED);
    }
    public static void setIn(){
            roller.set(PUSHFORWARD_SPEED);
    } 
   
   public static void setOff(){
            roller.set(Relay.Value.kOff);
    }
   
   public static void set(Value value){
            roller.set(value);
   }
   
    public static Roller getInstance(){
        return instance;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.winch;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author skodali
 */
public class Winch extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    private static final Winch instance= new Winch();
    
    private Winch(){}
    
    private static Talon winchMotor = new Talon(RobotMap.WINCH_MOTOR);
    static DigitalInput limitSwitch = new DigitalInput(RobotMap.WINCH_LIMIT_SWITCH);
    
    private static final int PULLBACK_SPEED = 1; //change  to -1If needed
    
    private static final Runnable pullbackRunnable = new Runnable(){
        private static final int DELAY = 10;
        public void run(){
            isPullingBack = true;
            moveBack();
            while(isBack() == false){
                //I think i remember reading that sleep is bad o
            }
            stop();
            isPullingBack = false;
            return;
        }
        
    };
    
    private static Thread pullbackThread;
    
    private volatile static boolean isPullingBack = false;
    
    public static boolean isPullingBack(){
        return isPullingBack;
    }
    
    public static void pullBack(){
        isPullingBack = true;
        pullbackThread = new Thread(pullbackRunnable);
        pullbackThread.setPriority(Thread.NORM_PRIORITY + 1);
        pullbackThread.start();
    }
    
    private static void moveBack(){
        winchMotor.set(PULLBACK_SPEED);
    }
    
    private static void stop(){
        winchMotor.set(0);
    }
    
    public static boolean isBack(){
        return limitSwitch.get();
    }
    
    public static Winch getInstance(){
        return instance;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

package edu.wpi.first.wpilibj.templates.winch;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.limitSwitches.InvertedLimitSwitch;

/**
 *
 * @author skodal
 */
public class Winch extends Subsystem {
    
    private static final Winch instance = new Winch();
    
    private Winch(){}
    
    private static final Talon winchMotor = new Talon(RobotMap.WINCH_MOTOR);
    private static final InvertedLimitSwitch limitSwitch = new InvertedLimitSwitch(RobotMap.WINCH_LIMIT_SWITCH);
    
    private static final Solenoid safety = new Solenoid(RobotMap.WINCH_SAFETY_SOLENOID);//ADD TO ROBOT MAP!!!
    private static final Solenoid winchSolenoid = new Solenoid(RobotMap.WINCH_SOLENOID);//^^^^^^^^^^^^
    private static boolean isSafetyEngaged = true;
    
    private static final int PULLBACK_SPEED = 1; //change  to -1If needed
    private static final Runnable pullbackRunnable = new Runnable(){ 
        public void run(){
            System.out.println("Runnable started");
            System.out.println(limitSwitch.get());
            if(limitSwitch.get()){
                try {
                    isPullingBack = true;
                    limitSwitch.requestInterrupts(false, true); //TEST!
                    System.out.println("Interrupt requested");
                    moveBack();
                    limitSwitch.waitForInterrupt(10);
                } finally {
                    stop();
                    limitSwitch.cancelInterrupts();
                    isPullingBack = false;
                }
            }else{
                isPullingBack = false;
            }
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
        //pullbackThread.setPriority(Thread.NORM_PRIORITY + 1);
        System.out.println("Thread created");
        pullbackThread.start();
    }
    
    public static boolean isPressed(){
        return limitSwitch.get();
    }
    
    private static void moveBack(){
        System.out.println("Winch Pulled Back");
        winchMotor.set(PULLBACK_SPEED);
    }
    
    private static void stop(){
        winchMotor.set(0);
        System.out.println("Winch Stopped");
    }
    
    public static void disengageSafety(){
        safety.set(true);
        isSafetyEngaged = false;
    }
    
    public static void engageSafety(){
        safety.set(false);
        isSafetyEngaged = true;
    }
    
    public static void engageWinch(){
        winchSolenoid.set(false);
    }
    
    public static boolean isSafetyEngaged(){
        return isSafetyEngaged;
    }
    
    
    public static void disengageWinch(){
        if(isSafetyEngaged()){
            System.out.print("Can't disengage winch with safety engaged!");
            engageWinch();
            return;
        }
        System.out.println("disengage winch");
        winchSolenoid.set(true);
    }
    
    public static Winch getInstance(){
        return instance;
    }

    public void initDefaultCommand() {
    }
}

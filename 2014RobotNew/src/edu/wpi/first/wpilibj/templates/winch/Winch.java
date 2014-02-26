package edu.wpi.first.wpilibj.templates.winch;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author skodal
 */
public class Winch extends Subsystem {
    
    
    private Winch(){
        winchMotor.set(0);
        winchMotor.setSafetyEnabled(false);
        System.out.println("winch created");
    }
    
    private static final Talon winchMotor = new Talon(RobotMap.WINCH_MOTOR);
    private static final DigitalInput limitSwitch = new DigitalInput(RobotMap.WINCH_LIMIT_SWITCH);
    
    private static final Solenoid safety = new Solenoid(RobotMap.WINCH_SAFETY_SOLENOID);//ADD TO ROBOT MAP!!!
    private static final Solenoid winchSolenoid = new Solenoid(RobotMap.WINCH_SOLENOID);//^^^^^^^^^^^^
    private static boolean isSafetyEngaged = true;
    
    private static final int PULLBACK_SPEED = 1; //change  to -1If needed
    
//    private static final Runnable pullbackRunnable = new Runnable(){ 
//        public void run(){
//            System.out.println("Runnable started");
//            System.out.println(limitSwitch.get());
//            if(limitSwitch.get()){
//                try {
//                    isPullingBack = true;
//                    //limitSwitch.requestInterrupts(false, true); //TEST!
//                    System.out.println("Interrupt requested");
//                    moveBack();
//                    limitSwitch.waitForInterrupt(10);
//                } finally {
//                    stop();
//                    limitSwitch.cancelInterrupts();
//                    isPullingBack = false;
//                }
//            }else{
//                isPullingBack = false;
//            }
//        }
//    };
    private static final Runnable pullbackRunnableNoIRQYield = new Runnable(){ 
        public void run(){
            if(!limitSwitch.get()){
                try {
                    isPullingBack = true;
                    //limitSwitch.requestInterrupts(false, true); //TEST!
                    //System.out.println("Interrupt requested");
                    moveBack();
                    while(!limitSwitch.get()){
                        Thread.yield();
                    }
                    //limitSwitch.waitForInterrupt(10);
                } finally {
                    stop();
                    //limitSwitch.cancelInterreupts();
                    isPullingBack = false;
                }
            }else{
                isPullingBack = false;
            }
            
        }
    };
    private static final Runnable pullbackRunnableNoIRQSleep = new Runnable(){ 
        public void run(){
            if(limitSwitch.get()){
                try {
                    isPullingBack = true;
                    //limitSwitch.requestInterrupts(false, true); //TEST!
                    //System.out.println("Interrupt requested");
                    moveBack();
                    while(!limitSwitch.get()){
                        Thread.sleep(10);
                    }
                    //limitSwitch.waitForInterrupt(10);
                } catch (InterruptedException ex) {
                    System.out.println("Pullback thread interupted?");
                } finally {
                    stop();
                    //limitSwitch.cancelInterrupts();
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
        pullbackThread = new Thread(pullbackRunnableNoIRQYield);
        //pullbackThread.setPriority(Thread.NORM_PRIORITY + 1);
        pullbackThread.start();
        //isPullingBack = false;
    }
    
    public static boolean isPressed(){
        return limitSwitch.get();
    }
    
    public static void moveBack(){
        winchMotor.set(PULLBACK_SPEED);
        
    }
    
    public static void stop(){
        winchMotor.set(0);
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
            engageWinch();
            return;
        }
        winchSolenoid.set(true);
    }
    
    public static Winch getInstance(){
        return instance;
    }

    public void initDefaultCommand() {
    }
    private static final Winch instance = new Winch();
}

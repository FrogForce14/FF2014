package edu.wpi.first.wpilibj.templates.claw;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author skodali
 */
public class Claw extends Subsystem {
    
    public static class Position{
        //sets openPosition of the Solenoid, can be changed at this one point, and everything else will depend on it
        public final static Value OPEN_SOLENOID_COMMAND = DoubleSolenoid.Value.kReverse;
        //This sets closed position to opposite of openPosition
        public final static Value CLOSE_SOLENOID_COMMAND = (OPEN_SOLENOID_COMMAND == DoubleSolenoid.Value.kForward) ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward;
        
        public final static int OPEN_VAL = 1;
        public final static int CLOSE_VAL = 0;
        
        public final int value;
        public final DoubleSolenoid.Value solenoidCommand;
        
        private Position(int value, DoubleSolenoid.Value solenoidCommand){
            this.value = value;
            this.solenoidCommand = solenoidCommand;
        }
        
        public final static Position OPEN = new Position(OPEN_VAL, OPEN_SOLENOID_COMMAND);
        public final static Position CLOSE = new Position(CLOSE_VAL, CLOSE_SOLENOID_COMMAND);
        
    }
    
    private final static Claw instance = new Claw();
    
    private Claw(){}
    
    //make a double solenoid
    private final static DoubleSolenoid verticalClaw = new DoubleSolenoid(RobotMap.VERTICAL_CLAW_SOLENOID_A, RobotMap.VERTICAL_CLAW_SOLENOID_B);
    private final static DoubleSolenoid horizontalClaw = new DoubleSolenoid(RobotMap.HORIZONTAL_CLAW_SOLENOID_A, RobotMap.HORIZONTAL_CLAW_SOLENOID_B);
    
    //method to possibly change the position of the solenoid to opposite of what it is NEVER DO THIS EVER!!!Q!!!!!!
//    public static void changeClawPosition(){
//        if(claw.get() == OPEN_POSITION){
//            closeClaw();
//        }else if(claw.get() == CLOSED_POSITION){
//        openClaw();
//    }
//    }
    
    public static void verticalClawSet(Position pos){
        verticalClaw.set(pos.solenoidCommand);
    }
    
    public static void horizontalClawSet(Position pos){
        horizontalClaw.set(pos.solenoidCommand);
    }
    
    public static Claw getInstance(){
        return instance;
    }
    public void initDefaultCommand() {
    }
}

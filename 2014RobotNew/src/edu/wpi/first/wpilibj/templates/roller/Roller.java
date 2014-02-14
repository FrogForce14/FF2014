package edu.wpi.first.wpilibj.templates.roller;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author skodal
 */
public class Roller extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public static class Direction{
        public final static int IN_VAL = 1;
        public final static Relay.Value IN_RELAY_COMMAND = Relay.Value.kForward;
        public final static int OUT_VAL = 2;
        public final static Relay.Value OUT_RELAY_COMMAND = Relay.Value.kReverse;
        public final static int OFF_VAL = 3;
        public final static Relay.Value OFF_RELAY_COMMAND = Relay.Value.kOff;
        
        public final int val;
        public final Relay.Value relayCommand;
        
        private Direction(int val, Relay.Value relayCommand){
            this.val = val;
            this.relayCommand = relayCommand;
        }
        
        public final static Direction IN = new Direction(IN_VAL, IN_RELAY_COMMAND);
        public final static Direction OUT = new Direction(OUT_VAL, OUT_RELAY_COMMAND);
        public final static Direction OFF = new Direction(OFF_VAL, OFF_RELAY_COMMAND);
    }
    
    private static final Roller instance = new Roller();
    
    private Roller(){}
    
    private static final Relay roller = new Relay(RobotMap.ROLLER_RELAY);
    
    //this just changes whatever the roller is at. If it's off, it'll turn it on, vice versa.
   public static void set(Direction direction){
            roller.set(direction.relayCommand);
   }
   
    public static Roller getInstance(){
        return instance;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        
    }
    
}


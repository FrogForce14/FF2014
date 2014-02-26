package edu.wpi.first.wpilibj.templates.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Vector;

/**
 *
 * @author Bryce
 */
public class Mode{
    
    public static class AutonMode extends Mode{
        public final SendableChooser chooser = new SendableChooser();
        private final String name;
        public AutonMode(int val, String name) {
            super(val);
            this.name = name;
        }
        
        public void addChoice(Command command, boolean isDefault){
            if(isDefault){
                chooser.addDefault(command.getName(), command);
            } else {
                chooser.addObject(command.getName(), command);                
            }
            SmartDashboard.putData(name, chooser);
        }
        
        public void addChoice(Command command, boolean isDefault, String nameOverride){
            if(isDefault){
                chooser.addDefault(nameOverride, command);
            } else {
                chooser.addObject(nameOverride, command);                
            }
            SmartDashboard.putData(name, chooser);
        }
        
        public void startAllCommands(){
            addCommand((Command) chooser.getSelected());
            super.startAllCommands();            
        }
    }
    
    public final static int PRE_MATCH_VAL = 0;
    public final static int AUTON_PRE_SHOT_VAL = 1;
    public final static int AUTON_POST_SHOT_VAL = 2;
    public final static int TELEOP_VAL = 3;
    public final static int POST_MATCH_VAL = 4;

    public final int value;
    private final Vector commands = new Vector(2);

    private Mode(int val){
        value = val;
    }

    public final static Mode PRE_MATCH = new Mode(PRE_MATCH_VAL);
    public final static AutonMode AUTON_PRE_SHOT = new AutonMode(AUTON_PRE_SHOT_VAL, "Autonomous Shooting");
    public final static AutonMode AUTON_POST_SHOT = new AutonMode(AUTON_POST_SHOT_VAL, "Autonomous Driving");
    public final static Mode TELEOP = new Mode(TELEOP_VAL);
    public final static Mode POST_MATCH = new Mode(POST_MATCH_VAL);
    private static Mode currentMode = PRE_MATCH;

    public static Mode getMode(){
        return currentMode;
    }

    private static void setMode(Mode mode){
        currentMode = mode;
    }

    public void addCommand(Command command){
        commands.addElement(command);
        if(currentMode == this){
            command.start();
        }
    }

    public void cancelAllCommands(){
        for(int i = 0; i < commands.size(); i++){
            ((Command) commands.elementAt(i)).cancel();
        }
    }

    public void startAllCommands(){
        for(int i = 0; i < commands.size(); i++){
            ((Command) commands.elementAt(i)).start();
        }
    }

    public static void changeMode(Mode mode){
        currentMode.cancelAllCommands();
        setMode(mode);
        currentMode.startAllCommands();
    }
}

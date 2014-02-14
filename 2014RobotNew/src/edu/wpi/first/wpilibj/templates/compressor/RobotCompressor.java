/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.compressor;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author skodali
 */
public class RobotCompressor extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    private static final RobotCompressor instance = new RobotCompressor();
    
    private RobotCompressor(){}
    
    static Compressor compressor = new Compressor(RobotMap.PRESSURE_SWITCH_CHANNEL,RobotMap.COMPRESSOR_RELAY_CHANNEL);
    
    public static void start(){
        compressor.start();
    }
    
    public static RobotCompressor getInstance(){
        return instance;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

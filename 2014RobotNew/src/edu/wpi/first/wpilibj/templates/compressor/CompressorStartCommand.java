/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.compressor;

import edu.wpi.first.wpilibj.templates.InstantaneousCommand;

/**
 *
 * @author skodali
 */
public class CompressorStartCommand extends InstantaneousCommand {
    
    protected void action(){
        RobotCompressor.start();
    }
}

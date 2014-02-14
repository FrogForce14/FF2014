package edu.wpi.first.wpilibj.templates.winch;

import edu.wpi.first.wpilibj.templates.InstantaneousCommand;

/**
 *
 * @author Bryce
 */
public class DisengageWinchCommand extends InstantaneousCommand {
    protected void action(){
        Winch.disengageWinch();
    }
}
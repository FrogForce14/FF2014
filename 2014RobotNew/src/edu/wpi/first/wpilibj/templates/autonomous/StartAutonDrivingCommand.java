package edu.wpi.first.wpilibj.templates.autonomous;

import edu.wpi.first.wpilibj.templates.InstantaneousCommand;

/**
 *
 * @author Bryce
 */
public class StartAutonDrivingCommand extends InstantaneousCommand{
    public void action(){
        Mode.changeMode(Mode.AUTON_POST_SHOT);
    }
}

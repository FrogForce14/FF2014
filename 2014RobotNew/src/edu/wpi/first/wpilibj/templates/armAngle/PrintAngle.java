package edu.wpi.first.wpilibj.templates.armAngle;

import edu.wpi.first.wpilibj.templates.InstantaneousCommand;

/**
 *
 * @author skodali
 */
public class PrintAngle extends InstantaneousCommand {
    double distance;
    protected void action() {
        distance = Angler.getDistance();
        System.out.println(distance + "ADD SHOT");
    }
}

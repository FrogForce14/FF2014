/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.armAngle;

import edu.wpi.first.wpilibj.templates.InstantaneousCommand;

/**
 *
 * @author skodali
 */
public class GetDistance extends InstantaneousCommand {
    double distance;
    protected void action() {
        distance = Angler.getDistance();
        System.out.println(distance);
    }
}

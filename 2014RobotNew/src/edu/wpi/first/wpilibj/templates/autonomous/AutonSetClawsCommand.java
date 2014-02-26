package edu.wpi.first.wpilibj.templates.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.InstantaneousCommand;
import edu.wpi.first.wpilibj.templates.claw.Claw;

/**
 *
 * @author Bryce
 */
public class AutonSetClawsCommand extends InstantaneousCommand{
    
    public static final SendableChooser horizontalChooser = new SendableChooser(), 
                                        verticalChooser = new SendableChooser();
    
    public AutonSetClawsCommand() {
        horizontalChooser.addDefault("HorizontalClawOpen", Claw.Position.OPEN);
        horizontalChooser.addDefault("HorizontalClawClosed", Claw.Position.CLOSED);
        verticalChooser.addDefault("VeritcalClawOpen", Claw.Position.OPEN);
        verticalChooser.addDefault("VerticalClawClosed", Claw.Position.CLOSED);
        SmartDashboard.putData("HorizontalClaw Teleop Position", horizontalChooser);
        SmartDashboard.putData("VerticalClaw Teleop Position", verticalChooser);
    }


    protected void action() {
        Claw.horizontalClawSet((Claw.Position) horizontalChooser.getSelected());
        Claw.verticalClawSet((Claw.Position) verticalChooser.getSelected());
    }
}

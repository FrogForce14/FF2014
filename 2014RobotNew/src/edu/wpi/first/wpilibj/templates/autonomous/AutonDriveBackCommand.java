package edu.wpi.first.wpilibj.templates.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.drivetrain.DriveStraightCommand;

/**
 *
 * @author Bryce
 */
public class AutonDriveBackCommand extends Command{
    
    public AutonDriveBackCommand() {
        super("Auton Drive Command");
        SmartDashboard.putNumber("AutonDriveDistance", 10);
    }

    private double distance;
    private Command drivetrainCommand;
    
    protected void initialize() {
        distance = SmartDashboard.getNumber("AutonDriveDistance", 0);
        drivetrainCommand = new DriveStraightCommand(distance);
        drivetrainCommand.start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return drivetrainCommand.isCanceled() || !drivetrainCommand.isRunning();
    }

    protected void end() {
        drivetrainCommand.cancel();
    }

    protected void interrupted() {
        end();
    }
}

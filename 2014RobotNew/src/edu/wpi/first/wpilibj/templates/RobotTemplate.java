/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.armAngle.Angler;
import edu.wpi.first.wpilibj.templates.armAngle.Angler.Angle;
import edu.wpi.first.wpilibj.templates.armAngle.CalibrateCommandGroup;
import edu.wpi.first.wpilibj.templates.armAngle.GoToAngleCommand;
import edu.wpi.first.wpilibj.templates.armAngle.ManualAnglerCommand;
import edu.wpi.first.wpilibj.templates.autonomous.OneBallCommandGroup;
import edu.wpi.first.wpilibj.templates.autonomous.TwoBallCommandGroup;
import edu.wpi.first.wpilibj.templates.camera.HotOrNot;
import edu.wpi.first.wpilibj.templates.claw.Claw;
import edu.wpi.first.wpilibj.templates.compressor.CompressorStartCommand;
import edu.wpi.first.wpilibj.templates.compressor.RobotCompressor;
import edu.wpi.first.wpilibj.templates.drivetrain.Drivetrain;
import edu.wpi.first.wpilibj.templates.drivetrain.ShifterHighGearCommand;
import edu.wpi.first.wpilibj.templates.drivetrain.TeleopDriveCommand;
import edu.wpi.first.wpilibj.templates.roller.Roller;
import edu.wpi.first.wpilibj.templates.winch.Winch;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

    Command calibrateCommand = new CalibrateCommandGroup(); 
    Command lowAngle = new GoToAngleCommand(Angle.LOW_SHOT);
    Command autonomousCommand1 = new OneBallCommandGroup(); 
    Command autonomousCommand2 = new TwoBallCommandGroup();
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        // Initialize all subsystems
        OI.init();
        SmartDashboard.putData("Winch", Winch.getInstance());
        (new CompressorStartCommand()).start();
        
        SmartDashboard.putData("Claw", Claw.getInstance());
        SmartDashboard.putData("Angler", Angler.getInstance());
        SmartDashboard.putData("Drivetrain", Drivetrain.getInstance());
        SmartDashboard.putData("Roller", Roller.getInstance());
        SmartDashboard.putData("Compressor", RobotCompressor.getInstance());
        
    }

    public void autonomousInit() {
        // schedule the autonomous command (example) 
        //calibrate.start();
        (new ShifterHighGearCommand()).start();
        (new HotOrNot()).start();
        //testing, delete when done
        //calibrateCommand.start();
        //w
        //autonomousCommand1.start();
        //lowAngle.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //autonomousCommand1.cancel();
        //do compressor like below
        (new TeleopDriveCommand()).start();
        (new ManualAnglerCommand()).start();
        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Angler Distance:", Angler.getDistance());
        SmartDashboard.putBoolean("Is calibrate Pressed", Angler.isCalibrateLimitSwitchPressed());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}


package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.armAngle.Angler.Angle;
import edu.wpi.first.wpilibj.templates.armAngle.CalibrateCommandGroup;
import edu.wpi.first.wpilibj.templates.armAngle.GoToAngleCommand;
import edu.wpi.first.wpilibj.templates.drivetrain.ShifterHighGearCommand;
import edu.wpi.first.wpilibj.templates.drivetrain.ShifterLowGearCommand;
import edu.wpi.first.wpilibj.templates.roller.Roller.Direction;
import edu.wpi.first.wpilibj.templates.roller.SetRollerCommand;
import edu.wpi.first.wpilibj.templates.claw.SetVerticalClawCommand;
import edu.wpi.first.wpilibj.templates.claw.Claw.Position;
import edu.wpi.first.wpilibj.templates.claw.SetHorizontalClawCommand;
import edu.wpi.first.wpilibj.templates.winch.FireCommandGroup;
import edu.wpi.first.wpilibj.templates.winch.PullBackWinchWithPneumaticCommandGroup;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    private static final int LEFT_X_AXIS = 6,
                            RIGHT_Y_AXIS = 5,
                            TRIGGER = 3;
    
    private static final Joystick leftJoystick = new Joystick(1),
            rightJoystick = new Joystick(2),
            operatorJoystick = new Joystick(3);
    
    private static final TriggerButton rightTrigger = new TriggerButton(operatorJoystick, TRIGGER, true),
                                            leftTrigger = new TriggerButton(operatorJoystick, TRIGGER, false);
    
    
    private static final JoystickButton 
                                        highShotButton = new JoystickButton(operatorJoystick, 4),
                                        lowShotButton = new JoystickButton(operatorJoystick, 2),
                                        pickupButton = new JoystickButton(operatorJoystick, 1),
                                        //7
                                        //rollerOff = new JoystickButton(operatorJoystick, 3),
                                        verticalClawOpen = new JoystickButton(operatorJoystick, 6),
                                        verticalClawClose = new JoystickButton(operatorJoystick, 5),
                                        horizontalClawOpen = new JoystickButton(operatorJoystick, 9),
                                        horizontalClawClose = new JoystickButton(operatorJoystick, 10),
                                        calibrate = new JoystickButton(operatorJoystick, 3),
    
                                        shiftHigh = new JoystickButton(rightJoystick, 6),
                                        
                                        shiftLow = new JoystickButton(rightJoystick, 4);
    
    public static final JoystickButton rollerIn = new JoystickButton(operatorJoystick, 8),
                                        rollerOut = new JoystickButton(operatorJoystick, 7);
     
    public static final double ANGLE_SENSITIVITY = .5,
                                DRIVE_SENSITIVITY = .5;
    
    public static double scale(double input, double sensitivity){
        return sensitivity * (input*input*input) + (1-sensitivity) * input;
    }
    
    public static double getLeftInput(){
        double input = leftJoystick.getY(), output = scale(input, DRIVE_SENSITIVITY);
        return output;
       
    }
    
    public static double getRightInput(){
        double input = rightJoystick.getY(), output = scale(input, DRIVE_SENSITIVITY);
        return input;
    }
    
    
    public static double getAngleInput(){
        double output = -scale(operatorJoystick.getRawAxis(RIGHT_Y_AXIS), ANGLE_SENSITIVITY)*0.3;
        return output;
    }
    
    
    public static void init(){
        //straightUpButton.whileHeld(new GoToAngleCommand(Angle.STRAIGHT_UP));
        highShotButton.whileHeld(new GoToAngleCommand(Angle.HIGH_SHOT));
        pickupButton.whileHeld(new GoToAngleCommand(Angle.PICKUP));  
        lowShotButton.whileHeld(new GoToAngleCommand(Angle.LOW_SHOT));
        
        //WinchFire.whenPressed(new FireCommandGroup());
        //pullBack.whileHeld(new PullBackWinchCommand());
        
        rollerIn.whenPressed(new SetRollerCommand(Direction.IN));
        rollerOut.whenPressed(new SetRollerCommand(Direction.OUT));
        
        verticalClawOpen.whenPressed(new SetVerticalClawCommand(Position.OPEN));
        verticalClawClose.whenPressed(new SetVerticalClawCommand(Position.CLOSE));
        
        horizontalClawOpen.whenPressed(new SetHorizontalClawCommand(Position.OPEN));
        horizontalClawClose.whenPressed(new SetHorizontalClawCommand(Position.CLOSE));
        
        shiftHigh.whenPressed(new ShifterHighGearCommand());
        shiftLow.whenPressed(new ShifterLowGearCommand());
        
        
        leftTrigger.whenPressed(new PullBackWinchWithPneumaticCommandGroup());
        rightTrigger.whenPressed(new FireCommandGroup());
        
        calibrate.whenPressed(new CalibrateCommandGroup());
        
        
    }
}


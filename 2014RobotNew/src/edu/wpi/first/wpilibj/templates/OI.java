
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
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
//1: A
//2: B
//3: X
//4: Y
//5: Left Bumper
//6: Right Bumper
//7: Back
//8: Start
//9: Left Joystick
//10: Right Joystick
//
//The axis on the controller follow this mapping
//(all output is between -1 and 1)
//1: Left Stick X Axis
//-Left:Negative ; Right: Positive
//2: Left Stick Y Axis
//-Up: Negative ; Down: Positive
//3: Triggers
//-Left: Positive ; Right: Negative
//4: Right Stick X Axis
//-Left: Negative ; Right: Positive
//5: Right Stick Y Axis
//-Up: Negative ; Down: Positive
    private static final int A = 1,
                            B = 2,
                            X = 3,
                            Y = 4,
                            LB = 5,
                            RB = 6,
                            BACK = 7,
                            START = 8,
                            LJOY = 9,
                            RJOY = 10,
                            LEFT_X = 1,
                            LEFT_Y = 2,
                            TRIGGERS = 3,
                            RIGHT_X = 4,
                            RIGHT_Y = 5;
    
    public static final Joystick leftJoystick = new Joystick(1),
                                    rightJoystick = new Joystick(2),
                                    operatorJoystick = new Joystick(3);
    
    public static final TriggerButton leftTrigger = new TriggerButton(operatorJoystick, TRIGGERS, Hand.kLeft),
                                            rightTrigger = new TriggerButton(operatorJoystick, TRIGGERS, Hand.kRight);
    
    
    public static final JoystickButton pickupButton = new JoystickButton(operatorJoystick, A),
                                        lowShotButton = new JoystickButton(operatorJoystick, B),
                                        calibrate = new JoystickButton(operatorJoystick, X),
                                        highShotButton = new JoystickButton(operatorJoystick, Y),
                                        verticalClawClose = new JoystickButton(operatorJoystick, LB),
                                        verticalClawOpen = new JoystickButton(operatorJoystick, RB),
                                        rollerOut = new JoystickButton(operatorJoystick, BACK),
                                        rollerIn = new JoystickButton(operatorJoystick, START),
                                        horizontalClawOpen = new JoystickButton(operatorJoystick, LJOY),
                                        horizontalClawClose = new JoystickButton(operatorJoystick, RJOY),
    
                                        shiftHigh = new JoystickButton(rightJoystick, 6),                                        
                                        shiftLow = new JoystickButton(rightJoystick, 4);
    
     
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
        return output;
    }
    
    
    public static double getAngleInput(){
        double output = -scale(operatorJoystick.getRawAxis(RIGHT_Y), ANGLE_SENSITIVITY)*0.667;
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
        verticalClawClose.whenPressed(new SetVerticalClawCommand(Position.CLOSED));
        
        horizontalClawOpen.whenPressed(new SetHorizontalClawCommand(Position.OPEN));
        horizontalClawClose.whenPressed(new SetHorizontalClawCommand(Position.CLOSED));
        
        shiftHigh.whenPressed(new ShifterHighGearCommand());
        shiftLow.whenPressed(new ShifterLowGearCommand());
        
        
        rightTrigger.whenPressed(new PullBackWinchWithPneumaticCommandGroup());
        leftTrigger.whenPressed(new FireCommandGroup());
        
        calibrate.whenPressed(new CalibrateCommandGroup());
        
        
    }
}


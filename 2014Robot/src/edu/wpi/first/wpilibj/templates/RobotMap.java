package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    public static final int LEFT_FRONT_MOTOR = 1;
    public static final int LEFT_BACK_MOTOR = 2;
    public static final int RIGHT_FRONT_MOTOR = 3;
    public static final int RIGHT_BACK_MOTOR = 4;
    public static final int 
                            WINCH_LIMIT_SWITCH = 1,
                            WINCH_MOTOR = 6,
                            DRIVETRAIN_LEFT_ENCODER_CHANNEL_A = 1,
                            DRIVETRAIN_LEFT_ENCODER_CHANNEL_B = 2,
                            DRIVETRAIN_RIGHT_ENCODER_CHANNEL_A = 3,
                            DRIVETRAIN_RIGHT_ENCODER_CHANNEL_B = 4,
                            VERTICAL_CLAW_SOLENOID_A = 3,
                            VERTICAL_CLAW_SOLENOID_B = 4,
                            ROLLER_RELAY = 2;
                            
}

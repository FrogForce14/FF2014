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
    public static final int LEFT_FRONT_MOTOR = 7,
                            LEFT_BACK_MOTOR = 4,
                            RIGHT_FRONT_MOTOR = 1,
                            RIGHT_BACK_MOTOR = 2, 
                            WINCH_SOLENOID = 5,
                            WINCH_SAFETY_SOLENOID = 6,
                            WINCH_LIMIT_SWITCH = 1,
                            ARM_ANGLE_MOTOR = 8,
                            WINCH_MOTOR = 6,
                            DRIVETRAIN_LEFT_ENCODER_CHANNEL_A = 4,
                            DRIVETRAIN_LEFT_ENCODER_CHANNEL_B = 5,
                            DRIVETRAIN_RIGHT_ENCODER_CHANNEL_A = 6,
                            DRIVETRAIN_RIGHT_ENCODER_CHANNEL_B = 7,
                            SHIFTER_SOLENOID_A = 1,
                            SHIFTER_SOLENOID_B = 2,
                            ARM_ANGLE_ENCODER_CHANNEL_A = 8,
                            ARM_ANGLE_ENCODER_CHANNEL_B = 9,
                            SHOOTER_ANGLER_UPPER_LIMIT_SWITCH_CHANNEL = 2,
                            SHOOTER_ANGLER_LOWER_LIMIT_SWITCH_CHANNEL = 3,
                            SHOOTER_ANGLER_CALIBRATE_LIMIT_SWITCH_CHANNEL = 11,
                            COMPRESSOR_RELAY_CHANNEL = 2,
                            PRESSURE_SWITCH_CHANNEL = 10,
                            VERTICAL_CLAW_SOLENOID_A = 3,
                            VERTICAL_CLAW_SOLENOID_B = 4,
                            HORIZONTAL_CLAW_SOLENOID_A = 7,
                            HORIZONTAL_CLAW_SOLENOID_B = 8,
                            ROLLER_RELAY = 3;
                            
}

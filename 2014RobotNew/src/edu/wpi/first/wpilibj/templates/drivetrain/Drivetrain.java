/*
 * 
 * 
 */
package edu.wpi.first.wpilibj.templates.drivetrain;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author skodali
 *///
public class Drivetrain extends Subsystem {
    
    private static Drivetrain instance;// = new Drivetrain();
    
    
    private final static RobotDrive drive = new RobotDrive(RobotMap.LEFT_FRONT_MOTOR, 
            RobotMap.LEFT_BACK_MOTOR, RobotMap.RIGHT_FRONT_MOTOR, RobotMap.RIGHT_BACK_MOTOR);
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    private static final Encoder leftEncoder, rightEncoder;
    private static final DoubleSolenoid shifter;
    
    private Drivetrain(){} //that way you can't make another drivetrain on accident
    
    
    static {
        leftEncoder = new Encoder(RobotMap.DRIVETRAIN_LEFT_ENCODER_CHANNEL_A,
                                    RobotMap.DRIVETRAIN_LEFT_ENCODER_CHANNEL_B, true,
                                    Encoder.EncodingType.k4X);
        rightEncoder = new Encoder(RobotMap.DRIVETRAIN_RIGHT_ENCODER_CHANNEL_A,
                                    RobotMap.DRIVETRAIN_RIGHT_ENCODER_CHANNEL_B, false,
                                    Encoder.EncodingType.k4X);
        leftEncoder.setDistancePerPulse(1d/250d);
        leftEncoder.setMaxPeriod(1);
        rightEncoder.setDistancePerPulse(1d/250d);//make sure you update these.
        rightEncoder.setMaxPeriod(1); 
        shifter = new DoubleSolenoid(RobotMap.SHIFTER_SOLENOID_A, RobotMap.SHIFTER_SOLENOID_B);
        startEncoders();
        //LiveWindow.addActuator("Drivetrain", "Left Motors", leftMotors);
        //LiveWindow.addActuator("Drivetrain", "Right Motors", rightMotors);
//        LiveWindow.addSensor("Drivetrain", "Left Encoder", leftEncoder);
//        LiveWindow.addSensor("Drivetrain", "Right Encoder", rightEncoder); 
    }
    
    
        
    public static void teleopDrive(){
        
        double leftInput = OI.getLeftInput(), rightInput = OI.getRightInput();
        
        if(Math.abs(OI.getLeftInput()) < 0.1){
            leftInput = 0;
        }
        if(Math.abs(OI.getRightInput()) < 0.1){
            rightInput = 0;
        }
        
        setMotors(leftInput, rightInput);
        
       
    }
    
    
    
    public static void setMotors(double speedLeft, double speedRight){
            drive.tankDrive(speedLeft, speedRight, false);
    }
    
    public static void drive(double speed, double curve){
        drive.drive(speed, curve);
    }
    
    public static Drivetrain getInstance(){
        if(instance == null){
            instance = new Drivetrain();
        }
        return instance;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new TeleopDriveCommand());
    }
     //
     //Gets the distance the left encoder has rotated.
     //@return the distance rotated, in rotations, positive when the robot is moving forward
     //
    //PUT NEGATIVES IF DOESN'T WORK 
    public static double getLeftDistance(){
        return leftEncoder.getDistance();
    }
    
    /**
     * Gets the distance the right encoder has rotated.
     * @return the distance rotated, in rotations, positive when the robot is moving forward
     */
    public static double getRightDistance(){
        return rightEncoder.getDistance();
    }
    
    /**
     * Gets the distance the robot has traveled.
     * @return 2x the distance traveled
     */
    public static double getDistance(){
        return getLeftDistance() + getRightDistance();
    }
    /**
     * Reset the encoders. 
     * Sets the accumulated distance to zero.
     */
    public static void resetEncoders(){
        leftEncoder.reset();
        rightEncoder.reset();
    }
    
    /**
     * Starts the encoders.
     * Starts the distance accumulation, they will both read 0.0 
     * if you forget to call this.
     */
    public static void startEncoders(){
        leftEncoder.start();
        rightEncoder.start();
    }
    
    public static void highGear(){
        shifter.set(DoubleSolenoid.Value.kReverse);
        
    }
    public static void lowGear(){
        shifter.set(DoubleSolenoid.Value.kForward);
    }
} 

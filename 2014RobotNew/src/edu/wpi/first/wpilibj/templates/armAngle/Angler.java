/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.armAngle;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.limitSwitches.InvertedLimitSwitch;

/**
 *
 * @author skodal
 */
public class Angler extends Subsystem implements PIDSource, PIDOutput{
    
    private static final Angler instance = new Angler();

    public static class Angle{
        public static final int PICKUP_VAL = 0;
        public static final double PICKUP_ANGLE = 2425;
        public static final int LOW_SHOT_VAL = 1;
        public static final double LOW_SHOT_ANGLE = 1310;
        public static final int HIGH_SHOT_VAL = 2;
        public static final double HIGH_SHOT_ANGLE = 990;
        public static final int STRAIGHT_UP_VAL = 3;
        public static final double STRAIGHT_UP_ANGLE = 0;
        
        public final int value;
        public final double angle;
        
        private Angle(int value, double angle){
            this.angle = angle;
            this.value = value;
        }
        
        public static final Angle PICKUP = new Angle(PICKUP_VAL, PICKUP_ANGLE);
        public static final Angle LOW_SHOT = new Angle(LOW_SHOT_VAL, LOW_SHOT_ANGLE);
        public static final Angle HIGH_SHOT = new Angle(HIGH_SHOT_VAL, HIGH_SHOT_ANGLE);
        public static final Angle STRAIGHT_UP = new Angle(STRAIGHT_UP_VAL, STRAIGHT_UP_ANGLE);//seen 
     }
    
    private Angler(){}
    private final static Talon anglerMotor = new Talon(RobotMap.ARM_ANGLE_MOTOR);
    private final static ArmEncoder encoder = new ArmEncoder(1,RobotMap.ARM_ANGLE_ENCODER_CHANNEL_A,1, RobotMap.ARM_ANGLE_ENCODER_CHANNEL_B,false, Encoder.EncodingType.k4X);
    private final static InvertedLimitSwitch lowerLimitSwitch = new InvertedLimitSwitch(RobotMap.SHOOTER_ANGLER_LOWER_LIMIT_SWITCH_CHANNEL);
    //private final static InvertedLimitSwitch upperLimitSwitch = new InvertedLimitSwitch(RobotMap.SHOOTER_ANGLER_UPPER_LIMIT_SWITCH_CHANNEL);
    private final static DigitalInput calibrateLimitSwitch = new DigitalInput(RobotMap.SHOOTER_ANGLER_CALIBRATE_LIMIT_SWITCH_CHANNEL);
    private final static double p = -0.03, i = -0.006, d = 0;//
    private final static PIDController controller = new PIDController(p, i, d, getInstance(), getInstance());
    
    private final static double ENCODER_CPR = 250;
    private final static double GEARBOX_RATIO = 1d/51d;
    private final static double CHAIN_RATIO = 1d/5d;//12:60
    private final static double MAX_STOPPED_RATE = 3.6; // 
    
    static {
        encoder.setDistancePerPulse(360/(ENCODER_CPR/*GEARBOX_RATIO*/*CHAIN_RATIO));
        encoder.setMaxPeriod(1/*seconds*/);    
        controller.setOutputRange(-0.3, 0.3);
        controller.setAbsoluteTolerance(20/*degrees, should be*/);//tuen this
        SmartDashboard.putData("ANGLER PID", controller);
    }
    
    public void pidWrite(double speed) {
        SmartDashboard.putNumber("PIDWRITE", speed);
        setSpeed(speed);
    }
    
    public static void setSpeed(double speed){
        SmartDashboard.putNumber("Angle speed", -speed);
        if((isLowerLimitSwitchPressed() && speed < 0)/*||(isUpperLimitSwitchPressed()  && speed > 0)*/){
            speed = 0;
        }
        
        speed = -speed;
        
        SmartDashboard.putNumber("Angle speed:", speed);
        if(speed > 0.1 || speed < 0.1){
            anglerMotor.set(speed); //pos speed is up.
        }else{
            stop();
        }
        
    }
    public double pidGet() {
        double tempDistance = getDistance(); 
        SmartDashboard.putNumber("Distance Error", (controller.getSetpoint() - tempDistance)); 
        SmartDashboard.putNumber("PID Distance", (tempDistance));
        return tempDistance; 
    }    
    
    
    public static boolean isLowerLimitSwitchPressed(){
        return lowerLimitSwitch.get();
    }
    public static boolean isUpperLimitSwitchPressed(){
        return false; //upperLimitSwitch.get();
    }
    public static boolean isCalibrateLimitSwitchPressed(){
        return calibrateLimitSwitch.get();
    }
    public static void setHigherLimit(){
        encoder.calibrate();
    }
    
    public static Angler getInstance(){
        return instance; 
    }
    
    public static void setSetpoint(double angle){
        controller.setSetpoint(angle);
    }
    
    public static double getDistance(){
        return encoder.getDistance();
    }
    
    public static boolean isStopped() {
        return encoder.getRate() < MAX_STOPPED_RATE;
    }
    
    
    public static void stop(){
        anglerMotor.set(0);
    }
    public static void manualAngler(){
        double operatorInput = OI.getAngleInput();
        setSpeed(operatorInput); 
    }
    public static boolean onTarget(){
        return controller.onTarget();
    }
    public static void pidEnable(){
        controller.enable();
    }
    public static void pidDisable(){
        controller.disable();
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new ManualAnglerCommand());
        
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

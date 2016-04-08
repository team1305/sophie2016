package org.usfirst.frc.team1305.robot.subsystems;


import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.LaunchHighGoal;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */

public class Launcher extends Subsystem {
	
	private boolean isLockedIn = false;
	//private double lockSpeed = 0;
	private double FLY_WHEEL_LOW_GOAL_SPEED = -0.5;
	private double mtrspeed;
	private Timer launchDelayTimer = new Timer();
	private Timer retractIntakeTimer = new Timer();
	private double LAUNCH_DELAY_SECONDS = 2.6;
	private double intakeForwards = 0.75; //forward intake speed
	private double intakeBackwards = -1;
	private double INTAKE_ROLLERS_CONTINUE_END_TIME = 0.2;
	private double INTAKE_ROLLERS_PAUSE_END_TIME = INTAKE_ROLLERS_CONTINUE_END_TIME + 0.5;
	private double INTAKE_ROLLERS_BACKUP_END_TIME = INTAKE_ROLLERS_PAUSE_END_TIME + 0.15;
	private boolean isAutoLaunch = false;
	private boolean isOnTarget = false;

	private int MAX_LAUNCHER_RPM = 6200;
	private double motorOutput; 
	
	private CANTalon Intake_Talon = new CANTalon(RobotMap.CAN_DEVICE_INTAKEROLLER);
	private CANTalon _fly_wheel_talon_right = new CANTalon(RobotMap.CAN_DEVICE_LAUNCHER_R);
	private CANTalon _fly_wheel_talon_left= new CANTalon(RobotMap.CAN_DEVICE_LAUNCHER_L);
	private Solenoid Intake_Slide = new Solenoid(RobotMap.CAN_SOLENOID, RobotMap.SOLENOID_CH_SLIDER);
	private DigitalInput ballSensor = new DigitalInput(RobotMap.DIO_BALLSENSOR);
	
	double targetSpeed ;
	double _throttleBeforeLockin;
	double _targetFlywheelPercent = 0.445;
	//Preferences prefs;
	//private String KEY_PREFS_TARGET_FLYWHEEL_PERCENT = "TargetFlywheelPercent";
	StringBuilder _sb = new StringBuilder();
	int _loops = 0;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Launcher()
	{
		/* first choose the sensor */
        _fly_wheel_talon_right.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        _fly_wheel_talon_right.reverseSensor(true);

        /* set the peak and nominal outputs, 12V means full */
        _fly_wheel_talon_right.configNominalOutputVoltage(+0.0f, -0.0f);
        _fly_wheel_talon_right.configPeakOutputVoltage(+12.0f, -12.0f); //Launcher only positive spin 
        /* set closed loop gains in slot0 */
        _fly_wheel_talon_right.setProfile(0);
        _fly_wheel_talon_right.setF(0.1097);//0.1097);
        _fly_wheel_talon_right.setP(0.15);//.22);
        _fly_wheel_talon_right.setI(0); 
        _fly_wheel_talon_right.setD(0);
        launchDelayTimer.start();
        retractIntakeTimer.start();
        //prefs = Preferences.getInstance();
        
        //set the right motor to same setting as left
        _fly_wheel_talon_left.changeControlMode(CANTalon.TalonControlMode.Follower);
        _fly_wheel_talon_left.set(_fly_wheel_talon_right.getDeviceID());
        
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new LaunchHighGoal());
    }
    
    /**
     * Toggles digital low gear on/off.
     * Triggered through ToggleGear function,
     * values are held in tankDrive method.
     */

    public void toggleLockin(){
    	if (this.isLockedIn)
    	{
    		this.isLockedIn = false;
    	}
    	else
    	{
    		this.isLockedIn = true;
    	}
    	SmartDashboard.putBoolean("LauncherLockedIn", isLockedIn);
    }
    
    public void ExtendIntake(){
		Intake_Talon.set(intakeForwards);
		Intake_Slide.set(true);
		retractIntakeTimer.reset();
		
		//SmartDashboard.putBoolean("Intake", Intake_Slide.get());
	}
    public void LowGoal(){

    		Intake_Talon.set(intakeBackwards);
    		_fly_wheel_talon_right.changeControlMode(TalonControlMode.PercentVbus);
        	_fly_wheel_talon_right.set(FLY_WHEEL_LOW_GOAL_SPEED);
        	//_fly_wheel_talon_left.set(_fly_wheel_talon_right.get());
        	
    		
    		//SmartDashboard.putBoolean("Intake", intakeIsOut);
    	}
    
    //public void resetTimer(){
    //	launchDelayTimer.reset();
    //}
    
    /**
     * Handles manual driving when smooth drive is not active.
     * @param moveValue Y-value of joystick passed to method.
     * @param rotateValue X-value of joystick passed to method.
     */
    public void highGoal(double moveValue, double rotateValue){
    	//this is default command, so retract (depower) the slide in case Extend just ran...
    	Intake_Slide.set(false);
    	
    	motorOutput = _fly_wheel_talon_right.getOutputVoltage() / _fly_wheel_talon_right.getBusVoltage();
    	/* prepare line to print */
        mtrspeed = _fly_wheel_talon_right.getSpeed();
        
        if(isLockedIn)
        {
        	/* Speed mode */
        	_fly_wheel_talon_right.changeControlMode(TalonControlMode.Speed);
        	targetSpeed = _targetFlywheelPercent * MAX_LAUNCHER_RPM;
        	
        	_fly_wheel_talon_right.set(targetSpeed); 

            if(Math.abs(_fly_wheel_talon_right.getSpeed() - 4020) < 50)
            	isOnTarget = true;
            else
            	isOnTarget = false;
            
        } else {
        	/* Percent voltage mode */
        	_fly_wheel_talon_right.changeControlMode(TalonControlMode.PercentVbus);
        	_fly_wheel_talon_right.set(moveValue);
        }
    	
    	    	
    	//launch is default command, so only turn on intake if flywheel is on
    	if (Math.abs(_fly_wheel_talon_right.get()) < 0.2)
    	{
    		launchDelayTimer.reset();
    		//if we were just in retract, this timer will be zero
    		//this code lets wheels on intake continue to pull ball in for x seconds
    		if (retractIntakeTimer.get() < INTAKE_ROLLERS_CONTINUE_END_TIME) 
    		{
    			//don't change anything - let it continue to intake
    		}
    		else if (retractIntakeTimer.get() < INTAKE_ROLLERS_PAUSE_END_TIME)
    		{
    					Intake_Talon.set(0);	
    		}
    		else if(retractIntakeTimer.get() < INTAKE_ROLLERS_BACKUP_END_TIME ) 
    		{
    			//start/continue backing up the rollers a bit
    			Intake_Talon.set(-0.8);
    			_fly_wheel_talon_right.changeControlMode(TalonControlMode.PercentVbus);
            	_fly_wheel_talon_right.set(FLY_WHEEL_LOW_GOAL_SPEED);
            	
    		}
            			else
    		{
    			//all our states are done - so turn the intake rollers off
    			Intake_Talon.set(0);	
    			_fly_wheel_talon_right.set(0);
    		}
    	}
    	else
    	{
	    	if (isAutoLaunch && launchDelayTimer.get() > LAUNCH_DELAY_SECONDS) {
	    		//feed the ball to the launcher flywheel
	    		Intake_Talon.set(intakeForwards);
	    	}
    	}
    	
    	
    }
    public boolean getLockedIn(){
    	return isOnTarget;
    }
    public void ShootNow(){
    		Intake_Talon.set(intakeForwards);
	}
    
    public void SpitOut(){
    	Intake_Talon.set(intakeBackwards);
    }
    
    public void StopIntake(){
    	Intake_Talon.set(0);
    }
    
    public boolean getBallSensor(){
    	return ballSensor.get();
    }
}




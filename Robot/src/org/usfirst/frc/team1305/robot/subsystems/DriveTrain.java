package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.Drive;


import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class DriveTrain extends Subsystem {
	
	CANTalon ml1 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_L1);
	CANTalon ml2 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_L2);
	CANTalon mr1 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_R1);
	CANTalon mr2 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_R2);
	CANTalon leftEncoder = ml2;
	CANTalon rightEncode = mr1;
	
	private boolean armPerspective = false;
	public 	boolean isLowGear = false;
	private double driveScalingFactor = 1; 
	private double rotateScalingFactor = 1; //1.2; //1.4;
	private double lowGearScalingFactor = .66;
	private boolean isDriveSmoothing = true;
	private double SLOW_DRIVE_SPEED = 0.03;
	private double FAST_DRIVE_SPEED = 1;
	private int MAX_DRIVE_RPM = 6200;
	double targetSpeed;
	public int getPosition;
	private int isgetPosition;
	public int setPosition;
	public int getPivotPosition;
	private int isPivotPosition;
	public int getFastPosition;
	private int isFastPosition;

	public int getBackPosition;
	private int isBackPosition;
	
	public int getBackwardsEncPosition;
	private int currentBackwardsEncPosition;
	
	
	private RobotDrive drive1 = new RobotDrive(ml1, ml2, mr1, mr2);
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new Drive());
    }
    
    public DriveTrain()
    {
    	
    	
    	leftEncoder.reverseSensor(false);
    	rightEncode.reverseSensor(true);
    	
    	getPosition = mr1.getEncPosition();
    	getPivotPosition = mr1.getEncPosition();
    	isgetPosition = getPosition;
    	isPivotPosition = getPosition;
    	isFastPosition = getPosition;
    	isBackPosition = getPosition;
    	
    	getBackwardsEncPosition = mr1.getEncPosition();
    	currentBackwardsEncPosition = getBackwardsEncPosition;
    	
    	ml2.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        ml2.reverseSensor(false);

        /* set the peak and nominal outputs, 12V means full */
        ml2.configNominalOutputVoltage(+0.0f, -0.0f);
        ml2.configPeakOutputVoltage(+12.0f, -12.0f); //Launcher only positive spin 
        /* set closed loop gains in slot0 */
        ml2.setProfile(0);
        ml2.setF(0.1097);//0.1097);
        ml2.setP(0.15);//.22);
        ml2.setI(0); 
        ml2.setD(0);
        
        mr1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        mr1.reverseSensor(true);

        /* set the peak and nominal outputs, 12V means full */
        mr1.configNominalOutputVoltage(+0.0f, -0.0f);
        mr1.configPeakOutputVoltage(+12.0f, -12.0f); //Launcher only positive spin 
        /* set closed loop gains in slot0 */
        mr1.setProfile(0);
        mr1.setF(0.1097);//0.1097);
        mr1.setP(0.15);//.22);
        mr1.setI(0); 
        mr1.setD(0);

	}
    
    /**
     * Toggles digital low gear on/off.
     * Triggered through ToggleGear function,
     * values are held in tankDrive method.
     */
    public void toggleGear(){
    	this.isLowGear = ! this.isLowGear;
    }
    public void toggleDriveSmoothing(){
    	this.isDriveSmoothing = ! this.isDriveSmoothing;
    	SmartDashboard.putBoolean("DriveSmoothing", isDriveSmoothing);
    }
    
    
    /**
     * Handles manual driving when smooth drive is not active.
     * @param moveValue Y-value of joystick passed to method.
     * @param rotateValue X-value of joystick passed to method.
     */
    public void arcadeDrive(double moveValue, double rotateValue){
    	//check for low gear
    	//SmartDashboard.putNumber("Move value", moveValue);
    	//SmartDashboard.putNumber("Rotate value", rotateValue);
    	//SmartDashboard.putBoolean("Low Gear", isLowGear);
    	if (mr1.getControlMode() != TalonControlMode.PercentVbus){
    		ml2.changeControlMode(TalonControlMode.PercentVbus);
        	mr1.changeControlMode(TalonControlMode.PercentVbus);
        	mr2.changeControlMode(TalonControlMode.PercentVbus);
            ml1.changeControlMode(TalonControlMode.PercentVbus);
    	}
    	
    	if(isLowGear){
    		moveValue *= lowGearScalingFactor;
    		rotateValue *= lowGearScalingFactor;
    	}
    	
    	if(isDriveSmoothing){
    		this.setRampRate(120);
    		
    	}
    	else
    	{
    		this.setRampRate(120);
    	}
    	
    	//SmartDashboard.putNumber("Gear-based Move value", moveValue);
    	//SmartDashboard.putNumber("Gear-based Rotate value", rotateValue);
    	
    	//check perspective and apply
    	if(armPerspective){
    		drive1.arcadeDrive(-moveValue * driveScalingFactor, rotateValue * rotateScalingFactor);
    		
    	}
    	else{
    		drive1.arcadeDrive(moveValue * driveScalingFactor, rotateValue * rotateScalingFactor);
    	}
    }

    /**
     * Handles all movement on the robot base.
     *
     * Takes commands from both smooth driving and normal driving,
     * if overridden manually.  leftValue and rightValue are passed from
     * the command Drive.
     * @param leftValue Handles left base movement of robot.
     * @param rightValue Handles right base movement of robot.
     */
    public void tankDrive(double leftValue, double rightValue){
    	if(isLowGear){
    		leftValue /= 1.6;
    		rightValue /= 1.6;
    	}
    	if(armPerspective){
        	drive1.tankDrive(-rightValue/1.3, -leftValue/1.3);
        	//SmartDashboard.putNumber("RightDrive", rightValue);
        	//SmartDashboard.putNumber("LeftDrive", leftValue);
    	}
    	else{
        	drive1.tankDrive(leftValue/1.3, rightValue/1.3);
    	}
    }
    private void setRampRate(double rampRate){
    	ml1.setVoltageRampRate(rampRate);
    	mr1.setVoltageRampRate(rampRate);
    	ml2.setVoltageRampRate(rampRate);
    	mr2.setVoltageRampRate(rampRate);
    }
    
    public void resetEncoders()
    {
    	ml2.setEncPosition(0);
    	mr1.setEncPosition(0);
    }
    
    public double getDistance()
    {
    	System.out.println("encoderPos" + (leftEncoder.getEncPosition() + rightEncode.getEncPosition())/2);
    	return (ml2.getEncPosition() + mr1.getEncPosition())/2;
    }
    
    public void driveForwardSlow(){
    	if (Math.abs(isgetPosition) > 0){
    		mr1.setEncPosition(0);
    		getPosition = mr1.getEncPosition();
    		isgetPosition = 0;
    	}
    	ml2.changeControlMode(TalonControlMode.Speed);
    	mr1.changeControlMode(TalonControlMode.Speed);
        mr2.changeControlMode(CANTalon.TalonControlMode.Follower);
        ml1.changeControlMode(CANTalon.TalonControlMode.Follower);
    	targetSpeed = SLOW_DRIVE_SPEED * MAX_DRIVE_RPM;
    	ml2.set(targetSpeed);
    	mr1.set(targetSpeed);
    	ml1.set(ml2.getDeviceID());
        mr2.set(mr1.getDeviceID());	        
        getPosition = mr1.getEncPosition();

        
    }
    
    public void driveForwardFast(){
    	if (Math.abs(isFastPosition) > 0){
    		mr1.setEncPosition(0);
    		getPosition = mr1.getEncPosition();
    		isFastPosition = 0;
    	}
    	ml2.changeControlMode(TalonControlMode.Speed);
    	mr1.changeControlMode(TalonControlMode.Speed);
        mr2.changeControlMode(CANTalon.TalonControlMode.Follower);
        ml1.changeControlMode(CANTalon.TalonControlMode.Follower);
    	targetSpeed = FAST_DRIVE_SPEED * MAX_DRIVE_RPM;
    	ml2.set(targetSpeed);
    	mr1.set(targetSpeed);
    	ml1.set(ml2.getDeviceID());
        mr2.set(mr1.getDeviceID());	        
        getFastPosition = mr1.getEncPosition();
    }
    
    public void driveBackwardsSlow2(){
    	if (Math.abs(isBackPosition) != 0){
    		mr1.setEncPosition(0);
    		getBackPosition = mr1.getEncPosition();
    		isBackPosition = 0;
    	}
    	mr1.reverseSensor(true);
    	ml2.changeControlMode(TalonControlMode.Speed);
    	mr1.changeControlMode(TalonControlMode.Speed);
        mr2.changeControlMode(CANTalon.TalonControlMode.Follower);
        ml1.changeControlMode(CANTalon.TalonControlMode.Follower);
    	targetSpeed = SLOW_DRIVE_SPEED * MAX_DRIVE_RPM;
    	ml2.set(-targetSpeed);
    	mr1.set(-targetSpeed);
    	ml1.set(ml2.getDeviceID());
        mr2.set(mr1.getDeviceID());	        
        getBackPosition = mr1.getEncPosition();
        System.out.println("Backwards");
        System.out.println(getBackPosition);
        //getBackwardsEncPosition = -getBackPosition;
    }
    
    
    public void driveBackwardFast(){
    	mr1.set(-1.0);
    	mr2.set(-1.0);
    	ml1.set(-1.0);
    	ml2.set(-1.0);
    }
    
    public void pivotCounterClockwise(){
    	mr1.set(0);
    	mr2.set(0);
    	ml1.set(-0.55);
    	ml2.set(-0.55);
    }
    
    public void pivotClockwise(){
    	if (Math.abs(isPivotPosition) != 0){
    		mr1.setEncPosition(0);
    		getPivotPosition = mr1.getEncPosition();
    		isPivotPosition = 0;
    	}
    	ml2.changeControlMode(TalonControlMode.Speed);
    	mr1.changeControlMode(TalonControlMode.Speed);
        mr2.changeControlMode(CANTalon.TalonControlMode.Follower);
        ml1.changeControlMode(CANTalon.TalonControlMode.Follower);
    	targetSpeed = SLOW_DRIVE_SPEED * MAX_DRIVE_RPM;
    	ml2.set(targetSpeed);
    	mr1.set(-targetSpeed);
    	ml1.set(ml2.getDeviceID());
        mr2.set(mr1.getDeviceID());	        
        getPivotPosition = mr1.getEncPosition();
        System.out.println("Turn");
        System.out.println(getPivotPosition);
    }
    
    public void turnRight(){
    	System.out.println("TurningRight");
    	ml1.set(0.25);
    	ml2.set(0.25);
    }
    public void stopDriving(){
    	System.out.println("Stopping");
    	ml1.set(0);
    	ml2.set(0);
    	mr1.set(0);
    	mr2.set(0);
    }
    
    public void talonSetToVBus(){
        
    }
    
}


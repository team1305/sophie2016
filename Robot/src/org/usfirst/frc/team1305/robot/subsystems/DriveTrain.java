package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.drive.Drive;

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
	CANTalon ml3 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_L3); //MTR3
	CANTalon mr1 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_R1);
	CANTalon mr2 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_R2);
	CANTalon mr3 = new CANTalon(RobotMap.CAN_DEVICE_DRIVE_R3); //MTR3
	
	CANTalon leftEncoder = ml2;
	CANTalon rightEncoder = mr1;
	
	private boolean armPerspective = false;
	public 	boolean isLowGear = false;
	private boolean isDriveSmoothing = true;
	
	private double driveScalingFactor = 1; 
	private double rotateScalingFactor = 1;
	private double lowGearScalingFactor = .66;
	private double SLOW_DRIVE_SPEED = 0.03;
	private double FAST_DRIVE_SPEED = 1;
	private double targetSpeed;
	
	private int MAX_DRIVE_RPM = 6200;
	private int isGetPosition;
	private int isPivotPosition;
	private int isFastPosition;
	private int isBackPosition;
	private int currentBackwardsEncPosition;
	
	public int getPosition;
	public int setPosition;
	public int getPivotPosition;
	public int getFastPosition;
	public int getBackPosition;
	public int getBackwardsEncPosition;
	
	public static final int ENCODER_COUNTS_PER_REVOLUTION = 128; //TODO: get the proper value for encoder counts per rev
	public static final boolean USING_MAG_ENCODERS = true; //TODO: set to false if you're using the blue encoders from last year
	
	private RobotDrive drive1 = new RobotDrive(ml1, ml2, mr1, mr2);
	
	
	

    public void initDefaultCommand()
    {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new Drive());
    }
    
    public DriveTrain()
    {
    	leftEncoder.reverseSensor(false);
    	rightEncoder.reverseSensor(true);
    	
    	getPosition = mr1.getEncPosition();
    	getPivotPosition = mr1.getEncPosition();
    	getBackwardsEncPosition = mr1.getEncPosition();
    	
    	isGetPosition = getPosition;
    	isPivotPosition = getPosition;
    	isFastPosition = getPosition;
    	isBackPosition = getPosition;
    	currentBackwardsEncPosition = getBackwardsEncPosition;
    	
    	if(this.USING_MAG_ENCODERS){
	    	ml2.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);

    	} else{
	    	ml2.setFeedbackDevice(FeedbackDevice.QuadEncoder);
	    	ml2.configEncoderCodesPerRev(ENCODER_COUNTS_PER_REVOLUTION);
    	}
    	//
        ml2.reverseSensor(false);

        ml2.configNominalOutputVoltage(+0.0f, -0.0f);
        ml2.configPeakOutputVoltage(+12.0f, -12.0f);
        ml2.setProfile(0);
        ml2.setF(0.1097);
        ml2.setP(0.15);
        ml2.setI(0); 
        ml2.setD(0);
        
        if(this.USING_MAG_ENCODERS){
        	mr1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
        } else{
	        mr1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
	        mr1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        }
        mr1.configEncoderCodesPerRev(ENCODER_COUNTS_PER_REVOLUTION);
        mr1.reverseSensor(true);

        mr1.configNominalOutputVoltage(+0.0f, -0.0f);
        mr1.configPeakOutputVoltage(+12.0f, -12.0f); 
        mr1.setProfile(0);
        mr1.setF(0.1097);
        mr1.setP(0.15);
        mr1.setI(0); 
        mr1.setD(0);
        
        mr3.changeControlMode(CANTalon.TalonControlMode.Follower);
        ml3.changeControlMode(CANTalon.TalonControlMode.Follower);
        mr3.set(mr1.getDeviceID());
        ml3.set(ml1.getDeviceID());


	}
    /**
     * 
     * @return left wheel distance, inches
     */
    public double getLeftEncPosition(){
//    	return (leftEncoder.getPosition())/136*100000;
    	return leftEncoder.getPosition();
    }
    
    /**
     * 
     * @return right wheel distance, inches
     */
    public double getRightEncPosition(){
//    	return (rightEncoder.getPosition())/136*100000;
    	return rightEncoder.getPosition();
    }
    
    public void toggleGear()
    {
    	this.isLowGear = ! this.isLowGear;
    }
    
    public void toggleDriveSmoothing()
    {
    	this.isDriveSmoothing = ! this.isDriveSmoothing;
    	SmartDashboard.putBoolean("DriveSmoothing", isDriveSmoothing);
    }
    
    public void arcadeDrive(double moveValue, double rotateValue)
    {
    	if (mr1.getControlMode() != TalonControlMode.PercentVbus)
    	{
    		ml2.changeControlMode(TalonControlMode.PercentVbus);
        	mr1.changeControlMode(TalonControlMode.PercentVbus);
        	mr2.changeControlMode(TalonControlMode.PercentVbus);
            ml1.changeControlMode(TalonControlMode.PercentVbus);
    	}
    	
    	if(isLowGear)
    	{
    		moveValue *= lowGearScalingFactor;
    		rotateValue *= lowGearScalingFactor;
    	}
    	
    	if(isDriveSmoothing)
    	{
    		this.setRampRate(120);
    	}
    	else
    	{
    		this.setRampRate(120);
    	}
    	
    	//check perspective and apply
    	if(armPerspective)
    	{
    		drive1.arcadeDrive(-moveValue * driveScalingFactor, rotateValue * rotateScalingFactor);
    		
    	}
    	else
    	{
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
    public void tankDrive(double leftValue, double rightValue)
    {
    	if (mr1.getControlMode() != TalonControlMode.PercentVbus){
    		ml2.changeControlMode(TalonControlMode.PercentVbus);
        	mr1.changeControlMode(TalonControlMode.PercentVbus);
        	mr2.changeControlMode(TalonControlMode.PercentVbus);
            ml1.changeControlMode(TalonControlMode.PercentVbus);
            //ml3.changeControlMode(TalonControlMode.PercentVbus); //MTR3
            //mr3.changeControlMode(TalonControlMode.PercentVbus); //MTR3
    	}
    	
    	if(isLowGear)
    	{
    		leftValue /= 1.6;
    		rightValue /= 1.6;
    	}
    	if(armPerspective)
    	{
        	drive1.tankDrive(-rightValue/1.3, -leftValue/1.3);
    	}
    	else
    	{
        	drive1.tankDrive(leftValue/1.3, rightValue/1.3);
    	}
    }
    
    
    public void tankdrive_raw(double left, double right){

    	if (mr1.getControlMode() != TalonControlMode.PercentVbus){
    		ml2.changeControlMode(TalonControlMode.PercentVbus);
        	mr1.changeControlMode(TalonControlMode.PercentVbus);
        	mr2.changeControlMode(TalonControlMode.PercentVbus);
            ml1.changeControlMode(TalonControlMode.PercentVbus);
            //ml3.changeControlMode(TalonControlMode.PercentVbus); //MTR3
            //mr3.changeControlMode(TalonControlMode.PercentVbus); //MTR3
    	}
    	
    	drive1.tankDrive(left,  right);
        
    }
    private void setRampRate(double rampRate)
    {
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
    	return (ml2.getEncPosition() + mr1.getEncPosition())/2;
    }
    
    public void driveForwardSlow()
    {
    	if (Math.abs(isGetPosition) > 0)
    	{
    		mr1.setEncPosition(0);
    		getPosition = mr1.getEncPosition();
    		isGetPosition = 0;
    	}
    	
    	ml2.changeControlMode(TalonControlMode.Speed);
    	mr1.changeControlMode(TalonControlMode.Speed);
        mr2.changeControlMode(CANTalon.TalonControlMode.Follower);
        ml1.changeControlMode(CANTalon.TalonControlMode.Follower);
        //mr3.changeControlMode(CANTalon.TalonControlMode.Follower); //MTR3
        //ml3.changeControlMode(CANTalon.TalonControlMode.Follower); //MTR3
        
    	targetSpeed = SLOW_DRIVE_SPEED * MAX_DRIVE_RPM;
    	ml2.set(targetSpeed);
    	mr1.set(targetSpeed);
    	ml1.set(ml2.getDeviceID());
        mr2.set(mr1.getDeviceID());
        //ml3.set(ml2.getDeviceID()); //MTR3
        //mr3.set(mr1.getDeviceID()); //MTR3
        
        getPosition = mr1.getEncPosition();
    }
    
    public void driveForwardFast()
    {
    	if (Math.abs(isFastPosition) > 0)
    	{
    		mr1.setEncPosition(0);
    		getPosition = mr1.getEncPosition();
    		isFastPosition = 0;
    	}
    	ml2.changeControlMode(TalonControlMode.Speed);
    	mr1.changeControlMode(TalonControlMode.Speed);
        mr2.changeControlMode(CANTalon.TalonControlMode.Follower);
        ml1.changeControlMode(CANTalon.TalonControlMode.Follower);
        //mr3.changeControlMode(CANTalon.TalonControlMode.Follower); //MTR3
        //ml3.changeControlMode(CANTalon.TalonControlMode.Follower); //MTR3
        
    	targetSpeed = FAST_DRIVE_SPEED * MAX_DRIVE_RPM;
    	
    	ml2.set(targetSpeed);
    	mr1.set(targetSpeed);
    	ml1.set(ml2.getDeviceID());
        mr2.set(mr1.getDeviceID());
        //ml3.set(ml2.getDeviceID());
        //mr3.set(mr1.getDeviceID());	        
        getFastPosition = mr1.getEncPosition();
    }
    
    public void driveBackwardsSlow2()
    {
    	if (Math.abs(isBackPosition) != 0)
    	{
    		mr1.setEncPosition(0);
    		getBackPosition = mr1.getEncPosition();
    		isBackPosition = 0;
    	}
    	
    	mr1.reverseSensor(true);
    	
    	ml2.changeControlMode(TalonControlMode.Speed);
    	mr1.changeControlMode(TalonControlMode.Speed);
    	
        mr2.changeControlMode(CANTalon.TalonControlMode.Follower);
        ml1.changeControlMode(CANTalon.TalonControlMode.Follower);
        //mr3.changeControlMode(CANTalon.TalonControlMode.Follower);
        //ml3.changeControlMode(CANTalon.TalonControlMode.Follower);
        
    	targetSpeed = SLOW_DRIVE_SPEED * MAX_DRIVE_RPM;
    	
    	ml2.set(-targetSpeed);
    	mr1.set(-targetSpeed);
    	
    	ml1.set(ml2.getDeviceID());
        mr2.set(mr1.getDeviceID());	   
        //ml3.set(ml2.getDeviceID()); //MTR3
        //mr3.set(mr1.getDeviceID()); //MTR3
        
        getBackPosition = mr1.getEncPosition();
    }
    
    public void driveBackwardFast()
    {
    	mr1.set(-1.0);
    	mr2.set(-1.0);
    	//mr3.set(-1.0); //MTR3
    	ml1.set(-1.0);
    	ml2.set(-1.0);
    	//ml3.set(-1.0); //MTR3
    }
    
    public void pivotCounterClockwise()
    {
    	mr1.set(0);
    	mr2.set(0);
    	//mr3.set(0); //MTR3
    	ml1.set(-0.55);
    	ml2.set(-0.55);
    	//ml3.set(-0.55); //MTR3
    }
    
    public void pivotClockwise()
    {
    	if (Math.abs(isPivotPosition) != 0)
    	{
    		mr1.setEncPosition(0);
    		getPivotPosition = mr1.getEncPosition();
    		isPivotPosition = 0;
    	}
    	
    	ml2.changeControlMode(TalonControlMode.Speed);
    	mr1.changeControlMode(TalonControlMode.Speed);
    	
        mr2.changeControlMode(CANTalon.TalonControlMode.Follower);
        ml1.changeControlMode(CANTalon.TalonControlMode.Follower);
        //mr3.changeControlMode(CANTalon.TalonControlMode.Follower); //MTR3
        //ml3.changeControlMode(CANTalon.TalonControlMode.Follower); //MTR3
        
    	targetSpeed = SLOW_DRIVE_SPEED * MAX_DRIVE_RPM;
    	
    	ml2.set(targetSpeed);
    	mr1.set(-targetSpeed);
    	
    	ml1.set(ml2.getDeviceID());
        mr2.set(mr1.getDeviceID());	   
        //ml3.set(ml2.getDeviceID()); //MTR3
        //mr3.set(mr1.getDeviceID());  //MTR3 
        
        getPivotPosition = mr1.getEncPosition();
    }
    
    public void turnRight()
    {
    	ml1.set(0.25);
    	ml2.set(0.25);
    	//ml3.set(0.25); //MTR3
    }
    public void stopDriving()
    {
    	ml1.set(0);
    	ml2.set(0);
    	//ml3.set(0); //MTR3
    	mr1.set(0);
    	mr2.set(0);
    	//mr3.set(0); //MTR3
    }
    public double getDriveAmps()
    {
    	double sum = mr1.getOutputCurrent() + mr2.getOutputCurrent() + ml1.getOutputCurrent() + ml2.getOutputCurrent();
    	return Math.abs(sum) / 4.0;
    }
}


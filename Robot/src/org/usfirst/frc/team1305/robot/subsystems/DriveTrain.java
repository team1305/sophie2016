package org.usfirst.frc.team1305.robot.subsystems;


import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.Drive;


import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
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
	
	private boolean armPerspective = false;
	public 	boolean isLowGear = false;
	private double driveScalingFactor = 1; 
	private double rotateScalingFactor = 1; //1.2; //1.4;
	private double lowGearScalingFactor = .66;
	private boolean isDriveSmoothing = true;
	
	private RobotDrive drive1 = new RobotDrive(ml1, ml2, mr1, mr2);
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new Drive());
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

  	
    	if(isLowGear){
    		moveValue *= lowGearScalingFactor;
    		rotateValue *= lowGearScalingFactor;
    	}
    	
    	if(isDriveSmoothing){
    		this.setRampRate(100);
    		
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
}


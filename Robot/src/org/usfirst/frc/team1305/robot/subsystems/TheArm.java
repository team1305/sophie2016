
package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;

import org.usfirst.frc.team1305.robot.commands.RetractArm;

/**
 *
 */
public class TheArm extends Subsystem {
    
	@SuppressWarnings("unused")
	private boolean isarmPos = false;
	private Solenoid Arm_Actuator = new Solenoid(RobotMap.CAN_SOLENOID, RobotMap.SOLENOID_CH_ARM);
	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	public TheArm(){
		
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new RetractArm());
    }
    public void extendArm(){
    	Arm_Actuator.set(true);
    	this.isarmPos = true;
    	SmartDashboard.putBoolean("Arm Out", Arm_Actuator.get());
    }
    
    public void retractArm(){
    	Arm_Actuator.set(false);
		this.isarmPos = false;
		SmartDashboard.putBoolean("Arm Out", Arm_Actuator.get());
	}
    
    public void AutonomousArm(){
    	Arm_Actuator.set(true);
    }
}


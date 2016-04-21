
package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1305.robot.commands.arm.RetractArm;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	SmartDashboard.putBoolean("Arm Active", isarmPos);
    }
    
    public void retractArm(){
    	Arm_Actuator.set(false);
		this.isarmPos = false;
		SmartDashboard.putBoolean("Arm Active", isarmPos);
	}
    
    public void AutonomousArm(){
    	Arm_Actuator.set(true);
    }
}


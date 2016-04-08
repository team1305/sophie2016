package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.BrakesRetract;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Brakes extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Solenoid s = new Solenoid(RobotMap.CAN_SOLENOID, RobotMap.SOLENOID_CH_BRAKES);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new BrakesRetract());
    }
    public void deploy(){
    	this.s.set(true);
    }
    public void retract(){
    	this.s.set(false);
    }
	public void toggle() {
		this.s.set(!this.s.get());
	}
}


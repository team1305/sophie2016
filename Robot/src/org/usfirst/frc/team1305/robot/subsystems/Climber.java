
package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.ClimberModes;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	VictorSP LeftClimb = new VictorSP(RobotMap.PWM_CLIMB_MOTOR_L);
	//VictorSP RightClimb = new VictorSP(RobotMap.PWM_CLIMB_MOTOR_R);
	
	Solenoid tilter = new Solenoid(RobotMap.CAN_DEVICE_LIFTER_SOLENOID, RobotMap.SOLENOID_CH_LIFT);
	
	DigitalInput stop = new DigitalInput(RobotMap.DIO_LIMIT_RETRACT_STOP);
	
	boolean isTilted = false;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ClimberModes());
    }
    
    public void ToggleTilt() {
    	
    	if(isTilted){
    		isTilted = false;
    		System.out.println("isTilted" + isTilted);
    	}
    	else{
    		isTilted = true;	
    	}
    	tilter.set(isTilted);
    }
    
    public void DoNothing() {
    	LeftClimb.set(0);
    	
    }
    
    
    public void Reel(int UpDown) {
    	boolean StopReel = stop.get();
    	System.out.println("Stop Reel" + StopReel);
    	if(!StopReel){
    		System.out.println("Stop Reel" + StopReel);
    		LeftClimb.set(UpDown);
    		//RightClimb.set(-1);
    		StopReel = stop.get();
    	}
    	else
    	{
    		LeftClimb.set(0);
    		// RightClimb.set(0);
    	}
    	
    }
}


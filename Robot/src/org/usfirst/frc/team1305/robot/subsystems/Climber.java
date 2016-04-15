package org.usfirst.frc.team1305.robot.subsystems;

import org.usfirst.frc.team1305.robot.RobotMap;
import org.usfirst.frc.team1305.robot.commands.ClimberModes;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;



public class Climber extends Subsystem {

	
	VictorSP ClimberMotors = new VictorSP(RobotMap.PWM_CLIMB_MOTORS);
	VictorSP ClawMotor = new VictorSP(RobotMap.PWM_CLAW_MOTOR);
	
	boolean isLaunched = false;
	
	public void initDefaultCommand(){
		setDefaultCommand(new ClimberModes());
	}
	public void LaunchClaw(int TurnReel){
		ClimberMotors.set(TurnReel);
		
	}
	public void DoNothing(){
		ClimberMotors.set(0);
		ClawMotor.set(0);
	}
	
	public void Reel(int UpDown){
		ClawMotor.set(UpDown);	
	}
}

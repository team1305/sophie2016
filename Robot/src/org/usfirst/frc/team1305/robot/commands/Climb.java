package org.usfirst.frc.team1305.robot.commands;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Climb extends Command {

	private int climberDirection;
	
	public Climb(int UpDown) {
		requires (Robot.climber);
		climberDirection = UpDown;
	}
	
	protected void initialize(){
		
	}
	
	protected void execute() {
		Robot.climber.LaunchClaw(climberDirection);
	}
	
	protected boolean isFinished(){
		return true;
	}
	
	protected void end(){
		
	}
	
	protected void interrupted(){
		
	}
}

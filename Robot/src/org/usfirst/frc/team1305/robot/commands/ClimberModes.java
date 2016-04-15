package org.usfirst.frc.team1305.robot.commands;

import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberModes extends Command {

	private int povReading;
	public static final int POV_NONE = -1;
	public static final int POV_UP = 0;
	public static final int POV_DOWN = 180;
	public static final int POV_RIGHT = 90;
	public static final int POV_LEFT = 270;
	
	public ClimberModes(){
		requires(Robot.climber);
	}
	
	protected void initialize(){
		
	}
	
	protected void execute(){
		povReading = Robot.oi.getPOV();
		
		switch (povReading){
		case POV_NONE:
			Robot.climber.DoNothing();
			break;
			
		case POV_UP:
			Robot.climber.LaunchClaw(-1);
			break;
			
		case POV_DOWN:
			Robot.climber.DoNothing();
			break;
			
		case POV_RIGHT:
			Robot.climber.Reel(1);
			break;	
			
		case POV_LEFT:
			Robot.climber.Reel(-1);
			break;	
		
		default:
			Robot.climber.DoNothing();
		}	
	}
	
	protected boolean isFinished(){
		return true;
	}
	
	protected void end(){
		
	}
	
	protected void interrupted(){
		
	}
}

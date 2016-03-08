package org.usfirst.frc.team1305.robot.commands;

import org.usfirst.frc.team1305.robot.Robot;
import org.usfirst.frc.team1305.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class ClimberModes extends Command {

	
	private int povReading;
	public static final int POV_NONE = -1;
	public static final int POV_UP = 0;
	public static final int POV_DOWN = 180;
	public static final int POV_RIGHT = 90;
	public static final int POV_LEFT = 270;

    public ClimberModes() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	povReading = Robot.oi.getPOV();
    	//System.out.println("POV" + povReading);
		
		switch (povReading) {
		case POV_NONE:
			Robot.climber.DoNothing();
			break;
			
		case POV_UP:
			Robot.climber.Reel(-1);
			break;
			
		case POV_DOWN:
			Robot.climber.Reel(1);
			break;
			
		case POV_RIGHT:
			Robot.climber.ToggleTilt();
			break;
		
		case POV_LEFT:
			Robot.climber.ToggleTilt();
			break;
			
		default:
			Robot.climber.DoNothing();
		}

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

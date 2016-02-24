package org.usfirst.frc.team1305.robot.commands;


import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 * Standard scrub drive for scrubs.
 */



public class AutonomousTurnLeft extends Command {	
	Timer endTimer = new Timer();
    public AutonomousTurnLeft() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(Drive);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	endTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        	
        	//apply stick values to the arcadedrive function
        	Robot.drivetrain.turnLeft();
//    	}

	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return endTimer.get() > 2;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	public static void toggleSmoothing() {

	}
}

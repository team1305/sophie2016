package org.usfirst.frc.team1305.robot.commands;


import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 * Standard scrub drive for scrubs.
 */



public class AutonomousDriveForward extends Command {	
	Timer endTimer = new Timer();
	private double numberOfSeconds;
    public AutonomousDriveForward(double NumberOfSeconds ) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(Drive);
    	requires(Robot.drivetrain);
    	numberOfSeconds = NumberOfSeconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//endTimer.reset();
    	endTimer.start();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        	
        	//apply stick values to the arcadedrive function
        	Robot.drivetrain.driveForward();
//    	}

	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println(endTimer.get());
        return endTimer.get() > numberOfSeconds;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}

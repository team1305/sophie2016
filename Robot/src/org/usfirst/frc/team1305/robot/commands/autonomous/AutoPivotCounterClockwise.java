package org.usfirst.frc.team1305.robot.commands.autonomous;


import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 * Standard scrub drive for scrubs.
 */



public class AutoPivotCounterClockwise extends Command {	
	Timer seqTimer = new Timer();
	private double timeSec;
    public AutoPivotCounterClockwise(double inSec) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(Drive);
    	requires(Robot.drivetrain);
    	timeSec = inSec;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//endTimer.reset();
    	seqTimer.start();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        	
        	//apply stick values to the arcadedrive function
        	Robot.drivetrain.pivotCounterClockwise();
//    	}

	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println(seqTimer.get());
        return seqTimer.get() > timeSec;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stopDriving();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
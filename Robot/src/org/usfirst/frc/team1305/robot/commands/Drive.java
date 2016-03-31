package org.usfirst.frc.team1305.robot.commands;


import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Standard scrub drive for scrubs.
 */



public class Drive extends Command {	
    public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(Drive);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        	double XL = Robot.oi.getDriveXL();
        	double YR = Robot.oi.getDriveYR();
        	//apply stick values to the arcadedrive function
        	Robot.drivetrain.arcadeDrive(XL, YR);
        	Robot.oi.rumbleLowDriveController(Robot.drivetrain.getDriveAmps() / 30.0);
//    	}

	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.oi.rumbleLowDriveController(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.oi.rumbleLowDriveController(0.0);
    }

	public static void toggleSmoothing() {

	}
}

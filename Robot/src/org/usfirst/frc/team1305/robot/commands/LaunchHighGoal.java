package org.usfirst.frc.team1305.robot.commands;


import org.usfirst.frc.team1305.robot.Robot;

/**
 * Standard scrub drive for scrubs.
 */
public class LaunchHighGoal extends Command {

    public LaunchHighGoal() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(Drive);
    	requires(Robot.launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.launcher.resetTimer();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(Robot.drivetrain.isLowGear == false)
//    	{
//	    	//sets values from drivestick for arcade drive
//    		//use other stick; double XL = Robot.oi.getDriveX() * 1.2; //0.8;
//	    	double XR = Robot.oi.getDriveXR();
//	    	double YL = Robot.oi.getDriveY();
//
//	    	//apply stick values to the arcadedrive function
//	    	Robot.drivetrain.arcadeDrive(YL, XR);
//    	}else{
    		//sets values from drivestick for arcade drive
    		//use other stick; double XL = Robot.oi.getDriveX();
        	//double XR = Robot.oi.getDriveXR();
        	boolean input = Robot.oi.getBallY();
        	double YL;
    		if(input)
    			YL = 1.0;
    		else
    			YL = 0.0;

        	//apply stick values to the Launcher function
        	Robot.launcher.highGoal( YL, 0);
//    	}

	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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

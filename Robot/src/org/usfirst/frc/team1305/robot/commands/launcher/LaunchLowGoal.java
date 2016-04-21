package org.usfirst.frc.team1305.robot.commands.launcher;


import org.usfirst.frc.team1305.robot.Robot;


import edu.wpi.first.wpilibj.command.Command;

/**
 * Standard scrub drive for scrubs.
 */
public class LaunchLowGoal extends Command {

    public LaunchLowGoal() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(Drive);
    	requires(Robot.launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
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
        	//double YL = Robot.oi.getBallY();

        	//apply stick values to the Launcher function
        	Robot.launcher.LowGoal();
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

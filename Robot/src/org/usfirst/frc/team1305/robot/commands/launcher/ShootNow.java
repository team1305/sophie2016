package org.usfirst.frc.team1305.robot.commands.launcher;


import org.usfirst.frc.team1305.robot.Robot;
import edu.wpi.first.wpilibj.Timer;


import edu.wpi.first.wpilibj.command.Command;

/**
 * Standard scrub drive for scrubs.
 */
public class ShootNow extends Command {
	private Timer StopShootingTimer = new Timer(); 
	private int ShooterEndTime = 2;
    public ShootNow() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(Drive);
    	requires(Robot.launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	StopShootingTimer.reset();
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
        	Robot.launcher.ShootNow();
//    	}

	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return StopShootingTimer.get() > ShooterEndTime;
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

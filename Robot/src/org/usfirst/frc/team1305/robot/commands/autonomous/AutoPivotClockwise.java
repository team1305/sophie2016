package org.usfirst.frc.team1305.robot.commands.autonomous;


import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Standard scrub drive for scrubs.
 */



public class AutoPivotClockwise extends Command {	
	private double setPosition;
	private double convertPosition;
	private double negconverted;
    public AutoPivotClockwise(int getPosition) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(Drive);
    	requires(Robot.drivetrain);
    	setPosition = getPosition;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//endTimer.reset();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        	
    		SmartDashboard.putNumber("Position in Inches:", setPosition);
		
    		//get setPosition and convert value from inches to usable value for encoders.
    		convertPosition = Math.abs(setPosition)/136*100000;
    		negconverted = -convertPosition;
    		SmartDashboard.putNumber("Position Useable Converted:", convertPosition);
    		SmartDashboard.putNumber("Position Useable Converted1:", negconverted);
    		System.out.println("Turn");
            System.out.println(negconverted);
        	//apply stick values to the arcadedrive function
    		//System.out.println(convertPosition);
        	Robot.drivetrain.pivotClockwise();
//    	}

	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.drivetrain.getPivotPosition < negconverted; 
        
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
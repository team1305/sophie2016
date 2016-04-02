
package org.usfirst.frc.team1305.robot.commands;

import org.usfirst.frc.team1305.robot.Robot;

/**
 *
 */
public class ExtendIntake extends Command {

    public ExtendIntake() {
        // Use requires() here to declare subsystem dependencies
        //requires(Robot.Intake);
        requires(Robot.launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.Intake.Extend();
    	Robot.launcher.ExtendIntake();
    	
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
}

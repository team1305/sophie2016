
package org.usfirst.frc.team1305.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

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
    	if(Robot.launcher.getBallSensor()){
    		Robot.oi.rumbleHighDriveController(1.0);
    		Robot.oi.rumbleLowBallController(1.0);
    		Robot.oi.rumbleHighBallController(1.0);
    	}
    	else{
    		Robot.oi.rumbleHighDriveController(0.0);
    		Robot.oi.rumbleLowBallController(0.0);
    		Robot.oi.rumbleHighBallController(0.0);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.oi.rumbleHighDriveController(0.0);
		Robot.oi.rumbleLowBallController(0.0);
		Robot.oi.rumbleHighBallController(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		Robot.oi.rumbleHighDriveController(0.0);
		Robot.oi.rumbleLowBallController(0.0);
		Robot.oi.rumbleHighBallController(0.0);
    }
}

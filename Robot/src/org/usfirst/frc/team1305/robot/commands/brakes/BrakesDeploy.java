package org.usfirst.frc.team1305.robot.commands.brakes;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BrakesDeploy extends Command {

    public BrakesDeploy() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.brakes);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.brakes.deploy();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.brakes.retract();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.brakes.retract();
    }
}

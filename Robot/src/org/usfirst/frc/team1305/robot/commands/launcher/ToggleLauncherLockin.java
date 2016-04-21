package org.usfirst.frc.team1305.robot.commands.launcher;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Toggles between digital low gear and high gear.
 */
public class ToggleLauncherLockin extends Command {

    public ToggleLauncherLockin() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.launcher.toggleLockin();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
*/
public class AutoShootHigh extends Command {
	private boolean isFinished = false;
	Timer seqTimer = new Timer();

    public AutoShootHigh() {
        requires(Robot.launcher);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	seqTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.launcher.highGoal(1, 0);
    	if(Robot.launcher.getLockedIn()){
    		Robot.launcher.ShootNow();
    		isFinished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished && seqTimer.get() > 2;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

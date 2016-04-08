package org.usfirst.frc.team1305.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCMDGroupDriveBackwards extends CommandGroup {
    
    public  AutoCMDGroupDriveBackwards() {
    	addSequential(new AutoMoveBackToPosition(-125));
    	addSequential(new StopDriving());
    }
}
	
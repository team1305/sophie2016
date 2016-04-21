package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.drive.StopDriving;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCMDGroupForwardShort extends CommandGroup {
    
    public  AutoCMDGroupForwardShort() {
    	addSequential(new AutoMoveSlowToPosition(40));
    	addSequential(new StopDriving());
    }
}

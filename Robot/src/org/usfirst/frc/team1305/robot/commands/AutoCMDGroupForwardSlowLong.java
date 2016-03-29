package org.usfirst.frc.team1305.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCMDGroupForwardSlowLong extends CommandGroup {
    
    public  AutoCMDGroupForwardSlowLong() {
    	addSequential(new AutoMoveSlowToPosition(100));
    	addSequential(new StopDriving());
    }
}

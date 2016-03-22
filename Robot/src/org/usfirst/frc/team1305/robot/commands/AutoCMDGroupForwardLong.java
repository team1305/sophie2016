package org.usfirst.frc.team1305.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCMDGroupForwardLong extends CommandGroup {
    
    public  AutoCMDGroupForwardLong() {
    	addSequential(new AutoMoveFastToPosition(200));
    	addSequential(new StopDriving());
    }
}

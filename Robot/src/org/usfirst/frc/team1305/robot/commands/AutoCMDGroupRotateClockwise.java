package org.usfirst.frc.team1305.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCMDGroupRotateClockwise extends CommandGroup {
    
    public  AutoCMDGroupRotateClockwise() {
    	
    	addSequential(new AutoPivotClockwise(-9));
    	addSequential(new StopDriving());
    }
}

package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.drive.StopDriving;

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

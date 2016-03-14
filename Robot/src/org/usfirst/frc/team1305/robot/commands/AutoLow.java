package org.usfirst.frc.team1305.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLow extends CommandGroup {
    
    public  AutoLow() {
    	addSequential(new AutoLowDriveForward(5));
    	addParallel(new AutoLowDriveForward(1));
    	addSequential(new AutoSpit(1.5));
    	addSequential(new StopDriving());
    }
}

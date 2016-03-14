package org.usfirst.frc.team1305.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoMike extends CommandGroup {
    
    public  AutoMike() {
    	addSequential(new AutoLowDriveForward(7.75));
    	addSequential(new AutoPivotClockwise(.5));
    	addSequential(new AutoLowDriveForward(3));
    	addParallel(new AutoLowDriveForward(3));
    	addSequential(new AutoSpit(3));
    }
}

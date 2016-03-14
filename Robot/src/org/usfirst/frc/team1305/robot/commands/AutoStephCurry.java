package org.usfirst.frc.team1305.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoStephCurry extends CommandGroup {
    
    public  AutoStephCurry() {
    	addSequential(new AutoLowDriveForward(7.75));
    	addSequential(new AutoPivotCounterClockwise(1.1));
    	addSequential(new AutoLowDriveBackward(3));
    	addSequential(new AutoLowDriveForward(0.1));
    	addSequential(new StopDriving());
    	addSequential(new ToggleLauncherLockin());
    	addSequential(new AutoShootHigh());
    	addSequential(new ToggleLauncherLockin());
    }
}

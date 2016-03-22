package org.usfirst.frc.team1305.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCMDGroupHighGoal extends CommandGroup {
    
    public  AutoCMDGroupHighGoal() {
    	addSequential(new AutoMoveSlowToPosition(100));
    	addSequential(new AutoPivotClockwise(-3));
    	addSequential(new AutoMoveSlowToPosition(30));
    	addSequential(new StopDriving());
    	//addSequential(new ToggleLauncherLockin());
    	//addSequential(new AutoShootHigh());
    	//addSequential(new ToggleLauncherLockin();
    }
}

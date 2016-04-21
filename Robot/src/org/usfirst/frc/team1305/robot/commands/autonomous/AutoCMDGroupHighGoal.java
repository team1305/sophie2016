package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.drive.StopDriving;
import org.usfirst.frc.team1305.robot.commands.launcher.ToggleLauncherLockin;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCMDGroupHighGoal extends CommandGroup {
    
    public  AutoCMDGroupHighGoal() {
    	addSequential(new AutoMoveSlowToPosition(125));
    	addSequential(new AutoPivotClockwise(-6));
    	addSequential(new AutoMoveBackToPosition(-51));
    	addSequential(new StopDriving());
    	addSequential(new ToggleLauncherLockin());
    	addSequential(new AutoShootHigh());
    	addSequential(new ToggleLauncherLockin());
    	addSequential(new StopDriving());
    }
}


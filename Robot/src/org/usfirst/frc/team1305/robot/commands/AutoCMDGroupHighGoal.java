package org.usfirst.frc.team1305.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCMDGroupHighGoal extends CommandGroup {
    
    public  AutoCMDGroupHighGoal() {
    	addSequential(new AutoMoveSlowToPosition(125));
    	addSequential(new AutonomousStub(), 2.0);
    	addSequential(new AutoPivotClockwise(-6));
    	addSequential(new AutonomousStub(), 2.0);
    	addSequential(new AutoMoveBackToPosition(-51));
    	addSequential(new AutonomousStub(), 1.0);
    	addSequential(new StopDriving());
    	addSequential(new ToggleLauncherLockin());
    	addSequential(new AutoShootHigh());
    	addSequential(new ToggleLauncherLockin());
    	addSequential(new StopDriving());
    }
}


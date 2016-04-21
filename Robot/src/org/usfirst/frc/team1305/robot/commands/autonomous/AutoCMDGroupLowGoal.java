package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.drive.StopDriving;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCMDGroupLowGoal extends CommandGroup {
    
    public  AutoCMDGroupLowGoal() {
    	addSequential(new AutoMoveSlowToPosition(100));
    	addSequential(new AutoPivotClockwise(-38));
    	addSequential(new AutoMoveSlowToPosition(90));
    	addSequential(new StopDriving());
    	addSequential(new AutoSpit(1.5));
    	addSequential(new StopDriving());
    }
}

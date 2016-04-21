package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.drive.StopDriving;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCMDGroupForwardLong extends CommandGroup {
    
    public  AutoCMDGroupForwardLong() {
    	addSequential(new AutoMoveFastToPosition(125));
    	addSequential(new StopDriving());
    }
}

package org.usfirst.frc.team1305.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCMDGroupForwardLongWithEncoder extends CommandGroup {
    
    public  AutoCMDGroupForwardLongWithEncoder() {
    	addSequential(new DriveEncoder(125, 0.9));
    	addSequential(new StopDriving());
    }
}

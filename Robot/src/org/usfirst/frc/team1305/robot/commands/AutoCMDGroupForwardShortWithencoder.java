package org.usfirst.frc.team1305.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCMDGroupForwardShortWithencoder extends CommandGroup {
    
    public  AutoCMDGroupForwardShortWithencoder() {
    	addSequential(new DriveEncoder(40, 0.4));
    	addSequential(new StopDriving());
    }
}

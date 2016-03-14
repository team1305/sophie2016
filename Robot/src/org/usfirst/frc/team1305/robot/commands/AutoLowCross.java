package org.usfirst.frc.team1305.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLowCross extends CommandGroup {
    
    public  AutoLowCross() {
    	addSequential(new AutoLowDriveForward(4));
    	addSequential(new StopDriving());
    }
}

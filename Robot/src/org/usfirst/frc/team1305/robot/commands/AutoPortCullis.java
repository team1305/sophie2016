package org.usfirst.frc.team1305.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPortCullis extends CommandGroup {
    
    public  AutoPortCullis() {
    		addParallel(new ExtendArm());
    		addSequential(new AutoLowDriveBackward(3));
    		addSequential(new StopDriving());
    }
}

package org.usfirst.frc.team1305.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoChevyFries extends CommandGroup {
    
    public  AutoChevyFries() {
    	addSequential(new AutoLowDriveBackward(0.5));
    	addSequential(new StopDriving());
    	addSequential(new ExtendArm());
    	addSequential(new AutoLowDriveBackward(0.25));
    	addParallel(new RetractArm());
    	addSequential(new AutoPowerDriveBackward(1));
    	addSequential(new StopDriving());
    }
}

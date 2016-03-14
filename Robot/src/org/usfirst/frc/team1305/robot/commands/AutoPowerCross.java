package org.usfirst.frc.team1305.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPowerCross extends CommandGroup {
    
    public  AutoPowerCross() {
    	addSequential(new AutoPowerDriveForward(3));
    	addSequential(new StopDriving());
    }
}

package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.drive.DriveEncoder;
import org.usfirst.frc.team1305.robot.commands.drive.StopDriving;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCMDGroupForwardLongWithEncoder extends CommandGroup {
    
    public  AutoCMDGroupForwardLongWithEncoder() {
    	addSequential(new DriveEncoder(20, 0.98));
    	addSequential(new StopDriving());
    }
}

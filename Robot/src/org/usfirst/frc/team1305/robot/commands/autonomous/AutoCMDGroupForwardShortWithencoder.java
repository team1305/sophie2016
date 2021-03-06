package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.drive.DriveEncoder;
import org.usfirst.frc.team1305.robot.commands.drive.StopDriving;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCMDGroupForwardShortWithencoder extends CommandGroup {
    
    public  AutoCMDGroupForwardShortWithencoder() {
    	addSequential(new DriveEncoder(40, 0.6));
    	addSequential(new StopDriving());
    }
}

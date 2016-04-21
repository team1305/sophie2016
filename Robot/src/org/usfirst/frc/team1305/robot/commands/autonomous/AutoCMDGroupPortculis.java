package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.arm.ExtendArm;
import org.usfirst.frc.team1305.robot.commands.arm.RetractArm;
import org.usfirst.frc.team1305.robot.commands.drive.StopDriving;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCMDGroupPortculis extends CommandGroup {
    
    public  AutoCMDGroupPortculis() {
    	addSequential(new AutoMoveSlowToPosition(15));
    	//addSequential(new StopDriving());
    	//addSequential(new ExtendArm());
    	addParallel(new ExtendArm());
    	addSequential(new AutoMoveSlowToPosition(105));
    	addParallel(new RetractArm());
    	addSequential(new StopDriving());
    }
}

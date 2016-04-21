package org.usfirst.frc.team1305.robot.commands.autonomous;

import org.usfirst.frc.team1305.robot.commands.arm.ExtendArm;
import org.usfirst.frc.team1305.robot.commands.arm.RetractArm;
import org.usfirst.frc.team1305.robot.commands.drive.StopDriving;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCMDGroupChevelDeFrise extends CommandGroup {
    
    public  AutoCMDGroupChevelDeFrise() {
    	addSequential(new AutoMoveSlowToPosition(100));
    	addSequential(new StopDriving());
    	addSequential(new ExtendArm());
    	addSequential(new AutoMoveSlowToPosition(50));
    	addParallel(new ExtendArm());
    	addSequential(new AutoMoveSlowToPosition(100));
    	addParallel(new RetractArm());
    	addSequential(new StopDriving());
    }
}

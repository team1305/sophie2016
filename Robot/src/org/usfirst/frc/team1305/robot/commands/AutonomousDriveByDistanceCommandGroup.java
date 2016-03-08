package org.usfirst.frc.team1305.robot.commands;

import org.usfirst.frc.team1305.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousDriveByDistanceCommandGroup extends CommandGroup {
    
    public  AutonomousDriveByDistanceCommandGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	//private final static DriveDistance;
    	//private final static DrivePower;
    	Robot.drivetrain.resetEncoders();
    	addSequential(new AutonomousDriveByDistance(1.6));
    	addSequential(new StopDriving());
    	addSequential(new AutonomousExtendArm());
    	addSequential(new AutonomousDriveByDistance(4));
    	addSequential(new RetractArm());
    	addSequential(new AutonomousDriveByDistance(1.6));
    	addSequential(new StopDriving());
    	
    }
}

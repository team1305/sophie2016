package org.usfirst.frc.team1305.robot.commands;

/**
 *
 */
public class AutonomousDriveAndCommandGroup extends CommandGroup {
    
    public  AutonomousDriveAndCommandGroup() {
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
    	addSequential(new AutonomousDriveForward());
    	addSequential(new StopDriving());
    	addSequential(new AutonomousExtendArm());
    	addSequential(new AutonomousDriveForward2());
    	addSequential(new RetractArm());
    	addSequential(new AutonomousDriveForward());
    	addSequential(new StopDriving());
    	
    }
}

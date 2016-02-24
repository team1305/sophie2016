
package org.usfirst.frc.team1305.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1305.robot.commands.AutonomousCommandGroup;
import org.usfirst.frc.team1305.robot.commands.AutonomousStub;
import org.usfirst.frc.team1305.robot.commands.AutonomousTurnLeft;
import org.usfirst.frc.team1305.robot.commands.AutonomousTurnRight;
import org.usfirst.frc.team1305.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1305.robot.subsystems.Launcher;
import org.usfirst.frc.team1305.robot.subsystems.TheArm;
import org.usfirst.frc.team1305.robot.subsystems.Camera;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	Joystick driveController;
	public static final int X_BUTTON = 3;
	public static final int Y_BUTTON = 4;
	public static final int A_BUTTON = 1;
	int autoMode = -1;
	Command trialAutonomousCommand;
	
	public static final DriveTrain drivetrain = new DriveTrain();
	public static final Launcher launcher = new Launcher();
	public static final Camera camera = new Camera(1);
	public static final TheArm arm = new TheArm();
	
    Command autonomousCommand;

    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        // instantiate the command used for the autonomous period
        autonomousCommand = new AutonomousStub();
        driveController = oi.getDriveContoller();
       
        	
        
    }
	
	public void disabledPeriodic() {
		
		SmartDashboard.putString("indisabledPeriodic", "true");
		if (driveController.getRawButton(X_BUTTON) && autoMode != 1){
			autoMode = 1;
			Scheduler.getInstance().removeAll();
			trialAutonomousCommand = new AutonomousCommandGroup();
			
		}
		else if (driveController.getRawButton(Y_BUTTON) && autoMode != 2){
			autoMode = 2;
			Scheduler.getInstance().removeAll();
			trialAutonomousCommand = new AutonomousTurnRight();
			
		}
		else if (driveController.getRawButton(A_BUTTON) && autoMode != -1){
			autoMode = -1;
			Scheduler.getInstance().removeAll();
			//trialAutonomousCommand = new AutonomousTurnRight();
			
		}
		switch(autoMode){
		case -1:
			SmartDashboard.putString("Auto", "none");
			break;
			
		case 1:
			SmartDashboard.putString("Auto", "TurnLeft");
			break;
			
		case 2:
			SmartDashboard.putString("Auto", "TurnRight");
			break;
		
		}
		
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	System.out.println("autonomousPeriodic");
        Scheduler.getInstance().run();
        
        trialAutonomousCommand.start();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}

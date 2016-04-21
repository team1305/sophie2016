
package org.usfirst.frc.team1305.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1305.robot.commands.autonomous.AutoCMDGroupChevelDeFrise;
import org.usfirst.frc.team1305.robot.commands.autonomous.AutoCMDGroupDriveBackwards;
import org.usfirst.frc.team1305.robot.commands.autonomous.AutoCMDGroupForwardLong;
import org.usfirst.frc.team1305.robot.commands.autonomous.AutoCMDGroupForwardLongWithEncoder;
import org.usfirst.frc.team1305.robot.commands.autonomous.AutoCMDGroupForwardShort;
import org.usfirst.frc.team1305.robot.commands.autonomous.AutoCMDGroupForwardShortWithencoder;
import org.usfirst.frc.team1305.robot.commands.autonomous.AutoCMDGroupForwardSlowLong;
import org.usfirst.frc.team1305.robot.commands.autonomous.AutoCMDGroupHighGoal;
import org.usfirst.frc.team1305.robot.commands.autonomous.AutoCMDGroupLowGoal;
import org.usfirst.frc.team1305.robot.commands.autonomous.AutoCMDGroupPortculis;
import org.usfirst.frc.team1305.robot.commands.autonomous.AutoCMDGroupRotateClockwise;
import org.usfirst.frc.team1305.robot.commands.autonomous.AutoNothing;
import org.usfirst.frc.team1305.robot.commands.autonomous.AutoPortCullis;
import org.usfirst.frc.team1305.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1305.robot.subsystems.Launcher;
import org.usfirst.frc.team1305.robot.subsystems.TheArm;
import org.usfirst.frc.team1305.robot.subsystems.Brakes;
import org.usfirst.frc.team1305.robot.subsystems.Camera;
import org.usfirst.frc.team1305.robot.subsystems.Climber;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	Command AutonomousCommand;
	SendableChooser AutoChooser;
	
	public static final DriveTrain drivetrain = new DriveTrain();
	public static final Launcher launcher = new Launcher();
	public static final Camera camera = new Camera();
	public static final TheArm arm = new TheArm();
	public static final Climber climber = new Climber();
	public static final Brakes brakes = new Brakes();
	
    //Command autonomousCommand;

    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        // instantiate the command used for the autonomous period
        //driveController = oi.getDriveContoller();
        AutoChooser = new SendableChooser();
        AutoChooser.addObject("Do Nothing", new AutoNothing());
        AutoChooser.addObject("High Goal 'Steph Cury'", new AutoCMDGroupHighGoal());
        AutoChooser.addObject("Low Goal", new AutoCMDGroupLowGoal());
        AutoChooser.addObject("Forward Short (Slow)", new AutoCMDGroupForwardShort());
        AutoChooser.addDefault("Forward Long (Slow)", new AutoCMDGroupForwardSlowLong()); // default auto
        AutoChooser.addObject("Forward Long (Fast)", new AutoCMDGroupForwardLong());
        AutoChooser.addObject("Chevel de Frise", new AutoCMDGroupChevelDeFrise());
        AutoChooser.addObject("Portculis", new AutoCMDGroupPortculis());
        AutoChooser.addObject("Rotate Clockwise", new AutoCMDGroupRotateClockwise());
        AutoChooser.addObject("Drive Backwards", new AutoCMDGroupDriveBackwards());
        AutoChooser.addObject("PortCullis drive", new AutoPortCullis());
        AutoChooser.addObject("fast long with encoders!", new AutoCMDGroupForwardLongWithEncoder());
        AutoChooser.addObject("slow short with encoders!", new AutoCMDGroupForwardShortWithencoder());
        SmartDashboard.putData("Auto Mode:", AutoChooser);
    }
	
	public void disabledPeriodic() {
		AutonomousCommand = (Command) AutoChooser.getSelected();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	AutonomousCommand = (Command) AutoChooser.getSelected();
    	if (AutonomousCommand != null) {
    	AutonomousCommand.start();
    	}else{
    		AutonomousCommand = new AutoCMDGroupForwardLong();
    		AutonomousCommand.start();
    	}
    	//ks 2016-03-06 trialAutonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (AutonomousCommand != null) AutonomousCommand.cancel();
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

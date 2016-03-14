
package org.usfirst.frc.team1305.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1305.robot.commands.AutoChevyFries;
import org.usfirst.frc.team1305.robot.commands.AutoHigh;
import org.usfirst.frc.team1305.robot.commands.AutoLow;
import org.usfirst.frc.team1305.robot.commands.AutoLowCross;
import org.usfirst.frc.team1305.robot.commands.AutoMike;
import org.usfirst.frc.team1305.robot.commands.AutoNothing;
import org.usfirst.frc.team1305.robot.commands.AutoPortCullis;
import org.usfirst.frc.team1305.robot.commands.AutoPowerCross;
import org.usfirst.frc.team1305.robot.commands.AutoStephCurry;
import org.usfirst.frc.team1305.robot.commands.AutonomousStub;
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
	public static final int B_BUTTON = 2;
	public static final int A_BUTTON = 1;
	int autoMode = -1;
	Command AutonomousCommand;
	SendableChooser AutoChooser;
	
	public static final DriveTrain drivetrain = new DriveTrain();
	public static final Launcher launcher = new Launcher();
	public static final Camera camera = new Camera(1);
	public static final TheArm arm = new TheArm();

	
    //Command autonomousCommand;

    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        // instantiate the command used for the autonomous period
		AutonomousCommand = new AutonomousStub();
        driveController = oi.getDriveContoller();
        AutoChooser = new SendableChooser();
        AutoChooser.addDefault("Do Nothing", new AutoNothing());
        AutoChooser.addObject("Low Cross", new AutoLowCross());
        AutoChooser.addObject("Power Cross", new AutoPowerCross());
        AutoChooser.addObject("Low Goal", new AutoLow());
        AutoChooser.addObject("High Goal", new AutoHigh());
        AutoChooser.addObject("Port Cullis", new AutoPortCullis());
        AutoChooser.addObject("Chevy and Fries", new AutoChevyFries());
        AutoChooser.addObject("Magic Mikey", new AutoMike());
        AutoChooser.addObject("Steph Curry", new AutoStephCurry());
        SmartDashboard.putData("Auto Mode:", AutoChooser);
    }
	
	public void disabledPeriodic() {
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	AutonomousCommand = (Command) AutoChooser.getSelected();
    	AutonomousCommand.start();
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

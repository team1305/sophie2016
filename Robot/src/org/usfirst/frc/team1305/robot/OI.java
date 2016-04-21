package org.usfirst.frc.team1305.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


//import org.usfirst.frc.team1305.robot.commands.ExampleCommand;



import org.usfirst.frc.team1305.robot.commands.arm.ExtendArm;
//import org.usfirst.frc.team1305.robot.commands.toggleSlide;
import org.usfirst.frc.team1305.robot.commands.brakes.BrakesDeploy;
import org.usfirst.frc.team1305.robot.commands.brakes.BrakesRetract;
import org.usfirst.frc.team1305.robot.commands.brakes.BrakesToggle;
import org.usfirst.frc.team1305.robot.commands.drive.ToggleDriveSmoothing;
import org.usfirst.frc.team1305.robot.commands.drive.ToggleGear;
import org.usfirst.frc.team1305.robot.commands.launcher.ExtendIntake;
import org.usfirst.frc.team1305.robot.commands.launcher.LaunchLowGoal;
import org.usfirst.frc.team1305.robot.commands.launcher.ShootNow;
import org.usfirst.frc.team1305.robot.commands.launcher.ToggleLauncherLockin;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	private final Joystick driveController = new Joystick(0);
	private final Joystick ballController = new Joystick(1);
	
	// xbox axis reference
	public static final int AXIS_XL = 4;
	public static final int AXIS_YL = 3;
	public static final int AXIS_XR = 0;
    public static final int AXIS_YR = 1;
    //Attack 3 axis reference
	public static final int AXIS_X = 4;//1
	public static final int AXIS_Y = 1;//0 is side to side, and 2 is rotate
	public static final int AXIS_Z = 3;
	
	
	private final Button toggleGear = new JoystickButton(driveController, 6);
	private final Button extendArm = new JoystickButton(driveController, 5);
	private final Button toggleDriveSmoothing = new JoystickButton(driveController, 9);
	//private final Button climberup = new JoystickButton(driveController, 1);
	//private final Button climberdown = new JoystickButton(driveController, 4);
	//private final Button tiltout = new JoystickButton(driveController, 2);
	private final Button brakeDeploy = new JoystickButton(driveController, 1);
	private final Button brakeRetract = new JoystickButton(driveController, 2);


	
	private final Button toggleLauncherLockin = new JoystickButton(ballController, 5);
	private final Button launchLowGoal = new JoystickButton(ballController, 2);//7
	private final Button extendIntake = new JoystickButton(ballController, 1);//8
	private final Button shootNow = new JoystickButton(ballController,  6);
	
	
	private final boolean invertDriveStick = false;
	private final boolean invertDriveRotateStick = false;
	//private final boolean invertBallStick = true;
	
	//getAxis functions for drivestick
	
		public OI()
		{
			//toggleGear       = new JoystickButton(driveController, 5);
			//toggleGear.whenPressed    (new ToggleGear());
			toggleGear.whenPressed(new ToggleGear());
			toggleLauncherLockin.whenPressed(new ToggleLauncherLockin());
			toggleDriveSmoothing.whenPressed(new ToggleDriveSmoothing());
			brakeDeploy.whenPressed(new BrakesDeploy());
			brakeRetract.whenPressed(new BrakesRetract());
			
			
			
			launchLowGoal.whileHeld(new LaunchLowGoal());
			extendIntake.whileHeld(new ExtendIntake());
			extendArm.whileHeld(new ExtendArm());
			shootNow.whileHeld(new ShootNow());
			
		}
		
		public double getDriveXR(){
			if (invertDriveRotateStick)
	        {
				return driveController.getRawAxis(AXIS_XR) * -1;
	        }
	        else
	        {
	        	return driveController.getRawAxis(AXIS_XR);
	        }
		}

		public double getDriveXL(){
			if (invertDriveRotateStick)
	        {
	        	return driveController.getRawAxis(AXIS_XL) * -1;
	        }
	        else
	        {
	        	return driveController.getRawAxis(AXIS_XL);
	        }
			
	    }

	    public double getDriveYR(){
	        if (invertDriveStick)
	        {
	        	return driveController.getRawAxis(AXIS_YR) * -1;
	        }
	        else
	        {
	        	return driveController.getRawAxis(AXIS_YR);
	        }
	    }
	    public double getDriveYL(){
	        if (invertDriveStick)
	        {
	        	return driveController.getRawAxis(AXIS_YL) * -1;
	        }
	        else
	        {
	        	return driveController.getRawAxis(AXIS_YL);
	        }
	    }
	    
	    public boolean getBallY(){
	        	return ballController.getRawButton(8);
	    }
	    
	    public int getPOV(){
	    	//System.out.println("POV in oi" + driveController.getPOV(0));
	    	return ballController.getPOV();
	    }
	    
	    public Joystick getDriveContoller(){
	    	return driveController;
	    }
	    public void rumbleHighDriveController(double mag){
	    	driveController.setRumble(RumbleType.kRightRumble, (float) mag);
	    }
	    public void rumbleLowDriveController(double mag){
	    	driveController.setRumble(RumbleType.kLeftRumble, (float) mag);
	    }
	    public void rumbleHighBallController(double mag){
	    	ballController.setRumble(RumbleType.kRightRumble, (float) mag);
	    }
	    public void rumbleLowBallController(double mag){
	    	ballController.setRumble(RumbleType.kLeftRumble, (float) mag);
	    }
}



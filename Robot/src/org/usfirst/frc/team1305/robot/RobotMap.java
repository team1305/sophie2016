package org.usfirst.frc.team1305.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	public static final int AXIS_DRIVETRAIN_MOVE   = OI.AXIS_Y;
	public static final int AXIS_DRIVETRAIN_ROTATE = OI.AXIS_X;

	
	public static final int CAN_DEVICE_DRIVE_L1 = 11;//1;
	public static final int CAN_DEVICE_DRIVE_L2 = 12;//2;
	public static final int CAN_DEVICE_DRIVE_L3 = 14;//3rd motor on the left side;
	public static final int CAN_DEVICE_DRIVE_R1 = 15;//9;
	public static final int CAN_DEVICE_DRIVE_R2 = 16;//10;
	public static final int CAN_DEVICE_DRIVE_R3 = 18;//3rd motor on right side;
	
	public static final int CAN_DEVICE_LAUNCHER_L = 17;//13;
	public static final int CAN_DEVICE_LAUNCHER_R = 13;//13;
	public static final int CAN_DEVICE_INTAKEROLLER = 10;
	
	public static final int CAN_SOLENOID = 21;
	public static final int SOLENOID_CH_SLIDER = 1;
	public static final int SOLENOID_CH_ARM = 0;
	public static final int SOLENOID_CH_BRAKES = 2;
	
	public static final int PWM_CLIMB_MOTORS = 8;
	public static final int PWM_CLAW_MOTOR = 9;
	
	public static final int DIO_BALLSENSOR = 0;


		
	
}

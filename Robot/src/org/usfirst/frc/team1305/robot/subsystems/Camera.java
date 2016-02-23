package org.usfirst.frc.team1305.robot.subsystems;


import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;




/**
 *Camera control System
 */

public class Camera extends Subsystem {

	
    CameraServer server;

	
	public Camera(int a)
	{
		server = CameraServer.getInstance();
        server.setQuality(30);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam3");
        
	}
	

    public void initDefaultCommand() {
    
    }
}


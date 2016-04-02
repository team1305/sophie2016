package org.usfirst.frc.team1305.robot.subsystems;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Camera control System
 */

public class Camera extends Subsystem {

	
    CameraServer server;
    private boolean isBackCam = false;

	
	public Camera(int a)
	{
		server = CameraServer.getInstance();
        server.setQuality(30);
        //the camera name (ex "cam0") can be found through the roborio web interface
        if(!isBackCam){
        	//front cam
            server.startAutomaticCapture("cam3");
        }else
        	//back cam
            server.startAutomaticCapture("cam0");
        // change above to rear facing camera
	}
	
	public void camSwitch(){
		if(!isBackCam){
            server.startAutomaticCapture("cam3");
        }else
            server.startAutomaticCapture("cam0");
        // change above to rear facing camera
		
	}

    public void initDefaultCommand() {
    
    }
}


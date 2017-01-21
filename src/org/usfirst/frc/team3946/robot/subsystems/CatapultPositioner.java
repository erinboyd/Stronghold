package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class CatapultPositioner extends Subsystem {
    
	public final Solenoid catapult = new Solenoid(RobotMap.catapult);
	//private Solenoid catapult;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
//  public CatapultPositioner() {
//  catapult = new Solenoid(1);                        // Solenoid port
//  }
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	LiveWindow.addActuator("Pistons", "Catapult Positioner", catapult);
    }
    
    public void launchReady() {
    	catapult.set(true);
    }
    
    public void loadReady() {
    	catapult.set(false);   	
    }
}

//http://www.chiefdelphi.com/forums/showthread.php?t=80790
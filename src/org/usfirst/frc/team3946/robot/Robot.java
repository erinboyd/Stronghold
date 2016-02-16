package org.usfirst.frc.team3946.robot;

import org.usfirst.frc.team3946.robot.commands.AutoTravel;
import org.usfirst.frc.team3946.robot.commands.LoadPrefNames;
import org.usfirst.frc.team3946.robot.subsystems.BallPickup;
import org.usfirst.frc.team3946.robot.subsystems.CatapultPositioner;
import org.usfirst.frc.team3946.robot.subsystems.DriveTrainEncoder;
import org.usfirst.frc.team3946.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3946.robot.subsystems.IntakePositioner;
import org.usfirst.frc.team3946.robot.subsystems.LaunchLatch;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Drivetrain drivetrain = new Drivetrain();
	public static DriveTrainEncoder driveTrainEncoder = new DriveTrainEncoder();
	public static AnalogGyro gyro = new AnalogGyro(1);
	public static AnalogInput ballFinder = new AnalogInput(3);
	public static BallPickup ballPickup = new BallPickup();
	public static IntakePositioner intakePositioner = new IntakePositioner();
	public static LaunchLatch launchLatch = new LaunchLatch();
	public static CatapultPositioner catapultPositioner = new CatapultPositioner();
	public static Compressor compressor = new Compressor(0);
	public static Accelerometer accel = new BuiltInAccelerometer();
	public static ThreadedPi threadedpi = new ThreadedPi();
	Command autonomousCommand;
	SendableChooser chooser;
	public static double distanceTarget = 130;
	public static double distanceOffset = 0;
	double testPref;
	public static Preferences prefs;
	public static SendableChooser cameraSelector;
	static String lastSelected = "";
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		driveTrainEncoder.initEncoders();
		chooser = new SendableChooser();
		chooser.addDefault("Position One", "Position One");
		chooser.addObject("Position Two", "Position Two");
		chooser.addObject("Position Three", "Position Three");
		chooser.addObject("Position Four", "Position Foue");
		chooser.addObject("Position Five", "Position Five");
		SmartDashboard.putData("Auto mode", chooser);
		prefs = Preferences.getInstance();
		distanceTarget = prefs.getDouble("DistanceTarget", distanceTarget);
				//prefs.putDouble("DistanceTarget", 130.0);
		distanceOffset =  prefs.getDouble("DistanceOffset", distanceOffset);
				//prefs.putDouble("DistanceOffset", 0.0);
		//testPref =  prefs.getDouble("TestPref", 11110.0);
		//prefs.putDouble("TestPrefSave", 11110.0);
		SmartDashboard.putData("LoadPrefNames", new LoadPrefNames());
//		CameraServer server = CameraServer.getInstance();
//		server.setQuality(50);
//		server.startAutomaticCapture("cam0");
//		CameraServer server2 = CameraServer.getInstance();
//		server2.setQuality(50);
//		server2.startAutomaticCapture("cam1");
		cameraSelector = new SendableChooser();
		cameraSelector.addDefault("Front View", "Front");
		cameraSelector.addObject("Back View", "Back");
		SmartDashboard.putData("Camera Selector", cameraSelector);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		// autonomousCommand = (Command) chooser.getSelected();

		String autoSelected = SmartDashboard.getString("Auto Selector",
				"Default");
		switch (autoSelected) {
		case "Position One":
		default:
			autonomousCommand = new AutoTravel(5, 60);
			break;
		case "Position Two":
			autonomousCommand = new AutoTravel(5, 30);
			break;
		case "Position Three":
				autonomousCommand = new AutoTravel(5, 15);
				break;
		case "Position Four":
				autonomousCommand = new AutoTravel(5, 5);
				break;
		case "Position Five":
				autonomousCommand = new AutoTravel(5, -30);
				break;
		}
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
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
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Actual Right Speed",
				Robot.driveTrainEncoder.getRightRate());
		SmartDashboard.putNumber("Actual Right Distance",
				Robot.driveTrainEncoder.getRightDistance());
		SmartDashboard.putNumber("Range Finder", Robot.ballFinder.getVoltage());
		SmartDashboard.putNumber("Gyro", Robot.gyro.getAngle());
		SmartDashboard.putNumber("Actual Left Speed",
				Robot.driveTrainEncoder.getLeftRate());
		SmartDashboard.putNumber("Actual Left Distance",
				Robot.driveTrainEncoder.getLeftDistance());
		SmartDashboard.putNumber("Accel X Value", Robot.accel.getX());
		SmartDashboard.putNumber("Accel Y Value", Robot.accel.getY());
		SmartDashboard.putNumber("Accel Z Value", Robot.accel.getZ());
		SmartDashboard.putNumber("Angle",
				(Math.atan2(Robot.accel.getY(), Robot.accel.getZ())) * (180 / Math.PI));
		updateCamera();
	}
	
	public void updateCamera(){
		String cameraSelected = SmartDashboard.getString("Camera Selector",
				"default");
		if(cameraSelected == lastSelected){
			return;
		}
		switch (cameraSelected) {
		default:
		case "Front View":		
			CameraServer server = CameraServer.getInstance();
			server.setQuality(50);
			server.startAutomaticCapture("cam0");
			lastSelected = "Front View";
			break;
		case "Back View":
			CameraServer server2 = CameraServer.getInstance();			
			server2.setQuality(50);
			server2.startAutomaticCapture("cam1");
			lastSelected = "Back View";
			break;
		
			//break;
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}

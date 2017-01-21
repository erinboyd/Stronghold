package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LoadPrefNames extends Command {

	public LoadPrefNames() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.prefs.putDouble("DistanceTarget", Robot.distanceTarget);
		Robot.prefs.putDouble("DistanceOffset", Robot.distanceOffset);
		Robot.prefs.putDouble("AngleMultiplier", Robot.angleMultiplier);
		Robot.prefs.putDouble("AngleAddition", Robot.angleAddition);
		Robot.prefs.putDouble("DistanceMultiplier", Robot.distanceMultiplier);
		Robot.prefs.putDouble("DistanceAddition", Robot.distanceAddition);
		Robot.prefs.putDouble("LeftInches", Robot.leftInches);
		Robot.prefs.putDouble("RightInches", Robot.rightInches);
		Robot.prefs.putDouble("LeftTicks", Robot.leftTicks);
		Robot.prefs.putDouble("RightTicks", Robot.rightTicks);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

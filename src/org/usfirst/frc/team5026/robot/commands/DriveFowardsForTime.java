package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the robot forwards for a given amount of time
 */

public class DriveFowardsForTime extends Command {
	
	private Timer timer = new Timer();
	private double seconds;
	private double negative;

    public DriveFowardsForTime(double seconds) {
    	requires(Robot.drive);
    	this.seconds = Math.abs(seconds);
    	this.negative = Math.abs(seconds) / seconds;
    }

    protected void initialize() {
    	Robot.drive.stopDriveMotors();
    	timer.reset();
    	timer.start();
    }

    protected void execute() {
    	Robot.drive.setLeftRightMotors(0.5 * negative, 0.5 * negative);
    }

    protected boolean isFinished() {
        return timer.hasPeriodPassed(seconds);
    }

    protected void end() {
    	Robot.drive.stopDriveMotors();
    	timer.stop();
    }

    protected void interrupted() {
    	end();
    }
}

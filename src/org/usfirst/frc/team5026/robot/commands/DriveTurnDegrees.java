package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTurnDegrees extends Command {

	private Timer timer = new Timer();
	private double seconds;
	private double negative;

    public DriveTurnDegrees(double degrees) {
    	requires(Robot.drive);
    	this.seconds = Math.abs(degrees) * Constants.SECONDS_PER_ANGLE;
    	this.negative = Math.abs(degrees) / degrees;
    }

    protected void initialize() {
    	Robot.drive.stopDriveMotors();
    	timer.reset();
    	timer.start();
    	System.out.println(seconds);
    }

    protected void execute() {
    	Robot.drive.setLeftRightMotors(0.5 * negative, -0.5 * negative);
    	SmartDashboard.putNumber("offsetAngle", Robot.rotate.offsetAngle);
    	SmartDashboard.putNumber("Current Angle", Robot.rotate.getGyro());
    }

    protected boolean isFinished() {
        return timer.hasPeriodPassed(seconds);
    }

    protected void end() {
    	Robot.drive.stopDriveMotors();
    	System.out.println("Ended!");
    	timer.stop();
    }

    protected void interrupted() {
    	System.out.println("Drive Turn Interrupted");
    	end();
    }
}

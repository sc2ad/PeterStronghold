package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionAngleCoarseRotation extends Command {

	private Timer timer = new Timer();
	private double seconds;
	private double negative;

    public VisionAngleCoarseRotation() {
    	requires(Robot.drive);
    	requires(Robot.rotate);
    }

    protected void initialize() {
    	
    	Robot.drive.stopDriveMotors();
    	System.out.println(Robot.rotate.offsetAngle);
    	seconds = Math.abs(Robot.rotate.offsetAngle) * Constants.SECONDS_PER_ANGLE;
    	negative = Math.abs(Robot.rotate.offsetAngle) / Robot.rotate.offsetAngle;
    	System.out.println(seconds);
    	timer.reset();
    	timer.start();
    }

    protected void execute() {
    	//System.out.println("go!");
    	Robot.drive.setLeftRightMotors(0.5 * negative, -0.5 * negative);
    	SmartDashboard.putNumber("offsetAngle", Robot.rotate.offsetAngle);
    	SmartDashboard.putNumber("Current Angle", Robot.rotate.getGyro());
    }

    protected boolean isFinished() {
        return timer.hasPeriodPassed(seconds);
    }

    protected void end() {
    	Robot.drive.stopDriveMotors();
    	timer.stop();
    	System.out.println("VisionEnded!");
    }

    protected void interrupted() {
    	end();
    	System.out.println("VisionInterrupted!!");
    }
}

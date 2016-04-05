package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Waits until the shooter PID has reached a steady state
 */

public class ShooterWaitForStabilize extends Command {
	
	private boolean finished;
	
	private double lastUpperExponentialError = 46000;
	private double currentUpperExponentialError = 0.0;
	
	private double lastLowerExponentialError = 46000;
	private double currentLowerExponentialError = 0.0;
	
	/** 0 < alpha < 1
	 * Number came out of our ass (and a little testing)
	 */
	private double alpha = 0.15;
	
	private double upperRange;
	private double lowerRange;

    public ShooterWaitForStabilize() {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    	finished = false;
    	
    	// Conversion from rpm to ticks
    	upperRange = Math.abs(Robot.shooter.upperRPM * Constants.SHOOTER_TOLERANCE * 4096 / 600);
    	lowerRange = Math.abs(Robot.shooter.lowerRPM * Constants.SHOOTER_TOLERANCE * 4096 / 600);
    	
    	lastUpperExponentialError = 22000;
    	currentUpperExponentialError = 22000;
    	
    	lastLowerExponentialError = 22000;
    	currentLowerExponentialError = 22000;
    }

    protected void execute() {
    	Robot.shooter.prints(true);
    	Robot.shooter.prints(false);
    	
    	currentUpperExponentialError = alpha * Math.abs(Robot.hardware.upperShooterGroup.motor1.getClosedLoopError()) + (1 - alpha) * lastUpperExponentialError;
    	currentLowerExponentialError = alpha * Math.abs(Robot.hardware.lowerShooterGroup.motor1.getClosedLoopError()) + (1 - alpha) * lastLowerExponentialError;
    	
    	if(Math.abs(currentUpperExponentialError) < upperRange && Math.abs(currentLowerExponentialError) < lowerRange) {
    		System.out.println("Stable!: " + currentUpperExponentialError + " < " + upperRange + " and " + currentLowerExponentialError + " < " + lowerRange);
    		SmartDashboard.putString("Stable shooter?", "YES");
    		finished = true;
    	}
    	else if(Robot.oi.boardButton4.get()) {
    		finished = true;
    		Scheduler.getInstance().removeAll();
    	}
    	
    	lastUpperExponentialError = currentUpperExponentialError;
    	lastLowerExponentialError = currentLowerExponentialError;
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {
    	Robot.shooter.slowStop();
    }

    protected void interrupted() { // Need to test
    	end();
    	finished = true;
    	Scheduler.getInstance().removeAll();
    }
}

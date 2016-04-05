package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StageTwoQueueToShooter extends Command {

	private Timer timer = new Timer();
	private double seconds = 0.5;
	
    public StageTwoQueueToShooter() {
        requires(Robot.stageTwo);
    }

    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    protected void execute() {
    	Robot.stageTwo.intakeBall();
    }

    protected boolean isFinished() {
        return timer.hasPeriodPassed(seconds);
    }

    protected void end() {
    	Robot.stageTwo.stopMotors();
    	timer.stop();
    }

    protected void interrupted() {
    }
}

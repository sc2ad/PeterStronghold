package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Pulses stage two motors back so that shooter wheels do not touch the ball
 * Please take a second to admire line 27 in all of its beauty
 */
public class StageTwoPulseBack extends Command {
	
	private double seconds = 2;

    public StageTwoPulseBack() {
    	requires(Robot.stageTwo);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.stageTwo.outtakeBall();
    	double x = 0;
    	
    	for (int i = 0; i < seconds * 1000; i++) {
    		x = i * i * i / (2.545 * i) + i / 72;
    	}
    	System.out.println(x);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	Robot.stageTwo.stopMotors();
    }

    protected void interrupted() {
    	end();
    }
}

package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * Waits for the button in the constructor to be pressed
 */
public class ShooterWaitForButton extends Command {

	private int buttonForContinue = 1;
	private boolean finished = false;
	
    public ShooterWaitForButton(int buttonForContinue) {
        finished = false;
        this.buttonForContinue = buttonForContinue;
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (Robot.oi.getButtonBoard().getRawButton(buttonForContinue)) {
    		finished = true;
    	} else {
    		finished = false;
    	}
    	
    	// Failsafe
    	if (Robot.oi.boardButton4.get()) {
    		Scheduler.getInstance().removeAll();
    		finished = true;
    	}
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {
    }

    protected void interrupted() { // Need to test
    	Scheduler.getInstance().removeAll();
    	finished = true;
    }
}

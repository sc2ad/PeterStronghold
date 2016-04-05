package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * Stops the shooter ... slowly
 */
public class ShooterSlowStop extends Command {

    public ShooterSlowStop() {
        requires(Robot.shooter);
    }
    
    protected void initialize() {
    }
    
    protected void execute() {
    	Robot.shooter.slowStop();
    	Scheduler.getInstance().removeAll();
    }
    
    protected boolean isFinished() {
        return true;
    }
    
    protected void end() {
    }
    
    protected void interrupted() { // Need to test
    	Robot.shooter.slowStop();
    	Scheduler.getInstance().removeAll();
    }
}

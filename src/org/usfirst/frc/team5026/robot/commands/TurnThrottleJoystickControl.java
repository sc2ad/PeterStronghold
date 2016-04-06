package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.lib.PantherJoystick;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses the swaggiest joystick control of them all.
 */

public class TurnThrottleJoystickControl extends Command {
	
	private PantherJoystick turnJoystick;
	private PantherJoystick throttleJoystick;

    public TurnThrottleJoystickControl(PantherJoystick turnJoystick, PantherJoystick throttleJoystick) {
    	requires(Robot.drive);
    	this.turnJoystick = turnJoystick;
    	this.throttleJoystick = throttleJoystick;
    }

    protected void initialize() {
    	Robot.drive.stopDriveMotors();
    }

    protected void execute() {
    	Robot.drive.turnThrottleControl(turnJoystick.getXAxis(), -throttleJoystick.getYAxis());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.drive.stopDriveMotors();
    }

    protected void interrupted() {
    	end();
    }
}

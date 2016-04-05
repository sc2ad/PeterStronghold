package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.lib.PantherJoystick;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Used to control drive base with joystick
 * Is the default command for the Drive subsystem
 */

public class ArcadeDriveWithJoystick extends Command {
	
	private PantherJoystick joystick;

    public ArcadeDriveWithJoystick(PantherJoystick joystick) {
    	requires(Robot.drive);
    	this.joystick = joystick;
    }

    protected void initialize() {
    	Robot.drive.stopDriveMotors();
    }

    protected void execute() {
    	Robot.drive.useArcadeDrive(-joystick.getXAxis(), joystick.getYAxis());
    	
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

package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.lib.PantherJoystick;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	Robot.drive.useArcadeDrive(-joystick.getYAxis(), -joystick.getXAxis());
    	SmartDashboard.putNumber("X", -joystick.getXAxis());
    	SmartDashboard.putNumber("Y", joystick.getYAxis());
    	SmartDashboard.putNumber("X Joystick" , joystick.getX());
    	SmartDashboard.putNumber("Y Joystick", joystick.getY());
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

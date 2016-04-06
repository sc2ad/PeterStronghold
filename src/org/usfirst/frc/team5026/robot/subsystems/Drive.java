package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.lib.PantherJoystick;
import org.usfirst.frc.team5026.robot.Hardware;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.ArcadeDriveWithJoystick;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Defines the drive base of the robot
 */

public class Drive extends Subsystem {
	
	private RobotDrive drive;
	private PantherJoystick joystick;
	private Hardware hardware;
	
	public Drive() {
		joystick = Robot.oi.getDriveJoystick();
		drive = new RobotDrive(new Talon(1), new Talon(0)); // hardware.leftDrive, hardware.rightDrive new Talon(1), new Talon(0));
	}
	
	/**
	 * For driving with joystick
	 * @param joystick: Joystick to be passed in
	 */
	public void joystickControl(PantherJoystick joystick) {
		drive.arcadeDrive(joystick);
	}
	
	public void useArcadeDrive(double xAxis, double yAxis) {
		drive.arcadeDrive(xAxis, yAxis);
	}
    
	public void setLeftRightMotors(double leftMotors, double rightMotors) {
		drive.setLeftRightMotorOutputs(leftMotors, rightMotors);
		System.out.println(leftMotors + ", " + rightMotors);
	}
	
	public void stopDriveMotors() {
		setLeftRightMotors(0, 0);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new ArcadeDriveWithJoystick(joystick));
    }
}


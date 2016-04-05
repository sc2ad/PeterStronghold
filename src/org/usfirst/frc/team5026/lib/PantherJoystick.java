package org.usfirst.frc.team5026.lib;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Accounts for joystick deadzones and drive motor deadzones
 */

public class PantherJoystick extends Joystick {
	
	private double deadzoneX;
	private double deadzoneY;
	private double motorDeadzone;
	private double scalingX;
	private double scalingY;
	
	public PantherJoystick(int port, double deadzoneX, double deadzoneY, double motorDeadzone, double scalingX, double scalingY) {
		super(port);
		this.deadzoneX = deadzoneX;
		this.deadzoneY = deadzoneY;
		this.motorDeadzone = motorDeadzone;
		this.scalingX = scalingX;
		this.scalingY = scalingY;
	}
	
	public double getXAxis() {
		double value;
		double negative = (Math.abs(getX()) / getX());
		double xAxis = getX() - negative * deadzoneX;
		
		if(Math.abs(getX()) > deadzoneX) {
			if(xAxis < 0) {
				value = -xAxis;
			}
			else {
				value = xAxis;
			}
			value = scalingX * value + negative * motorDeadzone;
		}
		else {
			value = 0;
		}
		
		return value;
	}
	
	public double getYAxis() {
		double value;
		double negative = (Math.abs(getY()) / getY());
		double yAxis = getY() - negative * deadzoneY;
		
		if(Math.abs(getY()) > deadzoneY) {
			if(yAxis < 0) {
				value = -yAxis;
			}
			else {
				value = yAxis;
			}
			value = scalingY * value + negative * motorDeadzone;
		}
		else {
			value = 0;
		}
		
		return value;
	}
}

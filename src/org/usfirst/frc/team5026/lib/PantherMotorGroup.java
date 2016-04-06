package org.usfirst.frc.team5026.lib;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Infinitely expandable motor group 'cause why not? 
 */

public class PantherMotorGroup implements SpeedController {
	
	private SpeedController[] motorGroup;
	private boolean isGroupReversed;
	
	public PantherMotorGroup(boolean isGroupReversed, SpeedController...motors) {
		this.isGroupReversed = isGroupReversed;
		motorGroup = motors.clone();
	}

	@Override
	public void pidWrite(double output) {
		for(SpeedController speedController : motorGroup) {
			speedController.pidWrite(output);
		}
	}

	@Override
	public double get() {
		return motorGroup[0].get();
	}

	@Override
	public void set(double speed, byte syncGroup) {
		for(SpeedController speedController: motorGroup) {
			speedController.set(speed, syncGroup);
		}	
	}

	@Override
	public void set(double speed) {
		for(SpeedController speedController : motorGroup) {
			speedController.set(speed);
		}
	}

	@Override
	public void setInverted(boolean isInverted) {
		isGroupReversed = isInverted;
	}

	@Override
	public boolean getInverted() {
		return isGroupReversed;
	}

	@Override
	public void disable() {
		for(SpeedController speedController : motorGroup) {
			speedController.disable();
		}
	}


	
	@Override
	public void stopMotor() {
		for(SpeedController speedController : motorGroup) {
			speedController.stopMotor();
		}
	}
	

}
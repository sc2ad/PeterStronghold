package org.usfirst.frc.team5026.lib;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Groups two motors together for the shooter subsystem
 */

public class ShooterMotorGroup implements SpeedController {
    	
	public CANTalon motor1;
	public CANTalon motor2;
	    	
	public ShooterMotorGroup(CANTalon motor1, CANTalon motor2) {
		this.motor1 = motor1;
		this.motor2 = motor2;
	}
	
	public double get() {
		return motor1.get();
	}
	
	public void set(double speed, byte whatever) {
	}
	 
   	public void set(double speed) {
		motor1.set(speed);
		motor2.set(speed);
	}

	public void disable() {
		motor1.disable();
		motor2.disable();
	}

	public void pidWrite(double output) {
		motor1.pidWrite(output); 
		motor2.pidWrite(output);
	}

	public void setInverted(boolean isInverted) {}

	public boolean getInverted() {
		return false;
	}

	public void stopMotor() {}
}

package org.usfirst.frc.team5026.lib;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * For use with drive base motors since one motor is flipped.
 */

public class DriveMotorGroup extends Subsystem implements SpeedController {
	
    private SpeedController motor1;
    private SpeedController motor2;
    private SpeedController motor3;
    
    boolean isInverted;
    
    public DriveMotorGroup(SpeedController motor1, SpeedController motor2, SpeedController motor3, boolean isInverted) {
    	this.motor1 = motor1;
    	this.motor2 = motor2;
    	this.motor3 = motor3;
    	this.isInverted = isInverted;
    }
    
    public double get() {
    	return motor1.get();
    }
    
    public void set(double speed, byte syncGroup) {
        set(isInverted ? -speed : speed);
    }
    
    public void set(double speed) {
    	motor1.set(-speed); // Top motor in gearbox
    	motor2.set(speed);
    	motor3.set(speed);
    }
    
    public void setInverted(boolean isRightSide) {
    	isInverted = isRightSide;
    }

    public boolean getInverted() {
    	return isInverted;
    }
    
    public void disable() {
    	motor1.disable();
    	motor2.disable();
    	motor3.disable();
    }
    
    public void pidWrite(double output) {
    	motor1.pidWrite(output);
    	motor2.pidWrite(output);
    	motor3.pidWrite(output);
    }

    public void initDefaultCommand() {}

	public void stopMotor() {}
}


package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Defines the banner sensor and motor for stage two
 */

public class StageTwo extends Subsystem {
	
	private DigitalInput bannerSensor;
	private CANTalon stageTwoMotor;
	
	public StageTwo() {
		bannerSensor = Robot.hardware.stageTwoBannerSensor;
		stageTwoMotor = Robot.hardware.stageTwoMotor;
	}
	
	public void intakeBall() {
		stageTwoMotor.set(0.40);
	}
	
	public void outtakeBall() {
		stageTwoMotor.set(-0.40);
	}
	
	public void stopMotors() {
		stageTwoMotor.set(0.0);
	}
	
	public boolean hasBall() {
		return !bannerSensor.get();
	}
	
    public void initDefaultCommand() {
    }
}


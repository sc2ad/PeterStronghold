package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.IntakeArmLower;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Defines the intake arm pistons and roller
 */

public class IntakeArm extends Subsystem {
    
	private DoubleSolenoid intakeArmSolenoid;
	private Talon rollerMotor;
	
	public IntakeArm() {
		intakeArmSolenoid = Robot.hardware.intakeSolenoid;
		rollerMotor = Robot.hardware.intakeMotor;
	}
	
	public void extendIntakeArm() {
		intakeArmSolenoid.set(Value.kForward);
	}
	
	public void retractIntakeArm() {
		intakeArmSolenoid.set(Value.kReverse);
	}
	
	public void intakeBall() {
		rollerMotor.set(-0.9);
	}
	
	public void outtakeBall() {
		rollerMotor.set(0.9);
	}
	
	public void stopIntake() {
		rollerMotor.set(0.0);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new IntakeArmLower());
    }
}


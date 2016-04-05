package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.lib.ShooterMotorGroup;
import org.usfirst.frc.team5026.lib.DriveMotorGroup;

import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * Class that creates all physical components of the robot
 */

public class Hardware {
	public DriveMotorGroup leftDrive;
	public DriveMotorGroup rightDrive;
	
	public DoubleSolenoid shifterSolenoid;
	public DoubleSolenoid shooterSolenoid;
	public DoubleSolenoid intakeSolenoid;
	
	public ShooterMotorGroup lowerShooterGroup;
	public ShooterMotorGroup upperShooterGroup;
	
	public Talon intakeMotor;
	public CANTalon stageTwoMotor;
	
	public Accelerometer roboRIOAccelerometer;
	public Gyro gyro;
	public DigitalInput stageTwoBannerSensor;
	
	public Hardware() {
		leftDrive = new DriveMotorGroup(new Talon(RobotMap.LEFT_DRIVE_MOTOR_1), new Talon(RobotMap.LEFT_DRIVE_MOTOR_2), new Talon(RobotMap.LEFT_DRIVE_MOTOR_3), true);
		rightDrive = new DriveMotorGroup(new Talon(RobotMap.RIGHT_DRIVE_MOTOR_1), new Talon(RobotMap.RIGHT_DRIVE_MOTOR_2), new Talon(RobotMap.RIGHT_DRIVE_MOTOR_3), false);

		shifterSolenoid = new DoubleSolenoid(RobotMap.SHIFT_SOLENOID_FORWARD_CHANNEL, RobotMap.SHIFT_SOLENOID_REVERSE_CHANNEL); 
		shooterSolenoid = new DoubleSolenoid(RobotMap.SHOOTER_SOLENOID_FORWARD_CHANNEL, RobotMap.SHOOTER_SOLENOID_REVERSE_CHANNEL);
		intakeSolenoid = new DoubleSolenoid(RobotMap.INTAKE_SOLENOID_FORWARD_CHANNEL, RobotMap.INTAKE_SOLENOID_REVERSE_CHANNEL);

		lowerShooterGroup = new ShooterMotorGroup(new CANTalon(RobotMap.LOWER_SHOOTER_MOTOR_1), new CANTalon(RobotMap.LOWER_SHOOTER_MOTOR_2));
		upperShooterGroup = new ShooterMotorGroup(new CANTalon(RobotMap.UPPER_SHOOTER_MOTOR_1), new CANTalon(RobotMap.UPPER_SHOOTER_MOTOR_2));

		intakeMotor = new Talon(RobotMap.INTAKE_ROLLER_MOTOR);
		stageTwoMotor = new CANTalon(RobotMap.STAGE_TWO_MOTOR);

		roboRIOAccelerometer = new ADXL362(Port.kOnboardCS1, Range.k2G);
		gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
		stageTwoBannerSensor = new DigitalInput(RobotMap.STAGE_TWO_BANNER_SENSOR);
	}
}

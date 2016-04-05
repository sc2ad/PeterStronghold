package org.usfirst.frc.team5026.robot;

/**
 * Contains mapping of ports, sensors, and actuators
 * Makes it easier to change ports on the fly
 */

public class RobotMap {
	
	// Drive Motors (All Talon SR)
	public static final int LEFT_DRIVE_MOTOR_1 = 2;
	public static final int LEFT_DRIVE_MOTOR_2 = 3;
	public static final int LEFT_DRIVE_MOTOR_3 = 4;
	
	public static final int RIGHT_DRIVE_MOTOR_1 = 5;
	public static final int RIGHT_DRIVE_MOTOR_2 = 6;
	public static final int RIGHT_DRIVE_MOTOR_3 = 7;
	
	// Intake Motor (Talon SR)
	public static final int INTAKE_ROLLER_MOTOR = 0;
	
	// Stage Two Motor (Talon SRX)
	public static final int STAGE_TWO_MOTOR = 3;
	
	// Shooter Motors (ALL Talon SRX)
	public static final int UPPER_SHOOTER_MOTOR_1 = 4; // Has encoder
	public static final int UPPER_SHOOTER_MOTOR_2 = 6;
	
	public static final int LOWER_SHOOTER_MOTOR_1 = 1; // Has encoder
	public static final int LOWER_SHOOTER_MOTOR_2 = 2;
	
	// Double Solenoids
	public static final int SHIFT_SOLENOID_FORWARD_CHANNEL = 6;
	public static final int SHIFT_SOLENOID_REVERSE_CHANNEL = 0;
	
	public static final int SHOOTER_SOLENOID_FORWARD_CHANNEL = 2;
	public static final int SHOOTER_SOLENOID_REVERSE_CHANNEL = 3;
	
	public static final int INTAKE_SOLENOID_FORWARD_CHANNEL = 7;
	public static final int INTAKE_SOLENOID_REVERSE_CHANNEL = 1;
	
	// Banner Sensor
	public static final int STAGE_TWO_BANNER_SENSOR = 5;
	
	// Joysticks
	public static final int BUTTON_BOARD = 0;
	public static final int DRIVE_JOYSTICK = 1;
	
	// Button board mappings
	public static final int BOARD_BUTTON_1 = 1;
	public static final int BOARD_BUTTON_2 = 2;
	public static final int BOARD_BUTTON_3 = 3;
	public static final int BOARD_BUTTON_4 = 4;
	public static final int BOARD_BUTTON_5 = 7;
	public static final int BOARD_BUTTON_6 = 6;
	public static final int BOARD_BUTTON_7 = 5;
	public static final int BOARD_BUTTON_8 = 8;
	public static final int BOARD_BUTTON_9 = 9;
	public static final int BOARD_SWITCH_10 = 10;
	public static final int BOARD_SWITCH_11 = 11;
	public static final int BOARD_SWITCH_12 = 12;
	public static final int BOARD_SWITCH_13 = 13;
}

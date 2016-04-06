package org.usfirst.frc.team5026.robot;

/**
 * Contains all the numbers that allow for robot control.
 * Examples are PID constants and encoder information.
 */

public class Constants {
	
	// Joystick
	public static final double DRIVE_JOYSTICK_X_DEADZONE = 0.3;
	public static final double DRIVE_JOYSTICK_Y_DEADZONE = 0.3;
	public static final double DRIVE_JOYSTICK_X_SCALING = 0.75;
	public static final double DRIVE_JOYSTICK_Y_SCALING = 1;

	// Motors
	public static final double DRIVE_MOTORS_DEAD_ZONE = 0.35; // Value required to overcome static friction
	
	// Encoder
	public static final int WHEEL_RADIUS = 6; // Inches
	public static final double WHEEL_CIRCUMFERENCE = 37.70; // Inches
	public static final int ENCODER_CPR = 256; // Counts per revolution
	public static final double ENCODER_COUNTS_PER_INCH = 6.79;
	
	// Shooter PID (All values obtained through magic and RTFM)
	public static final double UPPER_SHOOTER_P = 0.448973;
	public static final double UPPER_SHOOTER_I = 0.000898;
	public static final double UPPER_SHOOTER_D = 56.121601;
	public static final double UPPER_SHOOTER_F = 0.02;
	
	public static final double LOWER_SHOOTER_P = 0.093536;
	public static final double LOWER_SHOOTER_I = 0.000187;
	public static final double LOWER_SHOOTER_D = 0;
	public static final double LOWER_SHOOTER_F = 0.0107;
	
	public static final double SHOOTER_RAMP_RATE = 36;
	public static final int SHOOTER_MAX_RPM = 14000;
	public static final double SHOOTER_TOLERANCE = 0.005;
	public static final int SHOOTER_PROFILE = 0;
	
	// Vision values
	public static final int Y_THRESHOLD_LOW = -16;
	public static final int Y_THRESHOLD_HIGH = 20;
	public static final int X_NOMINAL_8_FT = 322;
	public static final int Y_NOMINAL_8_FT = 138;
	public static final double NOMINAL_FEET = 8;
	
	public static final int X_MAX_DIFFERENCE = 205; // At 30 degrees
	public static final double X_MAX_ANGLE = 30;
	public static final double ANGLE_THRESHOLD = 5;
	public static final double SECONDS_PER_ANGLE = 0.017;
}

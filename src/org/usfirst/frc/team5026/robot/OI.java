package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.lib.PantherJoystick;
import org.usfirst.frc.team5026.robot.commands.IntakeArmRaise;
import org.usfirst.frc.team5026.robot.commands.IntakeRollerSpinOut;
import org.usfirst.frc.team5026.robot.commands.RoutineIntakeBall;
import org.usfirst.frc.team5026.robot.commands.RoutineShootWithJoystick;
import org.usfirst.frc.team5026.robot.commands.ShooterPistonsRaise;
import org.usfirst.frc.team5026.robot.commands.ShooterSlowStop;
import org.usfirst.frc.team5026.robot.commands.StageTwoOuttake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	
	private PantherJoystick driveJoystick;
	private PantherJoystick turnJoystick; // FOR TESTING PURPOSES
	
	private Joystick buttonBoard;
	
	public Button boardButton1;
	public Button boardButton2;
	public Button boardButton3;
	public Button boardButton4;
	public Button boardButton5;
	public Button boardButton6;
	public Button boardButton7;
	public Button boardButton8;
	public Button boardButton9;
	public Button boardSwitch10;
	public Button boardSwitch11;
	public Button boardSwitch12;
	public Button boardSwitch13;
	
	public OI() {
		SmartDashboard.putString("INITS", "NONE");
		driveJoystick = new PantherJoystick(RobotMap.DRIVE_JOYSTICK, Constants.DRIVE_JOYSTICK_X_DEADZONE, Constants.DRIVE_JOYSTICK_Y_DEADZONE, 
				Constants.DRIVE_MOTORS_DEAD_ZONE, Constants.DRIVE_JOYSTICK_X_SCALING, Constants.DRIVE_JOYSTICK_Y_SCALING);
		buttonBoard = new Joystick(RobotMap.BUTTON_BOARD);
		
		turnJoystick = new PantherJoystick(3, Constants.DRIVE_JOYSTICK_X_DEADZONE, Constants.DRIVE_JOYSTICK_Y_DEADZONE, 
				Constants.DRIVE_MOTORS_DEAD_ZONE, Constants.DRIVE_JOYSTICK_X_SCALING, Constants.DRIVE_JOYSTICK_Y_SCALING);
		initButtons();
	}
	
	public PantherJoystick getDriveJoystick() {
		return driveJoystick;
	}
	
	// For testing purposes
	public PantherJoystick getTurnJoystick() {
		return turnJoystick;
	}
	public Joystick getButtonBoard() {
		return buttonBoard;
	}
	
	public void initButtons() {
		initDriveJoystick();
		initButtonBoard();
	}
	
	// Construct and add commands to buttons
	private void initDriveJoystick() {
		
	}
	
	private void initButtonBoard() {
		boardButton1 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_1);
		boardButton2 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_2);
		boardButton3 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_3);
		boardButton4 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_4);
		boardButton5 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_5);
		boardButton6 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_6);
		boardButton7 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_7);
		boardButton8 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_8);
		boardButton9 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_9);
		boardSwitch10 = new JoystickButton(buttonBoard, RobotMap.BOARD_SWITCH_10);
		boardSwitch11 = new JoystickButton(buttonBoard, RobotMap.BOARD_SWITCH_11);
		boardSwitch12 = new JoystickButton(buttonBoard, RobotMap.BOARD_SWITCH_12);
		boardSwitch13 = new JoystickButton(buttonBoard, RobotMap.BOARD_SWITCH_13);
		SmartDashboard.putString("INITS", "Board Done");
	}
	
	public void mapButtonsToCommands() {
		// Button Board
		boardButton1.whenPressed(new RoutineShootWithJoystick());
		//boardButton2.whenPressed(new RoutineBatterShot());
		//boardButton3.whenPressed(new RoutineAutoAlign());
		boardButton4.whenPressed(new ShooterSlowStop());
		boardButton5.whenPressed(new RoutineIntakeBall());
		boardButton6.whileHeld(new IntakeRollerSpinOut());
		boardButton7.whileHeld(new StageTwoOuttake());
		//boardButton8.whenPressed(new RoutineRotateTheta());
		//boardButton9.whenPressed(new RoutineRotateTheta());
		boardSwitch10.whileHeld(new IntakeArmRaise());
		boardSwitch11.whileHeld(new ShooterPistonsRaise());
		//boardSwitch12.whenPressed(new AIRPLANE());
		//boardSwitch13.whenPressed(new AIRPLANE());
	}
}


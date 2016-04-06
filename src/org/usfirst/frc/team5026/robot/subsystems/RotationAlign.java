package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * OKAY: FIX ALL THE NAMES HERE AND MAYBE PUT THIS OUTSIDE OF SUBSYSTEMS
 */
public class RotationAlign extends Subsystem {
    
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Timer timer = new Timer();
	public double targetAngle;
	public double offsetAngle;
	public double distance;
	public double kDegrees = 0.04;
	NetworkTable table;
	public RotationAlign () {
		NetworkTable.setServerMode();
	}

    public void initDefaultCommand() {
    }
    
    
    public void rotate(double degrees) {
    	timer.reset();
    	timer.start();
    	
    	// check if turning right if degrees is positive is right
    	while (timer.get() < Math.abs(degrees) * kDegrees) {
    		
    		if (degrees > 0) {
    			
    			Robot.drive.setLeftRightMotors(0.3, -0.3);
    			
    			//System.out.println("GO RIGHT");
    			
    		} else if (degrees < 0) {
    			
    			Robot.drive.setLeftRightMotors(-0.3, 0.3);
    			
    			//System.out.println("GO LEFT");
    		}
    	}
    	//System.out.println("I THINK IM DONE");
    	Robot.drive.stopDriveMotors();
    }
    
    public void stop() {
    	Robot.drive.stopDriveMotors();
    	offsetAngle = 0;
    }
    
    public void align() {
    	Robot.hardware.gyro.calibrate();
    }
    
    public double getGyro() {
    	return Robot.hardware.gyro.getAngle();
    }
    
    private double distanceFromY(double yCenter) {
    	return 2.2653197632351 * Math.pow(10, -4) * Math.pow(yCenter - Constants.Y_NOMINAL_8_FT, 2) + 0.03688336790171 * (yCenter - Constants.Y_NOMINAL_8_FT) +  1.516 * Math.pow(10, -13);
    }
    
    private double angleFromDeltaX(double xOffset2) {
    	return xOffset2 * Constants.X_MAX_ANGLE / Constants.X_MAX_DIFFERENCE;
    }
    
    public void offsetFromContours() {
    	offsetAngle = 0;
    	System.out.println("TABLES GET GO");
    	table = NetworkTable.getTable("GRIP");
    	System.out.println("TABLESGET");
    	double[] defaultValue = new double[0];
    	double[] centerX;
    	double[] centerY;
    	double[] areas;
    	double[] widths;
    	double[] heights;
    	int indexOfMaxArea = 0;
    	double maxArea = 0;
    	double contourCenterX;
    	double contourCenterY;
    	double contourX;
    	double contourY;
    	
    	centerX = table.getNumberArray("shooterContours/centerX", defaultValue);
		centerY = table.getNumberArray("shooterContours/centerY", defaultValue);
		areas = table.getNumberArray("shooterContours/area", defaultValue);
		heights = table.getNumberArray("shooterContours/height", defaultValue);
		widths = table.getNumberArray("shooterContours/width", defaultValue);
		maxArea = 0;
		SmartDashboard.putString("PLEASE", "YAYYYY");
		if (areas.length == 0) {
			indexOfMaxArea = 0;
			System.out.println("NO CONTOURS!");
			offsetAngle = 0;
			// Resets Index because for loop doesn't run 
			return;
		}
		
		for (int g = 0; g < areas.length; g++) {
			if (areas[g] > maxArea) {
				maxArea = areas[g];
				indexOfMaxArea = g;
				System.out.println("More than max area");
			} else {
				System.out.println("Less than max area");
			}
		}
		System.out.println("DONE");
		
		//System.out.println(areas.length);
		//indexOfMaxArea = 1;
		if (centerX.length == 0) {
			System.out.println("NO CONTOURS!!!");
			return;
		}
		contourCenterX = centerX[indexOfMaxArea];
		contourCenterY = centerY[indexOfMaxArea];
		//contourY = heights[indexOfMaxArea];
		//contourX = widths[indexOfMaxArea];
		
		offsetAngle = angleFromDeltaX(contourCenterX - Constants.X_NOMINAL_8_FT); //check if going right is positive offsetAngle
		distance = distanceFromY(contourCenterY);
		SmartDashboard.putNumber("Offset Angle", offsetAngle);
		// angle calc.
		// dist. calc.
		// END BEFORE ROTATING
    }
}

package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.lib.ShooterMotorGroup;
import org.usfirst.frc.team5026.robot.autonomous.CrossLowBarAutonomous;
import org.usfirst.frc.team5026.robot.autonomous.DoNothingAutonomous;
import org.usfirst.frc.team5026.robot.autonomous.SpyBotAutonomous;
import org.usfirst.frc.team5026.robot.subsystems.Drive;
import org.usfirst.frc.team5026.robot.subsystems.IntakeArm;
import org.usfirst.frc.team5026.robot.subsystems.Shooter;
import org.usfirst.frc.team5026.robot.subsystems.StageTwo;

import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Hardware hardware;
	public static Drive drive;
	public static IntakeArm intakeArm;
	public static StageTwo stageTwo;
	public static Shooter shooter;

    Command autonomousCommand;
    SendableChooser autonomousChooser;

	public int[] lookupU = {
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0
	};
	public int[] lookupL = {
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0
	};
	
	public static PIDController motorPID1;
	public static PIDController motorPID2;
	
	public int shooterIsNegative = -1;
	public static int rpmUpperBatter = -3200;
	public static int rpmLowerBatter = -4700;
	public static int rpmUpperShooter = -4800;
	public static int rpmLowerShooter = -3200;
	NetworkTable table;
	
	double[] defaultValue = new double[0];
	public double[] centerX;
	public double[] centerY;
	public double[] areas;
	public double[] widths;
	public double[] heights;
	public double contourCenterX;
	public double contourCenterY;
	public double contourX;
	public double contourY;
	public int indexOfMaxArea = 0;
	// dist.
	public double distance;
	public double xOffset;
	public double yOffset;
	String sr = "";
	
	double maxArea = 0;
	public static boolean canDrive = true;

	int arrayNum = 0;
	//distance = 91 inches: 4000, 4000; distance = 76 inches: 4000, 4000;
	// SPY BOX: 123 INCHES 4600 UPPER, 3200 LOWER
	// SAFE SHOT: 143 INCHES 4800 UPPER, 3200 LOWER
	// SAFE SHOT: 126 INCHES 4800 UPPER, 3200 LOWER
	
	private void setupTwoGroup(ShooterMotorGroup group, boolean reverse, boolean upper){
    	group.motor1.configNominalOutputVoltage(+0.0f, -0.0f);
    	group.motor1.configPeakOutputVoltage(+12.0f, -12.0f);
    	group.motor1.setProfile(Constants.SHOOTER_PROFILE);
    	if (upper) {
    		group.motor1.setF(Constants.UPPER_SHOOTER_F);
        	group.motor1.setP(Constants.UPPER_SHOOTER_P);
        	group.motor1.setI(Constants.UPPER_SHOOTER_I);
        	group.motor1.setD(Constants.UPPER_SHOOTER_D);
    	} else {
    		group.motor1.setF(Constants.LOWER_SHOOTER_F);
        	group.motor1.setP(Constants.LOWER_SHOOTER_P);
        	group.motor1.setI(Constants.LOWER_SHOOTER_I);
        	group.motor1.setD(Constants.LOWER_SHOOTER_I);
    	}
    	// Sec. 17.2.3 (Software Reference Manual)
    	group.motor1.reverseSensor(reverse);
    	group.motor1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	group.motor1.configEncoderCodesPerRev(1024); // 1/4 * 4096 (CAUSE 4096 IS THE NUMBER OF TICKS PER REV. MEASURED. F U MANUAL (check position changes (big number in selftest))
    	group.motor1.setPosition(0);
    	group.motor1.setForwardSoftLimit(+15.0);
    	group.motor1.setReverseSoftLimit(-15.0);
		group.motor1.changeControlMode(TalonControlMode.Speed);
    	
    	// Sec. 12.1.3 (Software Reference Manual)
    	//group.motorController1.setPID(akbar.p, akbar.i, akbar.d, akbar.f, akbar.izone, akbar.ramprate, akbar.profile);
    	
    	group.motor2.configNominalOutputVoltage(+0.0f, 0.0f);
    	group.motor2.configPeakOutputVoltage(+12.0f, 12.0f);
    	group.motor2.setProfile(Constants.SHOOTER_PROFILE);
    	group.motor2.setForwardSoftLimit(+15.0);
    	group.motor2.setReverseSoftLimit(15.0);
    	//group.motorController2.changeControlMode(TalonControlMode.PercentVbus);
    	group.motor2.changeControlMode(TalonControlMode.Follower);
    	group.motor2.reverseOutput(false);
    	group.motor2.set(group.motor1.getDeviceID());
    }
	
	private double distanceFromY(double yCenter) {
    	return 2.2653197632351 * Math.pow(10, -4) * Math.pow(yCenter - Constants.Y_NOMINAL_8_FT, 2) + 0.03688336790171 * (yCenter - Constants.Y_NOMINAL_8_FT) +  1.516 * Math.pow(10, -13);
	}
	
	private double angleFromDeltaX(double xOffset2) {
    	return xOffset2 * Constants.X_MAX_ANGLE / Constants.X_MAX_DIFFERENCE;
    }
	private void cameraPrintContourCenters() {
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
			// Resets Index because for loop doesn't run 
			return;
		}
		
		for (int g = 0; g < areas.length; g++) {
			if (areas[g] > maxArea) {
				maxArea = areas[g];
				indexOfMaxArea = g;
			}
		}
		
		//System.out.println(areas.length);
		//indexOfMaxArea = 1;
		if (centerX.length == 0) {
			System.out.println("NO CONTOURS!!!");
			return;
		}
		contourCenterX = centerX[indexOfMaxArea];
		contourCenterY = centerY[indexOfMaxArea];
		contourY = heights[indexOfMaxArea];
		contourX = widths[indexOfMaxArea];
		//System.out.println(contourCenterX);
		//System.out.println(contourCenterY);
		SmartDashboard.putNumber("Contour Center X", contourCenterX);
		SmartDashboard.putNumber("Contour Center Y", contourCenterY);
		SmartDashboard.putString("PLEASE", "yes 1");
		yOffset = contourCenterY - Constants.Y_NOMINAL_8_FT;
		xOffset = contourCenterX - Constants.X_NOMINAL_8_FT;
		distance = distanceFromY(contourCenterY);
		SmartDashboard.putString("PLEASE", "yes 2");
		
		sr = "";
		sr += "Deltas: (";
		sr += xOffset;
		sr += ", ";
		sr += yOffset;
		sr += "), Size: (";
		sr += contourX;
		sr += ", ";
		sr += contourY;
		sr += "), Pos: (";
		sr += contourCenterX;
		sr += ", ";
		sr += contourCenterY;
		sr += "), Dist. Angle: (";
		SmartDashboard.putString("PLEASE", "yes 3");
		sr += distance + Constants.NOMINAL_FEET;
		sr += ", ";
		sr += angleFromDeltaX(xOffset);
		sr += "), Gyro: ";
		sr += hardware.gyro.getAngle();
		SmartDashboard.putString("PLEASE", "yes 4");
		
		// we need to add distance calculation from from centerX
		// also add rotate approximately (with degrees)
		//rotate.align(angleFromDeltaX(xOffset));
		
		System.out.println(sr);
		
		if(Constants.Y_THRESHOLD_LOW <= yOffset && yOffset <= Constants.Y_THRESHOLD_HIGH)
		{
			return;
			//System.out.println("shoot");
		}
		Timer.delay(1);
		SmartDashboard.putString("PLEASE", "yes 5");
		//if negative,  right too much
		//if positive, left too much
		//different scale for left & right
		
		//shootable range, -16 to 20
		
		//camera right back 1 inch & left forward 1 inch: -54
		//camera right back .5 in & left forward .5 in: -26
		
		//camera left back 1 in & right forward 1 in: 36
		//camera left back .5 in & right forward .5 in: 20
		
		/*
		At robot front distance of 156 in. to target front:
		Center Y can be used to tell the angle relative to optimal shot
		*/
	}
    
    public void robotInit() {
		oi = new OI();
		hardware = new Hardware();
		drive = new Drive();
		intakeArm = new IntakeArm();
		stageTwo = new StageTwo();
		shooter = new Shooter(lookupU, lookupL);
		setupTwoGroup(hardware.lowerShooterGroup, true, false);
		setupTwoGroup(hardware.upperShooterGroup, true, true);
		NetworkTable.setServerMode();
		
        autonomousChooser = new SendableChooser();
        autonomousChooser.addDefault("Do Nothing", new DoNothingAutonomous());
        autonomousChooser.addObject("Cross Low Bar", new CrossLowBarAutonomous());
        autonomousChooser.addObject("Spy Box Shot", new SpyBotAutonomous());
        SmartDashboard.putData("Autonomous Selector", autonomousChooser);
        
    	for (int i = 0; i < lookupU.length; i++) {
    		if (lookupU[i] == 0) {
    			lookupU[i] = (Constants.SHOOTER_MAX_RPM * shooterIsNegative * (i + 1)) / lookupU.length;
    		}
    	}
    	for (int j = 0; j < lookupL.length; j++) {
    		if (lookupL[j] == 0) {
    			lookupL[j] = (Constants.SHOOTER_MAX_RPM * shooterIsNegative * (j + 1)) / lookupL.length;
    		}
    	}
    	
    	// this makes the shooter lookup tables have values that increase by a certain ratio,
    	// where the 30th value of the array is max speed (in rpms) {14000}
    	// and the 0th value is 0 rpm
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	shooter.stop();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) autonomousChooser.getSelected();
        
		String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "Cross Low Bar":
			autonomousCommand = new CrossLowBarAutonomous();
			break;
		case "Spy Box Shot":
			autonomousCommand = new SpyBotAutonomous();
		default:
			autonomousCommand = new DoNothingAutonomous();
			break;
		}
    	
		if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}

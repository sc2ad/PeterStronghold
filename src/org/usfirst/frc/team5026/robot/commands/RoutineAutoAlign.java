package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RoutineAutoAlign extends CommandGroup {
    
    public RoutineAutoAlign() {
    	double speed = 0.3;
    	addSequential(new VisionAngleCalculation());
    	//Robot.rotate.offsetAngle = 30;
        //addSequential(new VisionAngleRotation(speed));
    	addSequential(new VisionAngleCoarseRotation());
    }
}
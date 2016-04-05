package org.usfirst.frc.team5026.robot.autonomous;

import org.usfirst.frc.team5026.robot.commands.DriveFowardsForTime;
import org.usfirst.frc.team5026.robot.commands.IntakeArmLower;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Lowers intake arm and crosses low bar
 */

public class CrossLowBarAutonomous extends CommandGroup {
    
    public CrossLowBarAutonomous() {
    	addSequential(new IntakeArmLower());
    	addSequential(new DriveFowardsForTime(2));
    	addSequential(new DriveFowardsForTime(-2));
    	addSequential(new DriveFowardsForTime(2));
    }
}
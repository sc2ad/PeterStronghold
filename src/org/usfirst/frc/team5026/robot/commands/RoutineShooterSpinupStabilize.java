package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Handles spinup and PID stabilization for shooter
 */
public class RoutineShooterSpinupStabilize extends CommandGroup {
    
    public RoutineShooterSpinupStabilize() {
    	addSequential(new ShooterShootRPM(Robot.rpmUpperShooter, Robot.rpmLowerShooter));
        addSequential(new ShooterWaitForStabilize());
    }
}

package org.usfirst.frc.team5026.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RoutineShootBall extends CommandGroup {
    
    public  RoutineShootBall() {
    	addSequential(new RoutineShooterSpinupStabilize());
    	addSequential(new StageTwoQueueToShooter());
    	addSequential(new ShooterSlowStop());
    }
}

package org.usfirst.frc.team5026.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RoutineOuttakeBall extends CommandGroup {
    
    public RoutineOuttakeBall() {
    	addParallel(new StageTwoOuttake());
    	addParallel(new IntakeRollerSpinOut());
    }
}

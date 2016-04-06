package org.usfirst.frc.team5026.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Intakes with rollers and stage two until banner sensor has been triggered. Then pulses ball back.
 */

public class RoutineIntakeBall extends CommandGroup {
    
    public RoutineIntakeBall() {
    	addSequential(new IntakeRollerSpinIn());
    	addSequential(new StageTwoIntake());
    	addSequential(new StageTwoPulseBack());
    	addSequential(new IntakeRollerStop());
    }
}

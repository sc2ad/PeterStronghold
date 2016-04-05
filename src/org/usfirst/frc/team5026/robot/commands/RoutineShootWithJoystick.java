package org.usfirst.frc.team5026.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Shoots with a joystick button
 */
public class RoutineShootWithJoystick extends CommandGroup {
    
    public RoutineShootWithJoystick() {
        addSequential(new RoutineShooterSpinupStabilize());
        addSequential(new ShooterWaitForButton(1));
        addSequential(new ShooterQueue());
        addSequential(new ShooterSlowStop());
    }
}

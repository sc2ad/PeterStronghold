package org.usfirst.frc.team5026.robot.autonomous;

import org.usfirst.frc.team5026.robot.commands.RoutineShootBall;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SpyBotAutonomous extends CommandGroup {
    
    public  SpyBotAutonomous() {
        addSequential(new RoutineShootBall()); 
    }
}

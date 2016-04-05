package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeArmLower extends Command {

    public IntakeArmLower() {
    	requires(Robot.intakeArm);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	Robot.intakeArm.extendIntakeArm();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

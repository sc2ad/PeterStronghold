package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeRollerStop extends Command {

    public IntakeRollerStop() {
    	requires(Robot.intakeArm);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.intakeArm.stopIntake();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

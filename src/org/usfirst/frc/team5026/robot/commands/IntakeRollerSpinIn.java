package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeRollerSpinIn extends Command {

    public IntakeRollerSpinIn() {
    	requires(Robot.intakeArm);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.intakeArm.intakeBall();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

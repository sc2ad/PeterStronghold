package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeRollerSpinOut extends Command {

    public IntakeRollerSpinOut() {
    	requires(Robot.intakeArm);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.intakeArm.outtakeBall();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	Robot.intakeArm.stopIntake();
    }

    protected void interrupted() {
    }
}

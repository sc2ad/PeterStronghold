package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeArmRaise extends Command {

    public IntakeArmRaise() {
    	requires(Robot.intakeArm);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.intakeArm.retractIntakeArm();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

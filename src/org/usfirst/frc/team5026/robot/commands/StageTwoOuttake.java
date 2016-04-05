package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StageTwoOuttake extends Command {

    public StageTwoOuttake() {
    	requires(Robot.stageTwo);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.stageTwo.outtakeBall();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	Robot.stageTwo.stopMotors();
    }

    protected void interrupted() {
    	end();
    }
}

package org.texastorque.texastorque20155.auto;

import org.texastorque.texastorque20155.output.Output;

public abstract class AutoMode implements Runnable {

    protected Output output;//ONLY add if dead reckoning is needed
    //values
    protected double elevatorSetpoint;
    protected double drivebaseSetpoint;

    protected volatile double leftIntakeSpeed;
    protected double rightIntakeSpeed;

    protected boolean canHeld;
    protected boolean tailDown;

    //override values
    protected boolean override = false;

    protected double elevatorSpeed;

    protected double leftDriveSpeed;
    protected double rightDriveSpeed;

    public AutoMode() {
        output = Output.getInstance();
    }

    //get values
    public double getElevatorSetpoint() {
        return elevatorSetpoint;
    }

    public double getDrivebaseSetpoint() {
        return drivebaseSetpoint;
    }

    public synchronized double getLeftIntakeSpeed() {
        return leftIntakeSpeed;
    }

    public double getRightIntakeSpeed() {
        return rightIntakeSpeed;
    }

    public boolean isCanHeld() {
        return canHeld;
    }

    public boolean isTailDown() {
        return tailDown;
    }

    //get override values
    public boolean inOverride() {
        return override;
    }

    public double getElevatorOverrideSpeed() {
        return elevatorSpeed;
    }

    public double getLeftDriveOverrideSpeed() {
        return leftDriveSpeed;
    }

    public double getRightDriveOverrideSpeed() {
        return rightDriveSpeed;
    }
}

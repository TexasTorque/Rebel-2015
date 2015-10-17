package org.texastorque.texastorque20155.input;

public abstract class Input {

    protected double elevatorSetpoint = 15;
    protected double drivebaseSetpoint;
    protected double drivebaseAngularSetpoint;

    protected double leftIntakeSpeed;
    protected double rightIntakeSpeed;

    protected boolean stackStabilized = false;
    protected boolean overrideStabilized = false;
    protected boolean tailDown = false;
    protected boolean autoStackMode = false;
    protected boolean autoStackPlace = false;

    //override values
    protected boolean override = false;

    protected double elevatorSpeed;

    protected double leftDriveSpeed;
    protected double rightDriveSpeed;
    protected double placeDriveSpeed;

    //load parameters for input
    public abstract void loadParams();

    //update input
    public abstract void update();

    public double getElevatorSetpoint() {
        return elevatorSetpoint;
    }

    public double getDrivebaseSetpoint() {
        return drivebaseSetpoint;
    }

    public double getDrivebaseAngularSetpoint() {
        return drivebaseAngularSetpoint;
    }

    public double getLeftIntakeSpeed() {
        return leftIntakeSpeed;
    }

    public double getRightIntakeSpeed() {
        return rightIntakeSpeed;
    }

    public boolean isStackStabilized() {
        return stackStabilized;
    }

    public void passOverrideStabilized(boolean overrideStab) {//ewwww
        overrideStabilized = overrideStab;
    }

    public boolean getOverrideStabilized() {
        return overrideStabilized;
    }

    public boolean getOverrideAutoStackPlace() {
        return autoStackPlace;
    }

    public boolean isTailDown() {
        return tailDown;
    }

    public boolean isAutoStackMode() {
        return autoStackMode;
    }

    public boolean isOverride() {
        return override;
    }

    public double getElevatorSpeed() {
        return elevatorSpeed;
    }

    public double getLeftDriveSpeed() {
        return leftDriveSpeed;
    }

    public double getRightDriveSpeed() {
        return rightDriveSpeed;
    }

    public double getPlaceDriveSpeed() {
        return placeDriveSpeed;
    }
}

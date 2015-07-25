package org.texastorque.texastorque20155.auto;

public abstract class AutoMode implements Runnable {

    private final Thread autoThread;

//    protected Output output;  ONLY add if dead reckoning is needed
    //values
    protected double elevatorSetpoint;
    protected double drivebaseSetpoint;

    protected double leftIntakeSpeed;
    protected double rightIntakeSpeed;

    protected boolean canHeld;

    //override values
    protected boolean override = false;

    protected double elevatorSpeed;

    protected double leftDriveSpeed;
    protected double rightDriveSpeed;

    public AutoMode() {
        autoThread = AutoManager.getInstance().getAutoThread();
//        output = Output.getInstance();
    }

    //get values
    public double getElevatorSetpoint() {
        return elevatorSetpoint;
    }

    public double getDrivebaseSetpoint() {
        return drivebaseSetpoint;
    }

    public double getLeftIntakeSpeed() {
        return leftIntakeSpeed;
    }

    public double getRightIntakeSpeed() {
        return rightIntakeSpeed;
    }

    public boolean isCanHeld() {
        return canHeld;
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

    protected final void pause(double seconds) {
        try {
            autoThread.wait((long) (seconds / 1000.0));
        } catch (Exception e) {
        }
    }
}

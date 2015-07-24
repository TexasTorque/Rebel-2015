package org.texastorque.texastorque20155.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivebase extends Subsystem {

    private double leftSpeed;
    private double rightSpeed;

    //sensor
    private double leftPosition;
    private double rightPosition;

    private double leftVelocity;
    private double rightVelocity;

    private double leftAcceleration;
    private double rightAcceleration;

    private Drivebase() {
    }

    @Override
    public void run() {
        leftPosition = feedback.getLeftDrivePosition();
        rightPosition = feedback.getRightDrivePosition();

        leftVelocity = feedback.getLeftDriveVelocity();
        rightVelocity = feedback.getRightDriveVelocity();

        leftAcceleration = feedback.getLeftDriveAcceleration();
        rightAcceleration = feedback.getRightDriveAcceleration();

        if (input.isAutonomous()) {
            runAuto();
            output();
        } else if (input.isOperatorControlled()) {
            runTeleop();
            output();
        }
    }

    private void runAuto() {
    }

    private void runTeleop() {
        leftSpeed = input.getLeftDriveSpeed();
        rightSpeed = input.getRightDriveSpeed();
    }

    @Override
    protected void output() {
        output.setDriveSpeed(leftSpeed, rightSpeed);
    }

    @Override
    public void loadParams() {
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("Left Drive Speed", leftSpeed);
        SmartDashboard.putNumber("Right Drive Speed", rightSpeed);
    }

    @Override
    public void init() {
    }

    //singleton
    private static Drivebase instance;

    public static Drivebase getInstance() {
        if (instance == null) {
            instance = new Drivebase();
        }
        return instance;
    }
}

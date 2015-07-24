package org.texastorque.texastorque20155.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20155.constants.Constants;
import org.texastorque.torquelib.util.TorqueMathUtil;

public class Drivebase extends Subsystem {

    private double MAX_SPEED;

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
        leftSpeed = TorqueMathUtil.constrain(leftSpeed, MAX_SPEED);
        rightSpeed = TorqueMathUtil.constrain(rightSpeed, MAX_SPEED);
        output.setDriveSpeed(leftSpeed, rightSpeed);
    }

    @Override
    public void loadParams() {
        MAX_SPEED = Constants.D_MAX_SPEED.getDouble();
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("Left Drive Speed", leftSpeed);
        SmartDashboard.putNumber("Right Drive Speed", rightSpeed);

        SmartDashboard.putNumber("Left Drive Position", leftPosition);
        SmartDashboard.putNumber("Right Drive Position", rightPosition);

        SmartDashboard.putNumber("Left Drive Velocity", leftVelocity);
        SmartDashboard.putNumber("Right Drive Velocity", rightVelocity);

        SmartDashboard.putNumber("Left Drive Acceleration", leftAcceleration);
        SmartDashboard.putNumber("Right Drive Acceleration", rightAcceleration);
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

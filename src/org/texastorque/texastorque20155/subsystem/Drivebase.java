package org.texastorque.texastorque20155.subsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20155.constants.Constants;
import org.texastorque.torquelib.controlLoop.TorquePV;
import org.texastorque.torquelib.controlLoop.TorqueTMP;
import org.texastorque.torquelib.util.TorqueMathUtil;

public class Drivebase extends Subsystem {

    private double MAX_SPEED;

    private double MAX_VELOCITY;
    private double MAX_ACCELERATION;

    private double leftSpeed;
    private double rightSpeed;

    //sensor
    private double leftPosition;
    private double rightPosition;

    private double leftVelocity;
    private double rightVelocity;

    private double leftAcceleration;
    private double rightAcceleration;

    //profiling
    private TorqueTMP profile;
    private TorquePV leftPV;
    private TorquePV rightPV;

    private double targetPosition;
    private double targetVelocity;
    private double targetAcceleration;

    private double setpoint;
    private double previousSetpoint;

    private double prevTime;

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
        if (mode.inOverride()) {
            leftSpeed = mode.getLeftDriveOverrideSpeed();
            rightSpeed = mode.getRightDriveOverrideSpeed();
        } else {
            setpoint = mode.getDrivebaseSetpoint();

            if (setpoint != previousSetpoint) {
                previousSetpoint = setpoint;
                profile.generateTrapezoid(setpoint, 0.0, (leftSpeed + rightSpeed) / 2.0);

                feedback.resetDriveEncoders();
            }

            double dt = Timer.getFPGATimestamp() - prevTime;
            profile.calculateNextSituation(dt);
            prevTime = Timer.getFPGATimestamp();

            targetPosition = profile.getCurrentPosition();
            targetVelocity = profile.getCurrentVelocity();
            targetAcceleration = profile.getCurrentAcceleration();

            leftSpeed = leftPV.calculate(profile, leftPosition, leftVelocity);
            rightSpeed = leftPV.calculate(profile, rightPosition, rightVelocity);
        }
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

        MAX_VELOCITY = Constants.D_MAX_VELOCITY.getDouble();
        MAX_ACCELERATION = Constants.D_MAX_ACCELERATION.getDouble();

        leftPV.setGains(Constants.D_LEFT_PV_P.getDouble(),
                Constants.D_LEFT_PV_V.getDouble(),
                Constants.D_LEFT_PV_ffP.getDouble(),
                Constants.D_LEFT_PV_ffV.getDouble());
        leftPV.setTunedVoltage(Constants.TUNED_VOLTAGE.getDouble());
        rightPV.setGains(Constants.D_RIGHT_PV_P.getDouble(),
                Constants.D_RIGHT_PV_V.getDouble(),
                Constants.D_RIGHT_PV_ffP.getDouble(),
                Constants.D_RIGHT_PV_ffV.getDouble());
        rightPV.setTunedVoltage(Constants.TUNED_VOLTAGE.getDouble());
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
        profile = new TorqueTMP(MAX_VELOCITY, MAX_ACCELERATION);
        leftPV = new TorquePV();
        rightPV = new TorquePV();

        setpoint = 0.0;
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

package org.texastorque.texastorque20155.subsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20155.constants.Constants;
import org.texastorque.torquelib.controlLoop.TorquePV;
import org.texastorque.torquelib.controlLoop.TorqueTMP;
import org.texastorque.torquelib.util.TorqueMathUtil;

public class Elevator extends Subsystem {

    private double MAX_SPEED;

    private double MAX_VELOCITY;
    private double MAX_ACCELERATION;

    private double speed;

    private double position;
    private double velocity;
    private double accelertion;

    //complicated stuff
    private TorqueTMP profile;
    private TorquePV pv;

    private double targetPosition;
    private double targetVelocity;
    private double targetAcceleration;

    private double setpoint;
    private double previousSetpoint;

    private double prevTime;

    @Override
    public void run() {
        position = feedback.getElevatorPosition();
        velocity = feedback.getElevatorVelocity();
        accelertion = feedback.getElevatorAcceleration();

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
        if (input.inOverride()) {
            speed = input.getElevatorOverrideSpeed();
        } else {
            //get setpoint

            if (setpoint != previousSetpoint) {
                previousSetpoint = setpoint;
                profile.generateTrapezoid(setpoint, position, velocity);
            }

            double dt = Timer.getFPGATimestamp() - prevTime;
            profile.calculateNextSituation(Timer.getFPGATimestamp() - prevTime);
            prevTime = Timer.getFPGATimestamp();

            targetPosition = profile.getCurrentPosition();
            targetVelocity = profile.getCurrentVelocity();
            targetAcceleration = profile.getCurrentAcceleration();

            speed = pv.calculate(profile, position, velocity);
        }
    }

    @Override
    protected void output() {
        speed = TorqueMathUtil.constrain(speed, MAX_SPEED);
        output.setElevatorSpeed(speed);
    }

    @Override
    public void loadParams() {
        MAX_SPEED = Constants.E_MAX_SPEED.getDouble();

        MAX_VELOCITY = Constants.E_MAX_VELOCITY.getDouble();
        MAX_ACCELERATION = Constants.E_MAX_ACCELERATION.getDouble();

        pv.setGains(Constants.E_PV_P.getDouble(),
                Constants.E_PV_V.getDouble(),
                Constants.E_PV_ffP.getDouble(),
                Constants.E_PV_ffV.getDouble());
        pv.setTunedVoltage(Constants.TUNED_VOLTAGE.getDouble());
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("Elevator Speed", speed);
        SmartDashboard.putNumber("Elevator Position", position);
        SmartDashboard.putNumber("Elevator Velocity", velocity);
        SmartDashboard.putNumber("Elevator Acceleration", accelertion);

        SmartDashboard.putNumber("Elevator Target Position", targetPosition);
        SmartDashboard.putNumber("Elevator Target Velocity", targetVelocity);
        SmartDashboard.putNumber("Elevator Target Acceleration", targetAcceleration);
    }

    @Override
    public void init() {
        profile = new TorqueTMP(MAX_VELOCITY, MAX_ACCELERATION);
        pv = new TorquePV();

        setpoint = 0.0;
    }
}
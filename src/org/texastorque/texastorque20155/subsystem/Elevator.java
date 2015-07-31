package org.texastorque.texastorque20155.subsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20155.constants.Constants;
import org.texastorque.torquelib.controlLoop.TorquePV;
import org.texastorque.torquelib.controlLoop.TorqueTMP;
import org.texastorque.torquelib.util.TorqueMathUtil;

public class Elevator extends Subsystem {

    private double MAX_SPEED;

    private double speed;

    private double position;
    private double velocity;
    private double accelertion;

    //profiling
    private TorqueTMP profile;
    private TorquePV pv;

    private double targetPosition;
    private double targetVelocity;
    private double targetAcceleration;

    private double setpoint;
    private double previousSetpoint;

    private double prevTime;

    private Elevator() {
        pv = new TorquePV();
    }

    @Override
    public void run() {
        position = feedback.getElevatorPosition();
        velocity = feedback.getElevatorVelocity();
        accelertion = feedback.getElevatorAcceleration();

        if (input.isOverride()) {
            speed = input.getElevatorSpeed();
        } else {
            setpoint = input.getElevatorSetpoint();

            if (setpoint != previousSetpoint) {
                previousSetpoint = setpoint;
                profile.generateTrapezoid(setpoint, position, velocity);
            }

            double dt = Timer.getFPGATimestamp() - prevTime;
            profile.calculateNextSituation(dt);
            prevTime = Timer.getFPGATimestamp();

            targetPosition = profile.getCurrentPosition();
            targetVelocity = profile.getCurrentVelocity();
            targetAcceleration = profile.getCurrentAcceleration();

            speed = pv.calculate(profile, position, velocity);

            speed = TorqueMathUtil.constrain(speed, MAX_SPEED);
        }
        output();
    }

    @Override
    protected void output() {
        speed = TorqueMathUtil.constrain(speed, MAX_SPEED);
        output.setElevatorSpeed(speed);
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
        MAX_SPEED = Constants.E_MAX_SPEED.getDouble();

        profile = new TorqueTMP(Constants.E_MAX_VELOCITY.getDouble(),
                Constants.E_MAX_ACCELERATION.getDouble());
        position = feedback.getElevatorPosition();
        velocity = feedback.getElevatorVelocity();
        profile.generateTrapezoid(setpoint, position, speed);

        //setpoint = feedback.getElevatorPosition();

        pv.setGains(Constants.E_PV_P.getDouble(),
                Constants.E_PV_V.getDouble(),
                Constants.E_PV_ffP.getDouble(),
                Constants.E_PV_ffV.getDouble());
        pv.setTunedVoltage(Constants.TUNED_VOLTAGE.getDouble());

        prevTime = Timer.getFPGATimestamp();
    }

    //singleton
    private static Elevator instance;

    public static Elevator getInstance() {
        if (instance == null) {
            instance = new Elevator();
        }
        return instance;
    }
}

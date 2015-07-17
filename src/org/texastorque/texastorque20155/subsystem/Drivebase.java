package org.texastorque.texastorque20155.subsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20155.monitor.Monitor;
import org.texastorque.torquelib.controlLoop.TorquePV;
import org.texastorque.torquelib.controlLoop.TorqueTMP;
import org.texastorque.torquelib.util.TorqueUtil;

public class Drivebase {

    private Monitor monitor;

    private double leftSpeed;
    private double rightSpeed;

    private double lastTime;
    private TorqueTMP profile;
    private TorquePV leftPV;
    private TorquePV rightPV;

    public Drivebase() {
        monitor = Monitor.getInstance();

        profile = new TorqueTMP(10, 5);
        leftPV = new TorquePV();
        rightPV = new TorquePV();

        leftSpeed = 0.0;
        rightSpeed = 0.0;
    }

    public void setSetpoint(double setpoint) {
        profile.generateTrapezoid(setpoint, 0, (monitor.getLeftDriveVelocity() + monitor.getRightDriveVelocity()) / 2.0);
        monitor.reset();
        lastTime = Timer.getFPGATimestamp();
    }

    public void run() {
        profile.calculateNextSituation(Timer.getFPGATimestamp() - lastTime);
        leftSpeed = leftPV.calculate(profile, monitor.getLeftDrivePosition(), monitor.getLeftDriveVelocity());
        rightSpeed = rightPV.calculate(profile, monitor.getRightDrivePosition(), monitor.getRightDriveVelocity());
    }

    public void setSpeed(double left, double right) {
        leftSpeed = TorqueUtil.maxOne(left);
        rightSpeed = TorqueUtil.maxOne(right);
    }

    public void putToDashboard() {
        SmartDashboard.putNumber("Drivebase Left Percentage", leftSpeed);
        SmartDashboard.putNumber("Drivebase Right Percentage", rightSpeed);
        SmartDashboard.putNumber("TMP Position", profile.getCurrentPosition());
        SmartDashboard.putNumber("TMP Velocity", profile.getCurrentVelocity());
        SmartDashboard.putNumber("TMP Acceleration", profile.getCurrentAcceleration());
    }

    public void loadParams() {
    }

    public double getLeftSpeed() {
        return leftSpeed;
    }

    public double getRightSpeed() {
        return rightSpeed;
    }
}

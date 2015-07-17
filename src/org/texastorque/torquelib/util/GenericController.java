package org.texastorque.torquelib.util;

import edu.wpi.first.wpilibj.Joystick;

public class GenericController extends Joystick {

    private double deadband;

    public GenericController(int port, double deadband_) {
        super(port);
        deadband = deadband_;
    }

    public synchronized double getLeftYAxis() {
        return deadband(getRawAxis(2));
    }

    public synchronized double getLeftXAxis() {
        return deadband(getRawAxis(1));
    }

    public synchronized double getRightYAxis() {
        return deadband(getRawAxis(5));
    }

    public synchronized double getRightXAxis() {
        return deadband(getRawAxis(4));
    }

    public synchronized boolean getLeftDPAD() {
        return (getRawAxis(6) > 0.0);
    }

    public synchronized boolean getRightDPAD() {
        return (getRawAxis(6) < 0.0);
    }

    public synchronized boolean getLeftStickClick() {
        return getRawButton(9);
    }

    public synchronized boolean getRightStickClick() {
        return getRawButton(10);
    }

    public synchronized boolean getTopLeftBumper() {
        return getRawButton(5);
    }

    public synchronized boolean getTopRightBumper() {
        return getRawButton(6);
    }

    public synchronized boolean getBottomLeftBumper() {
        return (getRawAxis(3) > 0.2);
    }

    public synchronized boolean getBottomRightBumper() {
        return (getRawAxis(3) < -0.2);
    }

    public synchronized boolean getLeftCenterButton() {
        return getRawButton(7);
    }

    public synchronized boolean getRightCenterButton() {
        return getRawButton(8);
    }

    public synchronized boolean getLeftActionButton() {
        return getRawButton(3);
    }

    public synchronized boolean getTopActionButton() {
        return getRawButton(4);
    }

    public synchronized boolean getRightActionButton() {
        return getRawButton(2);
    }

    public synchronized boolean getBottomActionButton() {
        return getRawButton(1);
    }

    private double deadband(double value) {
        if (Math.abs(value) < deadband) {
            return 0.0;
        } else {
            return value;
        }
    }
}

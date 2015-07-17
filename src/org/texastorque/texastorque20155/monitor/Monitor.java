package org.texastorque.texastorque20155.monitor;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20155.constants.Ports;
import org.texastorque.torquelib.component.sensors.TorqueEncoder;

public class Monitor {

    private TorqueEncoder leftDriveEncoder;
    private TorqueEncoder rightDriveEncoder;

    private double leftDrivePosition;
    private double rightDrivePosition;
    private double leftDriveVelocity;
    private double rightDriveVelocity;

    private Monitor() {
        leftDriveEncoder = new TorqueEncoder(1, Ports.LEFT_DRIVE_ENCODER_A, 1, Ports.LEFT_DRIVE_ENCODER_B, false);
        rightDriveEncoder = new TorqueEncoder(1, Ports.RIGHT_DRIVE_ENCODER_A, 1, Ports.RIGHT_DRIVE_ENCODER_B, false);
        leftDrivePosition = 0.0;
        rightDrivePosition = 0.0;
    }

    public void update() {
        leftDriveEncoder.calc();
        rightDriveEncoder.calc();

        //calc pos and vel and acc
    }

    public double getLeftDrivePosition() {
        return leftDrivePosition;
    }

    public double getRightDrivePosition() {
        return rightDrivePosition;
    }

    public double getLeftDriveVelocity() {
        return leftDriveVelocity;
    }

    public double getRightDriveVelocity() {
        return rightDriveVelocity;
    }

    public void reset() {
        leftDriveEncoder.reset();
        rightDriveEncoder.reset();
    }

    public void putToDashboard() {
        SmartDashboard.putNumber("Left Drive Position", leftDrivePosition);
        SmartDashboard.putNumber("Right Drive Position", rightDrivePosition);
        SmartDashboard.putNumber("Left Drive Velocity", leftDriveVelocity);
        SmartDashboard.putNumber("Right Drive Rate", rightDriveVelocity);
    }

    //singleton
    private static Monitor instance;

    public static Monitor getInstance() {
        if (instance == null) {
            instance = new Monitor();
        }
        return instance;
    }
}

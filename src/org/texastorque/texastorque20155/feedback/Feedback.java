package org.texastorque.texastorque20155.feedback;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Feedback {

//    private TorqueEncoder leftDriveEncoder;
//    private TorqueEncoder rightDriveEncoder;

    private double leftDrivePosition;
    private double rightDrivePosition;
    private double leftDriveVelocity;
    private double rightDriveVelocity;

    private Feedback() {
//        leftDriveEncoder = new TorqueEncoder(1, Ports.LEFT_DRIVE_ENCODER_A, 1, Ports.LEFT_DRIVE_ENCODER_B, false);
//        rightDriveEncoder = new TorqueEncoder(1, Ports.RIGHT_DRIVE_ENCODER_A, 1, Ports.RIGHT_DRIVE_ENCODER_B, false);
        leftDrivePosition = 0.0;
        rightDrivePosition = 0.0;
    }

    public void update() {
//        leftDriveEncoder.calc();
//        rightDriveEncoder.calc();

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
//        leftDriveEncoder.reset();
//        rightDriveEncoder.reset();
    }

    public void putToDashboard() {
        SmartDashboard.putNumber("Left Drive Position", leftDrivePosition);
        SmartDashboard.putNumber("Right Drive Position", rightDrivePosition);
        SmartDashboard.putNumber("Left Drive Velocity", leftDriveVelocity);
        SmartDashboard.putNumber("Right Drive Rate", rightDriveVelocity);
    }

    //singleton
    private static Feedback instance;

    public static Feedback getInstance() {
        if (instance == null) {
            instance = new Feedback();
        }
        return instance;
    }
}

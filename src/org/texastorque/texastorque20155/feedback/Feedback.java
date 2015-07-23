package org.texastorque.texastorque20155.feedback;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import org.texastorque.texastorque20155.constants.Ports;
import org.texastorque.torquelib.component.TorqueEncoder;

public class Feedback {

    private TorqueEncoder elevatorEncoder;

    private TorqueEncoder leftDriveEncoder;
    private TorqueEncoder rightDriveEncoder;

    private double elevatorPosition;
    private double elevatorVelocity;
    private double elevatorAcceleration;

    private double leftPosition;
    private double leftVelocity;
    private double leftAcceleration;

    private double rightPosition;
    private double rightVelocity;
    private double rightAcceleration;

    private Feedback() {
        elevatorEncoder = new TorqueEncoder(Ports.ELEVATOR_ENCODER_A, Ports.ELEVATOR_ENCODER_B, false, EncodingType.k4X);

        leftDriveEncoder = new TorqueEncoder(Ports.LEFT_DRIVE_ENCODER_A, Ports.LEFT_DRIVE_ENCODER_B, false, EncodingType.k4X);
        rightDriveEncoder = new TorqueEncoder(Ports.RIGHT_DRIVE_ENCODER_A, Ports.RIGHT_DRIVE_ENCODER_B, false, EncodingType.k4X);
    }

    public void update() {
    }

    public void pushToDashboard() {
    }

    //getters
    public double getElevatorPosition() {
        return elevatorPosition;
    }

    public double getElevatorVelocity() {
        return elevatorVelocity;
    }

    public double getElevatorAcceleration() {
        return elevatorAcceleration;
    }

    public double getLeftPosition() {
        return leftPosition;
    }

    public double getLeftVelocity() {
        return leftVelocity;
    }

    public double getLeftAcceleration() {
        return leftAcceleration;
    }

    public double getRightPosition() {
        return rightPosition;
    }

    public double getRightVelocity() {
        return rightVelocity;
    }

    public double getRightAcceleration() {
        return rightAcceleration;
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

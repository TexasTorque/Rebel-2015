package org.texastorque.texastorque20155.subsystem;

public class Intake {

    private double leftIntakeSpeed;
    private double rightIntakeSpeed;

    public Intake() {
        leftIntakeSpeed = 0.0;
        rightIntakeSpeed = 0.0;
    }
    
    public void setSpeed(double left, double right) {
        leftIntakeSpeed = left;
        rightIntakeSpeed = right;
    }

    public double getLeftIntakeSpeed() {
        return leftIntakeSpeed;
    }

    public double getRightIntakeSpeed() {
        return rightIntakeSpeed;
    }
}

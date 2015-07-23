package org.texastorque.texastorque20155.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends Subsystem {

    private double leftSpeed;
    private double rightSpeed;

    @Override
    public void loadParams() {
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("Intake Left Speed", leftSpeed);
        SmartDashboard.putNumber("Intake Right Speed", rightSpeed);
    }

    @Override
    public void init() {
    }

    @Override
    public void run() {
        if (input.isActive()) {
            if (input.isAutonomous()) {
                runAuto();
                output();
            } else if (input.isOperatorControlled()) {
                runTeleop();
                output();
            }
        }
    }

    private void runAuto() {
    }

    private void runTeleop() {
        leftSpeed = input.getLeftIntakeSpeed();
        rightSpeed = input.getRightIntakeSpeed();
    }

    @Override
    protected void output() {
        output.setIntakeSpeed(leftSpeed, rightSpeed);
    }

    //singleton
    private static Intake instance;

    public static Intake getInstance() {
        if (instance == null) {
            instance = new Intake();
        }
        return instance;
    }
}

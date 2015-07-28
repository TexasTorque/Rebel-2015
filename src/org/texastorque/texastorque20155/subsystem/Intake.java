package org.texastorque.texastorque20155.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20155.constants.Constants;
import org.texastorque.torquelib.util.TorqueMathUtil;

public class Intake extends Subsystem {

    private double MAX_SPEED;

    private double leftSpeed;
    private double rightSpeed;

    private Intake() {
    }

    @Override
    public void run() {
        leftSpeed = input.getLeftIntakeSpeed();
        rightSpeed = input.getRightIntakeSpeed();
        output();
    }

    @Override
    protected void output() {
        leftSpeed = TorqueMathUtil.constrain(leftSpeed, MAX_SPEED);
        rightSpeed = TorqueMathUtil.constrain(rightSpeed, MAX_SPEED);
        output.setIntakeSpeed(leftSpeed, rightSpeed);
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("Intake Left Speed", leftSpeed);
        SmartDashboard.putNumber("Intake Right Speed", rightSpeed);
    }

    @Override
    public void init() {
        MAX_SPEED = Constants.I_MAX_SPEED.getDouble();
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

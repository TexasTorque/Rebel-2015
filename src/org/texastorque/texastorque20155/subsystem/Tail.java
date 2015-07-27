package org.texastorque.texastorque20155.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Tail extends Subsystem {

    private boolean down;

    private Tail() {
    }

    @Override
    public void run() {
        if (input.isAutonomous()) {
            runAuto();
            output();
        } else if (input.isOperatorControlled()) {
            runTeleop();
            output();
        }
    }

    private void runAuto() {
        down = mode.isTailDown();
    }

    private void runTeleop() {
        down = input.getTailDown();
    }

    @Override
    protected void output() {
        output.setTail(down);
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putBoolean("Tail Down", down);
    }

    @Override
    public void init() {
    }

    @Override
    public void loadParams() {
    }

    //singleton
    private static Tail instance;

    public static Tail getInstance() {
        if (instance == null) {
            instance = new Tail();
        }
        return instance;
    }
}

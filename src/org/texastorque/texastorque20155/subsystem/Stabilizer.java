package org.texastorque.texastorque20155.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Stabilizer extends Subsystem {

    private boolean stabilizing;

    private Stabilizer() {
    }

    @Override
    public void run() {
        stabilizing = input.isStackStabilized();
        output();
    }

    @Override
    protected void output() {
        output.setStabilizer(stabilizing);
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putBoolean("CanHolder", stabilizing);
    }

    @Override
    public void init() {
    }

    //singleton
    private static Stabilizer instance;

    public static Stabilizer getInstance() {
        if (instance == null) {
            instance = new Stabilizer();
        }
        return instance;
    }
}

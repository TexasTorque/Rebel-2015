package org.texastorque.texastorque20155.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CanHolder extends Subsystem {

    private boolean holding;

    private CanHolder() {
    }

    @Override
    public void run() {
        holding = input.isCanHeld();
        output();
    }

    @Override
    protected void output() {
        output.setCanHolder(holding);
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putBoolean("Can Holder", holding);
    }

    @Override
    public void init() {
    }

    //singleton
    private static CanHolder instance;

    public static CanHolder getInstance() {
        if (instance == null) {
            instance = new CanHolder();
        }
        return instance;
    }
}

package org.texastorque.texastorque20155.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Not118 extends Subsystem {
//this is not 118 at all -Crispy
//lies, these are all lies! -Mason

    private boolean down;

    @Override
    public void run() {
        down = input.isTailDown();
        output();
    }

    @Override
    protected void output() {
        output.setCanRake(down);
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putBoolean("Tail Down", down);
    }

    @Override
    public void init() {
    }

    //singleton
    private static Not118 instance;

    public static Not118 getInstance() {
        if (instance == null) {
            instance = new Not118();
        }
        return new Not118();
    }
}

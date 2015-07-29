package org.texastorque.texastorque20155.auto;

public abstract class AutoCommand {

    public AutoCommand() {
    }

    public abstract void run();

    protected final boolean close(double value1, double value2) {
        return Math.abs(value1 - value2) < 0.05;
    }
}

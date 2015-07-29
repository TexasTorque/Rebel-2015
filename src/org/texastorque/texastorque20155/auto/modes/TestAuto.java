package org.texastorque.texastorque20155.auto.modes;

public class TestAuto extends AutoMode {

    @Override
    public void run() {
        runCommand(new DriveCommand(7.5));
    }
}

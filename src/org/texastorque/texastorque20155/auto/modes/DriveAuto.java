package org.texastorque.texastorque20155.auto.modes;

public class DriveAuto extends AutoMode {

    @Override
    public void run() {
        runCommand(new DriveCommand(2.0 * 12.0));
    }
}

package org.texastorque.texastorque20155.auto.modes;

public class DriveAuto extends AutoMode {

    @Override
    public void run() {
        drivebaseSetpoint = 6.0 * 12.0;
    }
}

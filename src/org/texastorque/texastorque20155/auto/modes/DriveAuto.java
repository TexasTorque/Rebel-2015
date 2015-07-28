package org.texastorque.texastorque20155.auto.modes;

public class DriveAuto extends AutoMode {

    @Override
    public void run() {
        override = false;
        drivebaseSetpoint = 6.0 * 12.0;

        pause(2000);
    }
}

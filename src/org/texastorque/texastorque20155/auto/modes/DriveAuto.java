package org.texastorque.texastorque20155.auto.modes;

import org.texastorque.texastorque20155.auto.AutoMode;

public class DriveAuto extends AutoMode {

    @Override
    public void run() {
        output.setDriveSpeed(1.0, 1.0);
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }
        output.setDriveSpeed(0.0, 0.0);
    }
}

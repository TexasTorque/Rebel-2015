package org.texastorque.texastorque20155.auto.modes;

import org.texastorque.texastorque20155.auto.AutoMode;

public class DriveAuto extends AutoMode {

    @Override
    public void run() {
        drivebaseSetpoint = 10.0;
    }
}

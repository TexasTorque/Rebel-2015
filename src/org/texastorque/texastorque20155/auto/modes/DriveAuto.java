package org.texastorque.texastorque20155.auto.modes;

import org.texastorque.texastorque20155.auto.AutoMode;

public class DriveAuto extends AutoMode {

    @Override
    public void run() {
        drivebaseSetpoint = 24.0;
    }

    //singleton
    private static DriveAuto instance;

    public static DriveAuto getInstance() {
        if (instance == null) {
            instance = new DriveAuto();
        }
        return instance;
    }
}

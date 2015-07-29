package org.texastorque.texastorque20155.auto.modes;

import org.texastorque.texastorque20155.constants.Constants;

public class ThreeToteAuto extends AutoMode {

    @Override
    public void run() {
        elevatorSetpoint = Constants.E_UP_POSITION.getDouble();
        pause(0.5);
        drivebaseSetpoint = 24.0;
        leftIntakeSpeed = 1.0;
        rightIntakeSpeed = 1.0;
    }

}

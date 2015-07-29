package org.texastorque.texastorque20155.auto.modes;

import org.texastorque.texastorque20155.constants.Constants;

public class ThreeToteAuto extends AutoMode {

    @Override
    public void run() {
        elevatorSetpoint = Constants.E_UP_POSITION.getDouble();
        pause(0.5);
        drivebaseSetpoint = 10.0;
        leftIntakeSpeed = 1.0;
        rightIntakeSpeed = 1.0;
        pause(0.5);
        elevatorSetpoint = Constants.E_DOWN_POSITION.getDouble();
        pause(0.5);
        leftIntakeSpeed = 0.0;
        rightIntakeSpeed = 0.0;
        //turn
        //drive
        //turn
        //instake/stack
        //turn
        //drive
        //turn
        //drive
        //intake/stack
        //back up into auto zone
        //place and back up
    }

}

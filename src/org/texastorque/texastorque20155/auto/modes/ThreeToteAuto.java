package org.texastorque.texastorque20155.auto.modes;

import org.texastorque.texastorque20155.constants.Constants;

public class ThreeToteAuto extends AutoMode {

    @Override
    public void run() {
        stackStabilized = true;
        runCommand(new ElevatorCommand(Constants.E_DOWN_POSITION.getDouble()));
        leftIntakeSpeed = 0.0;
        rightIntakeSpeed = 0.0;
        runCommand(new ElevatorCommand(Constants.E_UP_POSITION.getDouble()));
        leftIntakeSpeed = -1.0;
        rightIntakeSpeed = 1.0;
        runCommand(new DriveCommand(43.0));
        leftIntakeSpeed = 1.0;
        rightIntakeSpeed = 1.0;
        runCommand(new DriveCommand(27.0));
        runCommand(new ElevatorCommand(Constants.E_DOWN_POSITION.getDouble()));
        leftIntakeSpeed = 0.0;
        rightIntakeSpeed = 0.0;
        runCommand(new ElevatorCommand(Constants.E_UP_POSITION.getDouble()));
        leftIntakeSpeed = -1.0;
        rightIntakeSpeed = 1.0;
        runCommand(new DriveCommand(46.0));
        leftIntakeSpeed = 1.0;
        rightIntakeSpeed = 1.0;
        runCommand(new DriveCommand(36.0));
        runCommand(new ElevatorCommand(Constants.E_DOWN_POSITION.getDouble()));
        leftIntakeSpeed = 0.0;
        rightIntakeSpeed = 0.0;
        runCommand(new ElevatorCommand(Constants.E_UP_POSITION.getDouble()));
        override = true;
        leftDriveSpeed = -0.5;
        rightDriveSpeed = 0.5;
        pause(2.0);
        leftDriveSpeed = -0.5;
        rightDriveSpeed = -0.5;
        pause(1.0);
        leftDriveSpeed = 0.0;
        rightDriveSpeed = 0.0;
        stackStabilized = false;
        runCommand(new ElevatorCommand(Constants.E_DOWN_POSITION.getDouble()));
        leftDriveSpeed = -0.5;
        rightDriveSpeed = -0.5;
        pause(1.0);
        leftDriveSpeed = 0.0;
        rightDriveSpeed = 0.0;
    }

}

package org.texastorque.texastorque20155.input;

import org.texastorque.texastorque20155.constants.Constants;
import org.texastorque.torquelib.util.GenericController;

public class HumanInput extends Input {

    private double X_DRIVE_MULTIPLIER;
    private double Y_DRIVE_MULTIPLIER;

    private GenericController driver;
    private GenericController operator;

    public HumanInput() {
        driver = new GenericController(0, GenericController.TYPE_XBOX, 0.12);
        operator = new GenericController(1, GenericController.TYPE_XBOX, 0.12);
    }

    @Override
    public void loadParams() {
        X_DRIVE_MULTIPLIER = Constants.XBOX_X_DRIVE_MULTIPLIER.getDouble();
        Y_DRIVE_MULTIPLIER = Constants.XBOX_Y_DRIVE_MULTIPLIER.getDouble();
    }

    @Override
    public void update() {
        if (operator.getLeftCenterButton()) {
            override = true;
        } else if (operator.getRightCenterButton()) {
            override = false;
        }

        leftDriveSpeed = -driver.getLeftYAxis() * Y_DRIVE_MULTIPLIER + driver.getRightXAxis() * X_DRIVE_MULTIPLIER;
        rightDriveSpeed = -driver.getLeftYAxis() * Y_DRIVE_MULTIPLIER - driver.getRightXAxis() * X_DRIVE_MULTIPLIER;

        leftIntakeSpeed = operator.getLeftYAxis() - operator.getLeftXAxis();
        rightIntakeSpeed = operator.getLeftYAxis() + operator.getLeftXAxis();

        elevatorSpeed = -operator.getRightYAxis();
        if (operator.getYButton()) {
            elevatorSetpoint = Constants.E_UP_POSITION.getDouble();
        } else if (operator.getBButton()) {
            elevatorSetpoint = Constants.E_DOWN_POSITION.getDouble();
        }

        tailDown = operator.getLeftBumper();
        canHeld = operator.getAButton();
        
    }
}
